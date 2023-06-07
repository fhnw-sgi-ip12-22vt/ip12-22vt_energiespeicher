package ch.fhnw.clashofenergiespeicher.view;

import ch.fhnw.clashofenergiespeicher.catalog.components.Ads1115;
import ch.fhnw.clashofenergiespeicher.catalog.components.Potentiometer;
import ch.fhnw.clashofenergiespeicher.catalog.components.SimpleButton;
import ch.fhnw.clashofenergiespeicher.catalog.components.helpers.PIN;
import ch.fhnw.clashofenergiespeicher.controller.util.mvcbase.PuiBase;
import ch.fhnw.clashofenergiespeicher.controller.GameController;
import ch.fhnw.clashofenergiespeicher.model.GameModel;
import com.pi4j.context.Context;

public class PhysicalUI extends PuiBase<GameModel, GameController> {
    // declare all hardware components attached to RaspPi
    // these are protected to give unit tests access to them
    protected SimpleButton button1;
    protected SimpleButton button2;
    protected Potentiometer poti1;
    protected Potentiometer poti2;
    protected Potentiometer poti3;
    protected Potentiometer poti4;
    protected Ads1115 ads1115;

    public PhysicalUI(GameController controller, Context pi4J) {
        super(controller, pi4J);
    }

    @Override
    public void initializeParts() {
        button1 = new SimpleButton(pi4J, PIN.D23, false);
        button2 = new SimpleButton(pi4J, PIN.D24, false);

        ads1115 = new Ads1115(pi4J, 0x01, Ads1115.GAIN.GAIN_4_096V, Ads1115.ADDRESS.GND, 4);
        poti1 = new Potentiometer(ads1115, 0, 3.3);
        poti2 = new Potentiometer(ads1115, 1, 3.3);
        poti3 = new Potentiometer(ads1115, 2, 3.3);
        poti4 = new Potentiometer(ads1115, 3, 3.3);

        poti1.startSlowContinuousReading(0.05, 10);
        poti2.startSlowContinuousReading(0.05, 10);
        poti3.startSlowContinuousReading(0.05, 10);
        poti4.startSlowContinuousReading(0.05, 10);
    }

    @Override
    public void setupUiToActionBindings(GameController controller) {
        String start = "The potentiometer slider is currently at ";
        String end = " % of its full travel.";
        poti1.setConsumerSlowReadChan(value1 -> {
            controller.setPotiValue1(value1);
            System.out.println(start + String.format("%.3f", value1)
                    + end);
        });
        poti2.setConsumerSlowReadChan(value2 -> {
            controller.setPotiValue2(value2);
            System.out.println(start + String.format("%.3f", value2)
                    + end);
        });
        poti3.setConsumerSlowReadChan(value3 -> {
            controller.setPotiValue3(value3);
            System.out.println(start + String.format("%.3f", value3)
                + end);
        });
        poti4.setConsumerSlowReadChan(value4 -> {
            controller.setPotiValue4(value4);
            System.out.println(start + String.format("%.3f", value4)
                + end);
        });

        button1.onDown(() -> controller.sendEvent(1));

        button2.onDown(() -> controller.sendEvent(2));
    }

    public boolean isGameStarted() {
        return button1.isDown() || button2.isDown();
    }
}

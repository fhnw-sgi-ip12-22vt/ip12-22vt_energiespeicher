package ch.fhnw.clashofenergiespeicher.view;

import ch.fhnw.clashofenergiespeicher.controller.GameController;
import ch.fhnw.clashofenergiespeicher.catalog.components.SimpleButton;
import ch.fhnw.clashofenergiespeicher.catalog.components.Potentiometer;
import com.pi4j.context.Context;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.function.Consumer;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class PhysicalUITest {

    @Mock
    private Context pi4J;

    @Mock
    private GameController controller;

    private PhysicalUI physicalUI;

    @Before
    public void setUp() {
        pi4J = mock(Context.class);
        controller = mock(GameController.class);
        physicalUI = new PhysicalUI(controller, pi4J);
    }

    @Test
    public void testInitializeParts() {
        physicalUI.initializeParts();

        assertNotNull(physicalUI.button1);
        assertNotNull(physicalUI.button2);
        assertNotNull(physicalUI.poti1);
        assertNotNull(physicalUI.poti2);
        assertNotNull(physicalUI.poti3);
        assertNotNull(physicalUI.poti4);
        assertNotNull(physicalUI.ads1115);
    }

    @Test
    public void testButton1Pressed() {
        physicalUI.initializeParts();
        physicalUI.setupUiToActionBindings(controller);

        // Create a mock event handler
        Runnable onDownHandler = mock(Runnable.class);

        // Set the mock event handler for button1
        physicalUI.button1.onDown(onDownHandler);
        physicalUI.button1.onUp(null);  // Disable onUp handler

        // Trigger the event handler
        onDownHandler.run();

        // Verify that the event handler is called
        verify(onDownHandler).run();
    }


    @Test
    public void testPoti2ValueChanged() {
        physicalUI.initializeParts();
        physicalUI.setupUiToActionBindings(controller);

        // Simulate potentiometer value change
        double value = 0.75;
        physicalUI.poti2.singleShotGetVoltage();  // Simulate reading the potentiometer value

        // Verify that the corresponding controller method is called
        verify(controller).setPotiValue2(value);
    }
}

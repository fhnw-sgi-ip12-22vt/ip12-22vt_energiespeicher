package ch.fhnw.clashofenergiespeicher.catalog.applications;

import ch.fhnw.clashofenergiespeicher.catalog.Application;
import ch.fhnw.clashofenergiespeicher.catalog.components.LedStrip;
import com.pi4j.context.Context;

/**
 * This example shows how to use the LEDStrip component by setting the LEDs on
 * the strip to different colors
 */
public class LedStrip_App implements Application {
    @Override
    public void execute(Context pi4j) {
        System.out.println("LED strip app started ...");
        // Initialize the RGB
        int pixels = 4;
        final LedStrip ledStrip = new LedStrip(pi4j, pixels, 0.5);

        // set them all off, so nothing is shining
        System.out.println("Starting with setting all leds off");
        ledStrip.allOff();

        System.out.println("setting the LEDs to RED");
        ledStrip.setStripColor(LedStrip.PixelColor.RED);
        ledStrip.render();
        delay(3000);

        System.out.println("setting the LEDs to Light Blue");
        ledStrip.setStripColor(LedStrip.PixelColor.LIGHT_BLUE);
        ledStrip.render();
        delay(3000);

        System.out.println("setting the first led to Purple");
        ledStrip.setPixelColor(0, LedStrip.PixelColor.PURPLE);
        ledStrip.render();
        delay(3000);

        System.out.println("setting the brightness to full and just show the first led as White");
        ledStrip.allOff();
        ledStrip.setBrightness(1);
        ledStrip.setPixelColor(0, LedStrip.PixelColor.WHITE);
        ledStrip.render();
        delay(3000);

        // finishing and closing
        ledStrip.close();
        System.out.println("closing the app");
        System.out.println("Color " + ledStrip.getPixelColor(0));

        System.out.println("LED strip app done.");
    }
}
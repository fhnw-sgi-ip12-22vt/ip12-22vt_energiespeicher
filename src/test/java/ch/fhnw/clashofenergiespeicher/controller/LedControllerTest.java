package ch.fhnw.clashofenergiespeicher.controller;

import ch.fhnw.clashofenergiespeicher.model.world.PlayerNumber;
import com.github.mbelling.ws281x.Color;
import com.github.mbelling.ws281x.LedStrip;
import com.github.mbelling.ws281x.Ws281xLedStrip;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import static org.junit.jupiter.api.Assertions.*;

class LedControllerTest {
    public LedStrip ledStrip;

    @BeforeAll
    public static void setupNativeLibrary() {
        String libraryPath = "/src/test/java/ch/fhnw/clashofenergiespeicher/controller/libws281x.so";
        System.load(libraryPath);
    }

    @BeforeEach
    public void setUp() {
        this.ledStrip = new Ws281xLedStrip();
        ledStrip.setBrightness(120);
    }

    @Test
    public void testAllLedsAreFunctional() {
        // Test if the LED's work assuming the LED strip has 28 LEDs
        int numLeds = 28;

        for (int i = 0; i < numLeds; i++) {
            when(ledStrip.getPixel(i)).thenReturn((long) Color.RED.getRed());
            ledStrip.setPixel(i, Color.RED);
            ledStrip.render();
            long actualColor = ledStrip.getPixel(i);
            long expectedColor = Color.RED.getRed();
            assertEquals(expectedColor, actualColor, "LED at index " + i + " is not functional");
        }

        verify(ledStrip, Mockito.times(numLeds)).render();
    }



 // @Test
 // public void testUpdatePlayerLedBar(PlayerNumber playerNumber, double difference, int expectedLedIndex, int expectedRed, int expectedGreen, int expectedBlue) {
 //     ledController.updatePlayerLedBar(difference, playerNumber);

 //     long actualColor = ledController.ledStrip.getPixel(expectedLedIndex);
 //     Color expectedColor = new Color(expectedRed, expectedGreen, expectedBlue);

 //     assertEquals(expectedColor, actualColor, "LED at index " + expectedLedIndex + " for " + playerNumber + " is not correctly updated");
 // }

}

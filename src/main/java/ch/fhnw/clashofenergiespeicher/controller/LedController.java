package ch.fhnw.clashofenergiespeicher.controller;
import ch.fhnw.clashofenergiespeicher.model.world.PlayerNumber;
import com.github.mbelling.ws281x.Color;
import com.github.mbelling.ws281x.LedStrip;
import com.github.mbelling.ws281x.Ws281xLedStrip;

import static com.github.mbelling.ws281x.Color.*;
import static com.github.mbelling.ws281x.Color.BLACK;

public class LedController {
    public LedStrip ledStrip;

    private static final int PLAYER_LED_OFFSET = 14;
    private static final int LED_IDX_ES1 = 10;
    private static final int LED_IDX_ES2 = 11;
    private static final int LED_IDX_STADT = 12;
    private static final int LED_IDX_KW = 13;
    private static final int LED_IDX_EVENT = 14;
    private static final int NUMBER_OF_LED_IN_BAR = 9;

    public LedController() {
        this.ledStrip = new Ws281xLedStrip();
        ledStrip.setBrightness(120);
    }

    public void updateEventLed(int ledIndex){
        ledStrip.setPixel(ledIndex, Color.GREEN);
        ledStrip.render();
    }

    public void stopEventLed(int ledIndex) {
        ledStrip.setPixel(ledIndex, BLACK);
        ledStrip.render();
    }


    public void updatePlayerLedBar(double difference, PlayerNumber playerNumber) {
        int offset;
        offset = (playerNumber == PlayerNumber.PLAYER1) ? 0 : PLAYER_LED_OFFSET;
        difference = difference / 1000;

        resetPlayerBarLED();

        for (int led = 0; led < NUMBER_OF_LED_IN_BAR; led++) {

            int ledIndex;
            Color ledColor;

            int ledValue = (int) Math.round(difference);

            if (ledValue < -4) {
                ledValue = -4;
            } else if (ledValue > 4) {
                ledValue = 4;
            }


            //Nicht nach RGB, sondern nach GRB, wegen dem verwechselt
            switch (ledValue) {
                case -4:
                    ledColor = GREEN;
                    ledIndex = 0 + offset;
                    break;
                case -3:
                    ledColor = YELLOW;
                    ledIndex = 1 + offset;
                    break;
                case -2:
                    ledColor = YELLOW;
                    ledIndex = 2 + offset;
                    break;
                case -1:
                    ledColor = RED;
                    ledIndex = 3 + offset;
                    break;
                case -0:
                    ledColor = RED;
                    ledIndex = 4 + offset;
                    break;
                case 1:
                    ledColor = RED;
                    ledIndex = 5 + offset;
                    break;
                case 2:
                    ledColor = YELLOW;
                    ledIndex = 6 + offset;
                    break;
                case 3:
                    ledColor = YELLOW;
                    ledIndex = 7 + offset;
                    break;
                case 4:
                    ledColor = GREEN;
                    ledIndex = 8 + offset;
                    break;

                default:
                    ledColor = RED;
                    ledIndex = 0;
            }


            ledStrip.setPixel(ledIndex, ledColor);
            ledStrip.render();

        }
    }

    public void resetLED() {
        ledStrip.setStrip(BLACK);
        ledStrip.render();
    }

    public void resetPlayerBarLED() {
        ledStrip.setPixel(0, BLACK);
        ledStrip.setPixel(1, BLACK);
        ledStrip.setPixel(2, BLACK);
        ledStrip.setPixel(3, BLACK);
        ledStrip.setPixel(4, BLACK);
        ledStrip.setPixel(5, BLACK);
        ledStrip.setPixel(6, BLACK);
        ledStrip.setPixel(7, BLACK);
        ledStrip.setPixel(8, BLACK);
        ledStrip.setPixel(0 + PLAYER_LED_OFFSET, BLACK);
        ledStrip.setPixel(1 + PLAYER_LED_OFFSET, BLACK);
        ledStrip.setPixel(2 + PLAYER_LED_OFFSET, BLACK);
        ledStrip.setPixel(3 + PLAYER_LED_OFFSET, BLACK);
        ledStrip.setPixel(4 + PLAYER_LED_OFFSET, BLACK);
        ledStrip.setPixel(5 + PLAYER_LED_OFFSET, BLACK);
        ledStrip.setPixel(6 + PLAYER_LED_OFFSET, BLACK);
        ledStrip.setPixel(7 + PLAYER_LED_OFFSET, BLACK);
        ledStrip.setPixel(8 + PLAYER_LED_OFFSET, BLACK);
        ledStrip.render();
    }
}

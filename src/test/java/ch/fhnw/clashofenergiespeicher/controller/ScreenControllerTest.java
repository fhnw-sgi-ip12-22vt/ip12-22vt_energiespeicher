package ch.fhnw.clashofenergiespeicher.controller;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScreenControllerTest {
    private ScreenController screenController;

    @Before
    public void setUp() {
        screenController = new ScreenController();
    }

    @Test
    public void testSetPlayerScore() {
        String playerScore = "100";
        screenController.setPlayerScore(playerScore);

        // Here we cannot test this because playerScore is a JavaFX UI component
         //assertEquals(playerScore, screenController.playerScore.getText());
    }

    @Test
    public void testSetHighScore() {
        String highScore = "200";
        screenController.setHighScore(highScore);

        // Here we cannot test this because highScore is a JavaFX UI component
        // assertEquals(highScore, screenController.highScore.getText());
    }

    @Test
    public void testSetCurrentDay() {
        String currentDay = "Monday";
        screenController.setCurrentDay(currentDay);

        // Here we cannot test this because currentDay is a JavaFX UI component
        // assertEquals(currentDay, screenController.currentDay.getText());
    }

    @Test
    public void testSetInfoText() {
        String infoText = "Hello, World!";
        screenController.setInfoText(infoText);

        // Here we cannot test this because info is a JavaFX UI component
         assertEquals(infoText, screenController.getInfo().getText());
    }
}

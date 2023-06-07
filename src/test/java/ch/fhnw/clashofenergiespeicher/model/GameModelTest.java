package ch.fhnw.clashofenergiespeicher.model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameModelTest {

    private GameModel gameModel;

    @Before
    public void setUp() {
        gameModel = new GameModel();
    }

    @Test
    public void testInitialValues() {
        // Verify initial values of observable values and properties
        assertEquals("JavaFX " + System.getProperty("javafx.version") + ", running on Java " + System.getProperty("java.version") + ".", gameModel.systemInfo.getValue());
        assertEquals(0.0, gameModel.potiValue1.getValue(), 0.01);
        assertEquals(0.0, gameModel.potiValue2.getValue(), 0.01);
        assertEquals(0.9, gameModel.potiValue3.getValue(), 0.01);
        assertEquals(0.9, gameModel.potiValue4.getValue(), 0.01);

        // Verify initial values of properties
        assertEquals(1, gameModel.dayCount.get());
        assertEquals(0, gameModel.scorePlayerOne.get());
        assertEquals(0, gameModel.scorePlayerTwo.get());
        assertEquals(5000, gameModel.highscore.get());
    }

    @Test
    public void testSettersAndGetters() {
        // Update values using setters
        gameModel.setScorePlayerOne(100);
        gameModel.setDayCount(5);
        gameModel.setScorePlayerTwo(50);
        gameModel.setHighscore(10000);

        // Verify values using getters
        assertEquals(100, gameModel.getScorePlayerOne());
        assertEquals(5, gameModel.getDayCount());
        assertEquals(50, gameModel.getScorePlayerTwo());
        assertEquals(10000, gameModel.getHighscore());
    }
}

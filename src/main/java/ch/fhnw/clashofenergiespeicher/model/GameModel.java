package ch.fhnw.clashofenergiespeicher.model;

import ch.fhnw.clashofenergiespeicher.controller.HighScore;
import ch.fhnw.clashofenergiespeicher.controller.util.mvcbase.ObservableValue;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * In MVC the 'Model' mainly consists of 'ObservableValues'.
 * <p>
 * There should be no need for additional methods.
 * <p>
 * All the application logic is handled by the 'Controller'
 */
public class GameModel {

    public final ObservableValue<String> systemInfo = new ObservableValue<>("JavaFX "
            + System.getProperty("javafx.version") + ", running on Java " + System.getProperty("java.version") + ".");
    public final ObservableValue<Double> potiValue1 = new ObservableValue<>(0.0);
    public final ObservableValue<Double> potiValue2 = new ObservableValue<>(0.0);
    public final ObservableValue<Double> potiValue3 = new ObservableValue<>(0.9);
    public final ObservableValue<Double> potiValue4 = new ObservableValue<>(0.9);
    public final SimpleIntegerProperty dayCount = new SimpleIntegerProperty(1);
    public final SimpleIntegerProperty scorePlayerOne = new SimpleIntegerProperty(0);

    public final SimpleIntegerProperty scorePlayerTwo = new SimpleIntegerProperty(0);

    public final SimpleIntegerProperty highscore = new SimpleIntegerProperty(5000);

    public int getScorePlayerOne() {
        return scorePlayerOne.get();
    }

    public void setScorePlayerOne(int scorePlayerOne) {
        this.scorePlayerOne.set(scorePlayerOne);
    }

    public int getDayCount() {
        return dayCount.get();
    }

    public int getScorePlayerTwo() {
        return scorePlayerTwo.get();
    }

    public int getHighscore() {
        return highscore.get();
    }
    public void setDayCount(int dayCount) {
        this.dayCount.set(dayCount);
    }

    public void setScorePlayerTwo(int scorePlayerTwo) {
        this.scorePlayerTwo.set(scorePlayerTwo);
    }

    public void setHighscore(int highscore) {
        this.highscore.set(highscore);
    }
}

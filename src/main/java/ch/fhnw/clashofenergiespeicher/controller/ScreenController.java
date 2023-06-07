package ch.fhnw.clashofenergiespeicher.controller;

import ch.fhnw.clashofenergiespeicher.model.GameModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ScreenController implements Initializable {

    private int currentBackgroundIndex = 0;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private Label highScore;
    @FXML
    private Label playerScore;
    @FXML
    private Label currentDay;
    @FXML
    private Label info;
    @FXML
    private ProgressBar energyStorageLeft;
    @FXML
    private ProgressBar energyStorageRight;

    private GameModel gameModel = new GameModel();
    private final String backgroundMorning = String.valueOf(getClass().getResource("/img/Background_morning.jpg"));
    private final String backgroundDay = String.valueOf(getClass().getResource("/img/Background_day.jpg"));
    private final String backgroundEvening = String.valueOf(getClass().getResource("/img/Background_evening.jpg"));
    private final String backgroundNight = String.valueOf(getClass().getResource("/img/Background_night.jpg"));


    public void setEnergyStorageLeft(double currentProgress) {
        this.energyStorageLeft.setProgress(currentProgress);
    }

    public void setEnergyStorageRight(double currentProgress) {
        this.energyStorageRight.setProgress(currentProgress);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void setPlayerScore(String playerScore) {
        this.playerScore.setText(playerScore);
    }

    public void setHighScore(String highScore) {
        this.highScore.setText(highScore);
    }

    public void setCurrentDay(String currentDay) {
        this.currentDay.setText(currentDay);
    }

    public Label getInfo() {
        return info;
    }

    public void setInfo(Label info) {
        this.info = info;
    }

    public void setInfoText(String info) {
        this.info.setText(info);
    }


    public void updateBackground() {
        String[] backgrounds = {backgroundMorning, backgroundDay, backgroundEvening, backgroundNight};

        // Set background image to current background
        Image currentBackground = new Image(backgrounds[currentBackgroundIndex]);
        backgroundImageView.setImage(currentBackground);

        // Create a new FadeTransition for the image view
        FadeTransition ft = new FadeTransition(Duration.seconds(1), backgroundImageView);

        // Set the action to be performed when the transition finishes
        ft.setOnFinished(event ->
                // Increase the current background index
                currentBackgroundIndex = (currentBackgroundIndex + 1) % backgrounds.length
        );
        // Play the transition
        ft.play();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // The initialize method is intentionally left empty.
        // The initialization logic for this controller is handled in other methods.
    }
}
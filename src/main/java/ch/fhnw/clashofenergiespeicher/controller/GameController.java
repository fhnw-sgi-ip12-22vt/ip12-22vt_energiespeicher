package ch.fhnw.clashofenergiespeicher.controller;

import ch.fhnw.clashofenergiespeicher.controller.util.Pi4JContext;
import ch.fhnw.clashofenergiespeicher.controller.util.mvcbase.ControllerBase;
import ch.fhnw.clashofenergiespeicher.model.GameModel;
import ch.fhnw.clashofenergiespeicher.model.event.Event;
import ch.fhnw.clashofenergiespeicher.model.event.Events;
import ch.fhnw.clashofenergiespeicher.model.world.*;
import ch.fhnw.clashofenergiespeicher.model.world.EnergyStorage.Storage;
import ch.fhnw.clashofenergiespeicher.settings.Setting;
import ch.fhnw.clashofenergiespeicher.view.PhysicalUI;
import com.pi4j.context.Context;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

public class GameController extends ControllerBase<GameModel> {
    Player player1;
    Player player2;
    private ScreenController screenControllerPlayer1;
    private ScreenController screenControllerPlayer2;
    private LedController ledController;
    private GameModel gameModel;
    private Timeline gameLoop;
    private Timeline startLoop;
    private Timeline gameLoopBackgrounds;
    private Daytime daytime;
    private Context pi4J; //for LED
    private PhysicalUI pui;
    private boolean gameIsRunning;

    private static final double[] E_USAGE_PER_GAME_HOUR = EnergyUsageInitializer.initEnergyUsageArray();
    private static final double ENERGY_FROM_CITY_PER_STORAGE =
        Setting.ENERGY_PRODUCTION_PER_DAY / Setting.DAY_TIME_INTERVAL / 2.0;
    private static final double GAME_HOUR_LENGTH = 24.0 / Setting.DAY_TIME_INTERVAL;

    public GameController(ScreenController screenController, ScreenController screenController1) {
        super(screenController.getGameModel());
        this.screenControllerPlayer1 = screenController;
        this.screenControllerPlayer2 = screenController1;
        this.ledController = new LedController();
        this.gameModel = screenController.getGameModel();
        screenControllerPlayer1.setGameModel(gameModel);
        pi4J = Pi4JContext.createContext();
        pui = new PhysicalUI(this, pi4J);
        gameIsRunning = false;

        EnergyStorage[] eStorages1 = {new EnergyStorage(Storage.CRANE), new EnergyStorage(Storage.PUMPED_STORAGE)};
        EnergyStorage[] eStorages2 = {new EnergyStorage(Storage.FLYWHEEL),
                new EnergyStorage(Storage.COMPRESSED_AIR_STORAGE)};
        player1 = new Player(eStorages1, PlayerNumber.PLAYER1);
        player2 = new Player(eStorages2, PlayerNumber.PLAYER2);

        daytime = new Daytime();
        screenControllerPlayer1.setHighScore(String.valueOf(model.getHighscore()));
        screenControllerPlayer2.setHighScore(String.valueOf(model.getHighscore()));
    }

    public void startListener(PhysicalUI pui) {
        startLoop = new Timeline(new KeyFrame(Duration.millis(100), event -> checkIfGameIsStarted(pui)));
        startLoop.setCycleCount(Animation.INDEFINITE);
        startLoop.play();
        screenControllerPlayer1.setInfoText("Drücke den Knopf um das Spiel zu starten!");
        screenControllerPlayer2.setInfoText("Drücke den Knopf um das Spiel zu starten!");
    }

    public void checkIfGameIsStarted(PhysicalUI pui) {
        if (pui.isGameStarted()) {
            startGameLoop();
            startLoop.stop();
            screenControllerPlayer1.setInfoText(" ");
            screenControllerPlayer2.setInfoText(" ");
            gameIsRunning = true;
        } else {
            gameIsRunning = false;
        }
    }

    public void startGameLoop() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(1000), event -> updateGameState()));
        gameLoopBackgrounds = new Timeline(new KeyFrame(Duration.millis(3000), event -> updateBackground()));

        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
        gameLoopBackgrounds.setCycleCount(Animation.INDEFINITE);
        gameLoopBackgrounds.play();
    }

    private void updateGameState() {
        System.out.println("=== Day: " + gameModel.dayCount.getValue() + " " + daytime.getCurrentTime() + "===");
        updatePlayerGameStates();
        // Update the model with the new values
        updateScores();
        // Update Time
        updateTime();

        if (gameModel.dayCount.getValue() >= Setting.GAME_LENGTH) {
            gameOver();
        }
    }

    private void updatePlayerGameStates() {
        handlePlayerOne();
        handlePlayerTwo();
    }


    private void updateBackground() {
        // Update the GUI with the new values from the model
        screenControllerPlayer1.updateBackground();
        screenControllerPlayer2.updateBackground();
    }

    private void updateScores() {
        model.setScorePlayerOne(player1.getScore().getTotalScore());
        model.setScorePlayerTwo(player2.getScore().getTotalScore());
        screenControllerPlayer1.setPlayerScore(Integer.toString(model.getScorePlayerOne()));
        screenControllerPlayer2.setPlayerScore(Integer.toString(model.getScorePlayerTwo()));

        // check highscore
        if (player1.getScore().getTotalScore() > model.getHighscore() || player2.getScore().getTotalScore() > model.getHighscore()) {
            model.setHighscore(Math.max(player1.getScore().getTotalScore(), player2.getScore().getTotalScore()));
            screenControllerPlayer1.setHighScore(Integer.toString(model.getHighscore()));
            screenControllerPlayer2.setHighScore(Integer.toString(model.getHighscore()));

        }
        System.out.println("GameController --> TotalScore p1: " + player1.getScore().getTotalScore());
        System.out.println("GameController --> TotalScore p2: " + player2.getScore().getTotalScore());
    }

    private void updateTime() {
        if(daytime.addHoursCheckNewDay(24 / Setting.DAY_TIME_INTERVAL)) model.setDayCount(model.getDayCount() + 1);

        screenControllerPlayer1.setCurrentDay(Integer.toString(model.getDayCount()));
        screenControllerPlayer2.setCurrentDay(Integer.toString(model.getDayCount()));
    }

    private void handlePlayerOne() {
        double potiValue1 = model.potiValue1.getValue();
        System.out.println("PotiValue1: " + potiValue1);
        double potiValue2 = model.potiValue2.getValue();
        System.out.println("PotiValue2: " + potiValue2);

        // check potiValues for strange behaviour
        potiValue1 = Math.min(potiValue1, 1.0);
        potiValue2 = Math.min(potiValue2, 1.0);

        handlePlayerGameState(potiValue1, potiValue2, player1);
    }

    private void handlePlayerTwo() {
        double potiValue3 = model.potiValue3.getValue();
        System.out.println("PotiValue3: " + potiValue3);
        double potiValue4 = model.potiValue4.getValue();
        System.out.println("PotiValue4: " + potiValue4);

        // check potiValues for strange behaviour
        potiValue3 = Math.min(potiValue3, 1.0);
        potiValue4 = Math.min(potiValue4, 1.0);

        handlePlayerGameState(potiValue3, potiValue4, player2);
    }

    private void handlePlayerGameState(double potiValue1, double potiValue2, Player player) {
        double optimalEnergyUsage = E_USAGE_PER_GAME_HOUR[(int) (daytime.getCurrentTime().getHour()
            / GAME_HOUR_LENGTH)];

        // How much % of energy should be used (given to city)?
        double useEnergyPoti1 = (potiValue1 * ENERGY_FROM_CITY_PER_STORAGE);
        double useEnergyPoti2 = (potiValue2 * ENERGY_FROM_CITY_PER_STORAGE);
        double useEnergy = useEnergyPoti1 + useEnergyPoti2;

        // Calculate optimal energy to store
        double optimalEnergyToStore;
        if (optimalEnergyUsage > ENERGY_FROM_CITY_PER_STORAGE * 2) {
            optimalEnergyToStore = ENERGY_FROM_CITY_PER_STORAGE * 2;
        } else {
            optimalEnergyToStore = ENERGY_FROM_CITY_PER_STORAGE * 2 - optimalEnergyUsage;
        }

        double currentEnergyToStore = ENERGY_FROM_CITY_PER_STORAGE * 2 - useEnergy;

        // update playerLedBar
        ledController.updatePlayerLedBar(optimalEnergyToStore - currentEnergyToStore, player.getPlayerNumber());

        // Calculate points: optimalEnergy & energyToUse
        System.out.printf(
                "GameController --> optimalEnergyToStore: %s ; currentEnergyToStore: %s ; Differenz: %s",
                optimalEnergyToStore, currentEnergyToStore, optimalEnergyToStore - currentEnergyToStore);
        player.calculateScore(optimalEnergyToStore, currentEnergyToStore);

        handleEnergy(player, optimalEnergyUsage, useEnergy);

        // safe left over energy
        player.getEnergyStorages()[0].saveEnergy(ENERGY_FROM_CITY_PER_STORAGE - useEnergyPoti1);
        player.getEnergyStorages()[1].saveEnergy(ENERGY_FROM_CITY_PER_STORAGE - useEnergyPoti2);
    }

    private void handleEnergy(Player player, double optimalEnergyUsage, double useEnergy) {
        // Check if player sent enough energy to the city
        double missingEnergy = optimalEnergyUsage - useEnergy;
        handleMissingEnergy(player, missingEnergy);
        handleEnergyStoragesGUI(player);
    }

    private void handleEnergyStoragesGUI(Player player) {
        for (EnergyStorage storage : player.getEnergyStorages()) {
            ScreenController screen;
            switch (player.getPlayerNumber()) {
                case PLAYER1:
                    screen = screenControllerPlayer1;
                    break;
                case PLAYER2:
                    screen = screenControllerPlayer2;
                    break;
                default:
                    throw new IllegalStateException("Unknown Player: " + player.getPlayerNumber());
            }

            System.out.println("Inhalt E-storage: " + (double) storage.getCurrentEnergy());
            if (storage.getMaxEnergy() == Setting.MAX_ENERGY_BIG_STORAGE) {
                screen.setEnergyStorageLeft(
                        (double) storage.getCurrentEnergy() / Setting.MAX_ENERGY_BIG_STORAGE);
            } else {
                screen.setEnergyStorageRight(
                        (double) storage.getCurrentEnergy() / Setting.MAX_ENERGY_SMALL_STORAGE);
            }
        }
    }

    private void handleMissingEnergy(Player player, double missingEnergy) {
        if (missingEnergy > 0) {
            // fill city with missing energy from energyStorage
            for (EnergyStorage storage : player.getEnergyStorages()) {
                missingEnergy = storage.useEnergy(missingEnergy);
                if (missingEnergy == 0)
                    break;
            }
            // if not enough energy is stored: fail - send warning or game over
            if (missingEnergy > 0) {
                if (player.getWarnings() < Setting.MAX_WARNINGS) {
                    player.increaseWarning();
                    System.out.println("Warning " + player.getPlayerNumber()+ ": " + player.getWarnings());
                } else {
                    gameOver();
                }
            }
        }
    }


    private void gameOver() {
        // Check warnings of each player
        if (player1.getWarnings() == Setting.MAX_WARNINGS) {
            winPlayer2();
        } else if (player2.getWarnings() == Setting.MAX_WARNINGS) {
            winPlayer1();
        } else {
            if (model.getScorePlayerOne() == model.getScorePlayerTwo()) {
                screenControllerPlayer1.setInfoText("Unentschieden!");
                screenControllerPlayer2.setInfoText("Unentschieden!");
            } else if (model.getScorePlayerOne() > model.getScorePlayerTwo()) {
                winPlayer1();
            } else {
                winPlayer2();
            }
        }
        ledController.resetLED();
        gameLoop.stop();
        gameLoopBackgrounds.stop();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        resetValues();
        startListener(pui);
    }

    private void winPlayer1() {
        screenControllerPlayer1.setInfoText("Du hast gewonnen!");
        screenControllerPlayer2.setInfoText("Du hast verloren!");
        PauseTransition pause = new PauseTransition(Duration.seconds(10));
        pause.setOnFinished(text -> screenControllerPlayer1.setInfoText(" "));
        pause.setOnFinished(text -> screenControllerPlayer2.setInfoText(" "));
        pause.play();
    }

    private void winPlayer2() {
        screenControllerPlayer1.setInfoText("Du hast verloren!");
        screenControllerPlayer2.setInfoText("Du hast gewonnen!");
        PauseTransition pause = new PauseTransition(Duration.seconds(10));
        pause.setOnFinished(text -> screenControllerPlayer1.setInfoText(" "));
        pause.setOnFinished(text -> screenControllerPlayer2.setInfoText(" "));
        pause.play();
    }

    public void resetValues() {
        model.setDayCount(0);
        player1.getScore().resetScore();
        player2.getScore().resetScore();
        model.setScorePlayerOne(0);
        model.setScorePlayerTwo(0);
        screenControllerPlayer1.setPlayerScore("0");
        screenControllerPlayer2.setPlayerScore("0");
        screenControllerPlayer1.setCurrentDay("0");
        screenControllerPlayer2.setCurrentDay("0");
        for(EnergyStorage energyStorage : player1.getEnergyStorages()) {
            energyStorage.setCurrentEnergy(10000);
        }
        for(EnergyStorage energyStorage : player2.getEnergyStorages()) {
            energyStorage.setCurrentEnergy(10000);
        }
        System.out.println(model.getScorePlayerOne());
        System.out.println(model.getScorePlayerTwo());
    }

    // the logic we need in our application
    // these methods can be called from GUI and PUI (and from nowhere else)
    public void setPotiValue1(double value1) {
        setValue(model.potiValue1, value1);
    }

    public void setPotiValue2(double value2) {
        setValue(model.potiValue2, value2);
    }

    public void setPotiValue3(double value3) {
        setValue(model.potiValue3, value3);
    }

    public void setPotiValue4(double value4) {
        setValue(model.potiValue4, value4);
    }

    public void sendEvent(int playerNr) {
        if(gameIsRunning) {
            Player player;
            Player enemy;
            ScreenController screenControllerEventEnemy;
            ScreenController screenControllerEvent;
            Event event;
            int ledIndex;
            switch (playerNr) {
                case 1:
                    System.out.println("BUTTON 1 Gedrückt");
                    player = player1;
                    enemy = player2;
                    event = Events.KINGKONG.getEvent();
                    screenControllerEventEnemy = screenControllerPlayer2;
                    screenControllerEvent = screenControllerPlayer1;
                    ledIndex = 13;
                    break;
                case 2:
                    System.out.println("BUTTON 2 Gedrückt");
                    player = player2;
                    enemy = player1;
                    event = Events.GODZILLA.getEvent();
                    screenControllerEventEnemy = screenControllerPlayer1;
                    screenControllerEvent = screenControllerPlayer2;
                    ledIndex = 27;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value for player: " + playerNr);
            }
            // Check available player points
            if (player.getScore().getTotalScore() >= Setting.POINTS_COST_PER_EVENT && !enemy.getHasActiveEvent()) {
                // deduct points
                player.getScore().setTotalScore(player.getScore().getTotalScore() - Setting.POINTS_COST_PER_EVENT);

                // get random Event and send to enemy player
                enemy.setHasActiveEvent(true);
                ledController.updateEventLed(ledIndex);
                // send notification to enemy player's infobox
                Platform.runLater(() -> {
                    screenControllerEventEnemy.setInfoText(event.getNotification());
                    screenControllerEvent.setInfoText("Du hast ein Event gesendet!");
                });

                //Magic number 1000, because of time in milliseconds
                Timeline eventLoop = new Timeline(new KeyFrame(Duration.millis((double)event.getDuration() * 1000)));
                eventLoop.setCycleCount(1);
                eventLoop.play();

                eventLoop.setOnFinished(e -> {
                    enemy.setHasActiveEvent(false);
                    Platform.runLater(() -> {
                        screenControllerEventEnemy.setInfoText(" ");
                        ledController.stopEventLed(ledIndex);
                    });
                    eventLoop.stop();
                });
            } else {
                Platform.runLater(() -> {
                    if (enemy.getHasActiveEvent()) {
                        screenControllerEvent.setInfoText("Event ist bereits aktiv!");
                        PauseTransition pause = new PauseTransition(Duration.seconds(10));
                        pause.setOnFinished(text -> screenControllerEvent.setInfoText(" "));
                        pause.play();
                    } else {
                        screenControllerEvent.setInfoText("Nicht genügend Punkte für ein Event!");
                        PauseTransition pause = new PauseTransition(Duration.seconds(10));
                        pause.setOnFinished(text -> screenControllerEvent.setInfoText(" "));
                        pause.play();
                    }
                });
            }
        }
    }
}
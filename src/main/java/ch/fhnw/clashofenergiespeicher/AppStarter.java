package ch.fhnw.clashofenergiespeicher;

import ch.fhnw.clashofenergiespeicher.controller.GameController;
import ch.fhnw.clashofenergiespeicher.controller.ScreenController;
import ch.fhnw.clashofenergiespeicher.controller.util.Pi4JContext;
import ch.fhnw.clashofenergiespeicher.view.PhysicalUI;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppStarter extends Application {

    private PhysicalUI pui;

    @Override
    public void start(Stage primaryStage) throws IOException {

        ScreenController screenControllerPlayer1;
        ScreenController screenControllerPlayer2;
        GameController gameController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Screen1.fxml"));
        AnchorPane page = fxmlLoader.load();
        Scene scene = new Scene(page);
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/fxml/Screen2.fxml"));
        AnchorPane page1 = fxmlLoader1.load();
        Scene scene1 = new Scene(page1);

        screenControllerPlayer1 = fxmlLoader.getController();
        screenControllerPlayer2 = fxmlLoader1.getController();

        gameController = new GameController(screenControllerPlayer1, screenControllerPlayer2);
        pui = new PhysicalUI(gameController, Pi4JContext.createContext());
        pui.setupUiToActionBindings(gameController);
        gameController.startListener(pui);

        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.setTitle("Clash of Energiespeicher");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        // Set the scene for screen 1 and show the stage
        Stage screen1 = new Stage();
        screen1.setX(800);
        screen1.setY(0);
        screen1.setWidth(848);
        screen1.setHeight(480);

        screen1.setScene(scene1);
        screen1.setMaximized(true);
        screen1.initStyle(StageStyle.UNDECORATED);
        screen1.show();
    }

    @Override
    public void stop() {
        pui.shutdown();
    }
    public static void main(String[] args) {
        launch(args); // start the whole application
    }
}

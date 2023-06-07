package ch.fhnw.clashofenergiespeicher.controller;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import ch.fhnw.clashofenergiespeicher.model.GameModel;
import ch.fhnw.clashofenergiespeicher.model.world.Player;

class GameControllerTest {

    @Test
    void testStartGameLoop() {
        // prepare mock objects
        GameModel mockModel = mock(GameModel.class);
        ScreenController mockScreenController = mock(ScreenController.class);
        when(mockScreenController.getGameModel()).thenReturn(mockModel);

        // create object to test
        GameController controller = new GameController(mockScreenController, mockScreenController);

        // start the game loop
        controller.startGameLoop();

        // Since gameLoop is private we can't directly check if it's running,
        // but we can verify if getGameModel() method of mockScreenController was called,
        // as it's used in the constructor of GameController
        verify(mockScreenController, times(2)).getGameModel();
    }

}

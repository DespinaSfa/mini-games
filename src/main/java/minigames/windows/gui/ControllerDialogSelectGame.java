package minigames.windows.gui;

import minigames.hangman.business.logic.SelectedGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ControllerDialogSelectGame {
    private UserGameData data;
    @FXML
    private Button ButtonHMSinglePlayer, ButtonHMMultiplayer, ButtonBSSinglePlayer;

    private static final Logger logger = LogManager.getLogger(ControllerDialogSelectGame.class);

    public ControllerDialogSelectGame(UserGameData data) {
        this.data = data;
    }

    private void ChangeSceneEnterSinglePlayerName(String dialogName) throws IOException {
        ControllerSinglePlayerChooseUsername myController = new ControllerSinglePlayerChooseUsername(data);
        FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource(dialogName));
        root.setController(myController);
        Scene scene = new Scene(root.load());
        Stage window = (Stage) ButtonBSSinglePlayer.getScene().getWindow();
        window.setScene(scene);
    }

    @FXML
    private void BSSinglePlayer_ButtonPressed(ActionEvent event) throws IOException {
        if (event.getSource() == ButtonBSSinglePlayer) {
            logger.info("Button BSSinglePlayer pressed");
            data.setGameSelection(SelectedGame.BeeSweeperSinglePlayer);

            ChangeSceneEnterSinglePlayerName("DialogSinglePlayerChooseUsername.fxml");
        }
    }

    @FXML
    private void HMMultiPlayer_ButtonPressed(ActionEvent event) throws IOException {
        if (event.getSource() == ButtonHMMultiplayer) {
            logger.info("Button HMMultipPlayer pressed");
            data.setGameSelection(SelectedGame.HangmanMultiPlayer);

            ControllerMultiplayerChooseUsername myController = new ControllerMultiplayerChooseUsername(data);
            FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogMultiplayerChooseUsername.fxml"));
            root.setController(myController);
            Scene scene = new Scene(root.load());
            Stage window = (Stage) ButtonHMMultiplayer.getScene().getWindow();
            window.setScene(scene);
        }
    }

    @FXML
    private void HMSinglePlayer_ButtonPressed(ActionEvent event) throws IOException {
        if (event.getSource() == ButtonHMSinglePlayer) {
            logger.info("Button HMSinglePlayer pressed");
            data.setGameSelection(SelectedGame.HangmanSinglePlayer);
            ChangeSceneEnterSinglePlayerName("DialogSinglePlayerChooseUsername.fxml");
        }
    }
}

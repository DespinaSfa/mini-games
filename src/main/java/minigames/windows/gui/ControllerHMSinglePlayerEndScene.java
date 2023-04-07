package minigames.windows.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerHMSinglePlayerEndScene {

    private static final Logger logger = LogManager.getLogger(ControllerHMSinglePlayerEndScene.class);

    private UserGameData data;
    @FXML
    private Button PlayAgainButton, GoBackButton;
    @FXML
    private Label GameResultInformation, MisteryWords;

    public ControllerHMSinglePlayerEndScene(UserGameData data) {
        this.data = data;
    }

    @FXML
    private void PlayAgain_ButtonPressed(ActionEvent event) throws Exception {
        if (event.getSource() == PlayAgainButton) {
            ControllerHMPlayerOne myController = new ControllerHMPlayerOne(data);
            FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogHMMultiplayerPlayer.fxml"));
            root.setController(myController);
            Scene scene = new Scene(root.load());
            Stage window = (Stage) PlayAgainButton.getScene().getWindow();
            window.setScene(scene);
        }
    }

    @FXML
    private void GoBack_ButtonPressed(ActionEvent event) throws Exception {
        if (event.getSource() == GoBackButton) {
            ControllerDialogSelectGame myController = new ControllerDialogSelectGame(data);
            FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogSelectGame.fxml"));
            root.setController(myController);
            Scene scene = new Scene(root.load());
            Stage window = (Stage) GoBackButton.getScene().getWindow();
            window.setScene(scene);
        }
    }

    @FXML
    private void initialize() {
        MisteryWords.setText("Misteryword:\nPlayer " + data.getPlayerName1() + ": " + data.getHmGameLogic().getMysteryWord());
        if (data.getHmGameLogic().getCurrentLevel() >= 10) {
            logger.info("Player lost!");
            GameResultInformation.setText("Player " + data.getPlayerName1() + " you LOST. Game over.\n Better luck next Time!");
        } else {
            logger.info("Player won!");
            GameResultInformation.setText("Congratulations, you WON.");
        }
    }
}

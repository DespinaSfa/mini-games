package minigames.windows.gui;

import minigames.hangman.business.logic.HangmanWinner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerHMMultiplayerWinner {

    private static final Logger logger = LogManager.getLogger(ControllerHMMultiplayerWinner.class);

    private UserGameData data;
    @FXML
    private Button PlayAgainButton, GoBackButton;
    @FXML
    private Label LabelWinnerInformation, MisteryWords;

    public ControllerHMMultiplayerWinner(UserGameData data) {
        this.data = data;
    }

    @FXML
    private void PlayAgain_ButtonPressed(ActionEvent event) throws Exception {
        if (event.getSource() == PlayAgainButton) {
            ControllerPlayerOneChooseMysteryWord myController = new ControllerPlayerOneChooseMysteryWord(data);
            FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogPlayerOneChooseWord.fxml"));
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
        MisteryWords.setText("Misterywords:\nPlayer " + data.getPlayerName1() + ": " + data.getHmGameLogic().getMysteryWord() +
                "\nPlayer " + data.getPlayerName2() + ": " + data.getHmGameLogicMP2().getMysteryWord());
        if (data.getHmWinner() == HangmanWinner.PlayerOne) {
            LabelWinnerInformation.setText("Congratulation " + data.getPlayerName1() + ", you won!");
            logger.info("Player: " + data.getPlayerName1() + "Won!");
        } else
            LabelWinnerInformation.setText("Congratulation " + data.getPlayerName2() + ", you won!");
        logger.info("Player: " + data.getPlayerName2() + "Won!");
    }
}

package minigames.windows.gui;

import minigames.hangman.business.logic.Hangman;
import minigames.hangman.business.logic.HangmanWinner;
import minigames.hangman.business.logic.Multiplayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerPlayerTwoChooseMysteryWord {

    private static final Logger logger = LogManager.getLogger(ControllerPlayerTwoChooseMysteryWord.class);

    private UserGameData data;
    @FXML
    private Button PlayerTwoChooseWordButton, GoBack_Button;
    @FXML
    private TextField TextField_chooseWord;
    @FXML
    private Label LabelPlayer2ChooseWord;

    public ControllerPlayerTwoChooseMysteryWord(UserGameData data) {
        this.data = data;
    }

    @FXML
    private void PlayerTwoChooseWord_ButtonPressed(ActionEvent event) throws IOException {
        boolean retVal = false;
        if (event.getSource() == PlayerTwoChooseWordButton) {
            if (!TextField_chooseWord.getText().isEmpty() && TextField_chooseWord.getText().length() < Hangman.getMaxMysteryWordLength()) {
                Pattern pattern = Pattern.compile("[a-z|A-Z]*");
                Matcher matcher = pattern.matcher(TextField_chooseWord.getText());
                if (matcher.matches()) {
                    retVal = true;
                    data.setHmGameLogic(new Multiplayer());
                    data.getHmGameLogic().initGame(TextField_chooseWord.getText());
                    data.setHmWinner(HangmanWinner.NoWinner);
                    ControllerHMMPlayerOne myControllerPlayer1 = new ControllerHMMPlayerOne(data);
                    ControllerHMMPlayerTwo myControllerPlayer2 = new ControllerHMMPlayerTwo(data);

                    FXMLLoader rootPlayer1 = new FXMLLoader(getClass().getClassLoader().getResource("DialogHMMultiplayerPlayer.fxml"));
                    FXMLLoader rootPlayer2 = new FXMLLoader(getClass().getClassLoader().getResource("DialogHMMultiplayerPlayer.fxml"));
                    rootPlayer1.setController(myControllerPlayer1);
                    rootPlayer2.setController(myControllerPlayer2);
                    Scene scenePlayer1 = new Scene(rootPlayer1.load());
                    Scene scenePlayer2 = new Scene(rootPlayer2.load());
                    data.setScenePlayer1(scenePlayer1);
                    data.setScenePlayer2(scenePlayer2);
                    Stage window = (Stage) PlayerTwoChooseWordButton.getScene().getWindow();
                    window.setScene(scenePlayer1);
                }
            }
            if (!retVal) {
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input Error");
                alert.setHeaderText("Please insert a word with max. " + Hangman.getMaxMysteryWordLength() + " letters!");
                logger.error("Input Error: " + "Please insert a word with max. " + Hangman.getMaxMysteryWordLength() + " letters!");
                alert.setResizable(false);
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void GoBack_ButtonPressed(ActionEvent event) throws IOException {
        if (event.getSource() == GoBack_Button) {
            ControllerDialogSelectGame myController = new ControllerDialogSelectGame(data);
            FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogSelectGame.fxml"));
            root.setController(myController);
            Scene scene = new Scene(root.load());
            Stage window = (Stage) GoBack_Button.getScene().getWindow();
            window.setScene(scene);
        }
    }

    @FXML
    private void initialize() {
        LabelPlayer2ChooseWord.setText("Player " + data.getPlayerName2() +
                "\nenter a Word for Player " + data.getPlayerName1());
    }
}
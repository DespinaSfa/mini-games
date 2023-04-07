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

public class ControllerPlayerOneChooseMysteryWord {

    private static final Logger logger = LogManager.getLogger(ControllerPlayerOneChooseMysteryWord.class);

    private UserGameData data;
    @FXML
    private Button PlayerOneChooseWordButton, GoBack_Button;
    @FXML
    private TextField TextField_chooseWord;
    @FXML
    private Label Label_Information;

    public ControllerPlayerOneChooseMysteryWord(UserGameData data) {
        this.data = data;
    }

    @FXML
    private void PlayerOneChooseWordButton_Pressed(ActionEvent event) throws Exception {
        boolean retVal = false;
        if (event.getSource() == PlayerOneChooseWordButton) {
            if (!TextField_chooseWord.getText().isEmpty() && TextField_chooseWord.getText().length() <= Hangman.getMaxMysteryWordLength()) {
                Pattern pattern = Pattern.compile("[a-z|A-Z]*");
                Matcher matcher = pattern.matcher(TextField_chooseWord.getText());
                if (matcher.matches()) {
                    retVal = true;
                    data.setHmGameLogicMP2(new Multiplayer());
                    data.getHmGameLogicMP2().initGame(TextField_chooseWord.getText());
                    data.setHmWinner(HangmanWinner.NoWinner);
                    ControllerPlayerTwoChooseMysteryWord myController = new ControllerPlayerTwoChooseMysteryWord(data);
                    FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogPlayerTwoChooseWord.fxml"));
                    root.setController(myController);
                    Scene scene = new Scene(root.load());
                    Stage window = (Stage) PlayerOneChooseWordButton.getScene().getWindow();
                    window.setScene(scene);
                }
            }
            if (!retVal) {
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input Error");
                alert.setHeaderText("Please insert a word with max. " + Hangman.getMaxMysteryWordLength() + " letters.");
                logger.error("Input Error - " + "Please insert a word with max. " + Hangman.getMaxMysteryWordLength() + " letters.");
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
        Label_Information.setText("Player " + data.getPlayerName1() +
                "\n please enter a Word for player " + data.getPlayerName2());
    }
}

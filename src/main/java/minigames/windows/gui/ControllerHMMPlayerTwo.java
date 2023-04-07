package minigames.windows.gui;

import minigames.hangman.business.logic.Hangman;
import minigames.hangman.business.logic.HangmanWinner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ControllerHMMPlayerTwo {

    private static final Logger logger = LogManager.getLogger(ControllerHMMPlayerTwo.class);

    private UserGameData data;

    @FXML
    private Button Button_ValidateMWCharacter, GoBack_Button;
    @FXML
    private Label LabelFieldInformation_PlayerName, misteryWordPlayer1, LevelCounter;
    @FXML
    private TextField TextField_MWCharacter;
    @FXML
    private ImageView ImageView_SMLevel1, ImageView_SMLevel2, ImageView_SMLevel3, ImageView_SMLevel4, ImageView_SMLevel5,
            ImageView_SMLevel6, ImageView_SMLevel7, ImageView_SMLevel8, ImageView_SMLevel9, ImageView_SMLevel10;

    public ControllerHMMPlayerTwo(UserGameData data) {
        this.data = data;
    }

    @FXML
    private void ValidateMWCharacter_ButtonPressed(ActionEvent event) throws Exception {
        if (event.getSource() == Button_ValidateMWCharacter) {
            if ((TextField_MWCharacter.getText().length() == 1) &&
                    ((TextField_MWCharacter.getText().charAt(0) >= 65 && TextField_MWCharacter.getText().charAt(0) <= 90) || // upper case characters
                            (TextField_MWCharacter.getText().charAt(0) >= 97 && TextField_MWCharacter.getText().charAt(0) <= 122))) { // lower case characters

                data.getHmGameLogicMP2().setMysteryWordCharacter(TextField_MWCharacter.getText());
                data.getHmGameLogicMP2().calculateMove();
                SetSnowmanVisible(data.getHmGameLogicMP2().getCurrentLevel());
                LevelCounter.setText("Level: " + data.getHmGameLogicMP2().getCurrentLevel() + "/" + Hangman.getLevelEnd());
                misteryWordPlayer1.setText(FormatResultWord(data.getHmGameLogicMP2().getResultWord()));
                TextField_MWCharacter.setText(""); // clear mistery word text input field for next user input
                if (data.getHmGameLogicMP2().getCurrentLevel() < Hangman.getLevelEnd() && !data.getHmGameLogicMP2().getResultWord().contains("_"))
                    data.setHmWinner(HangmanWinner.PlayerTwo);
                else if (data.getHmGameLogicMP2().getCurrentLevel() >= Hangman.getLevelEnd())
                    data.setHmWinner(HangmanWinner.PlayerOne);

                if (data.getHmWinner() != HangmanWinner.NoWinner) {
                    ControllerHMMultiplayerWinner myController = new ControllerHMMultiplayerWinner(data);
                    FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogWinnerHMMultiplayer.fxml"));
                    root.setController(myController);
                    Scene scene = new Scene(root.load());
                    Stage window = (Stage) Button_ValidateMWCharacter.getScene().getWindow();
                    window.setScene(scene);
                } else {
                    Stage window = (Stage) Button_ValidateMWCharacter.getScene().getWindow();
                    window.setScene(data.getScenePlayer1());
                }
            } else {
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input Error");
                alert.setHeaderText("Please insert only one letter from A to Z.");
                logger.error("Input error - User didn't input a valid letter");
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
        TextField_MWCharacter.setText(""); // clear mistery word character input field for next user input
        LabelFieldInformation_PlayerName.setText(data.getPlayerName2() + " it's your turn");
        misteryWordPlayer1.setText(FormatResultWord(data.getHmGameLogicMP2().getResultWord()));
        LevelCounter.setText("Level: " + data.getHmGameLogicMP2().getCurrentLevel() + "/" + Hangman.getLevelEnd());
        SetSnowmanVisible(0);
    }

    public String FormatResultWord(String inputWord) {
        String result = "";
        for (int i = 0; i < inputWord.length(); i++) {
            result += inputWord.charAt(i) + " ";
        }
        return result;
    }

    private void SetSnowmanVisible(int visibleSnowman) {
        ImageView_SMLevel1.setVisible(false);
        ImageView_SMLevel2.setVisible(false);
        ImageView_SMLevel3.setVisible(false);
        ImageView_SMLevel4.setVisible(false);
        ImageView_SMLevel5.setVisible(false);
        ImageView_SMLevel6.setVisible(false);
        ImageView_SMLevel7.setVisible(false);
        ImageView_SMLevel8.setVisible(false);
        ImageView_SMLevel9.setVisible(false);
        ImageView_SMLevel10.setVisible(false);

        switch (visibleSnowman) {
            case 0:
                ImageView_SMLevel1.setVisible(true);
                break;
            case 1:
                ImageView_SMLevel2.setVisible(true);
                break;
            case 2:
                ImageView_SMLevel3.setVisible(true);
                break;
            case 3:
                ImageView_SMLevel4.setVisible(true);
                break;
            case 4:
                ImageView_SMLevel5.setVisible(true);
                break;
            case 5:
                ImageView_SMLevel6.setVisible(true);
                break;
            case 6:
                ImageView_SMLevel7.setVisible(true);
                break;
            case 7:
                ImageView_SMLevel8.setVisible(true);
                break;
            case 8:
                ImageView_SMLevel9.setVisible(true);
                break;
            case 9:
                ImageView_SMLevel10.setVisible(true);
                break;
        }
    }
}
package minigames.windows.gui;

import minigames.hangman.business.logic.Difficulties;
import minigames.hangman.business.logic.SinglePlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerHMChooseGameMode {
    private UserGameData data;
    @FXML
    private Button EasyModeButton, MediumModeButton, HardModeButton;

    public ControllerHMChooseGameMode(UserGameData data) {
        this.data = data;
    }

    private void switchScene(String dialogName) throws IOException {
        ControllerHMPlayerOne myController = new ControllerHMPlayerOne(data);
        FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource(dialogName));
        root.setController(myController);
        Scene scene = new Scene(root.load());
        Stage window = (Stage) EasyModeButton.getScene().getWindow();
        window.setScene(scene);
    }

    @FXML
    private void EasyMode_ButtonPressed(ActionEvent event) throws Exception {
        if (event.getSource() == EasyModeButton) {
            data.setHmGameLogic(new SinglePlayer(Difficulties.EASY));
            switchScene("DialogHMMultiplayerPlayer.fxml");
        }
    }

    @FXML
    private void MediumMode_ButtonPressed(ActionEvent event) throws Exception {
        if (event.getSource() == MediumModeButton) {
            data.setHmGameLogic(new SinglePlayer(Difficulties.MEDIUM));
            switchScene("DialogHMMultiplayerPlayer.fxml");
        }
    }

    @FXML
    private void HardMode_ButtonPressed(ActionEvent event) throws Exception {
        if (event.getSource() == HardModeButton) {
            data.setHmGameLogic(new SinglePlayer(Difficulties.HARD));
            switchScene("DialogHMMultiplayerPlayer.fxml");
        }
    }
}
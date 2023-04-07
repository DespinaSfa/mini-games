package minigames.windows.gui;

import javafx.scene.Parent;
import minigames.minesweeper.business.logic.Difficulties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerBSChooseGameMode {

    private final UserGameData data;
    @FXML
    public Button EasyModeButton, MediumModeButton, HardModeButton;


    //'Constructor': User input data
    public ControllerBSChooseGameMode(UserGameData data) {
        this.data = data;
    }


    //"Easy"-Button pressed
    @FXML
    private void EasyMode_ButtonPressed(ActionEvent event) throws Exception {
        if (event.getSource() == EasyModeButton) {
            ChangeSceneDialogSelectGame("DialogPlayBeeSweeper.fxml",
                    Difficulties.EASY.ROW, Difficulties.EASY.COL, Difficulties.EASY.AMOUNT_OF_BOMBS);
        }
    }


    //"Medium"-Button pressed
    @FXML
    private void MediumMode_ButtonPressed(ActionEvent event) throws Exception {
        if (event.getSource() == MediumModeButton) {
            ChangeSceneDialogSelectGame("DialogPlayBeeSweeper.fxml",
                    Difficulties.MEDIUM.ROW, Difficulties.MEDIUM.COL, Difficulties.MEDIUM.AMOUNT_OF_BOMBS);
        }
    }


    //"Hard"-Button pressed
    @FXML
    private void HardMode_ButtonPressed(ActionEvent event) throws Exception {
        if (event.getSource() == HardModeButton) {
            ChangeSceneDialogSelectGame("DialogPlayBeeSweeper.fxml",
                    Difficulties.HARD.ROW, Difficulties.HARD.COL, Difficulties.HARD.AMOUNT_OF_BOMBS);
        }
    }


    //create GridPane with Buttons and start game
    private void ChangeSceneDialogSelectGame(String dialogName, int row, int col, int amountOfBombs) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(dialogName));
        Parent root = loader.load();
        ControllerBSGameMode controller = loader.getController();
        controller.init(data, row, col, amountOfBombs);

        Scene scene = new Scene(root);

        Stage window = (Stage) EasyModeButton.getScene().getWindow();

        window.setScene(scene);
    }
}
package minigames.windows.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerBSWinnerScene {

    private final UserGameData data;
    @FXML
    private Button GoBackButton;


    //winnerscene if user wins game
    public ControllerBSWinnerScene(UserGameData data) {this.data = data;}


    //Press "GoBack"-Button to get back to the "DialogSelectGame" where you can choose again between the two games
    @FXML private void GoBack_ButtonPressed(ActionEvent event) throws Exception {
        if (event.getSource() == GoBackButton) {
            ControllerDialogSelectGame myController = new ControllerDialogSelectGame(data);
            FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogSelectGame.fxml"));
            root.setController(myController);
            Scene scene = new Scene(root.load());
            Stage window = (Stage) GoBackButton.getScene().getWindow();
            window.setScene(scene);
        }
    }
}



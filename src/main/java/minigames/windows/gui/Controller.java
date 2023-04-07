package minigames.windows.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    private final UserGameData data;
    @FXML private Button ButtonFxId;

    public Controller(UserGameData data) {
        this.data = data;
    }

    @FXML private void ClickHere_ButtonPressed (ActionEvent event) throws Exception {
        if (event.getSource() == ButtonFxId) {
            ControllerDialogSelectGame myController = new ControllerDialogSelectGame(data);
            FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogSelectGame.fxml"));
            root.setController(myController);
            Scene scene = new Scene(root.load());
            Stage window = (Stage) ButtonFxId.getScene().getWindow();
            window.setScene(scene);
        }
    }
}

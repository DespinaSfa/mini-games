package minigames.windows.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import minigames.hangman.business.logic.UserName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;


public class ControllerMultiplayerChooseUsername {

    private static final Logger logger = LogManager.getLogger(ControllerSinglePlayerChooseUsername.class);

    private UserGameData data;
    @FXML
    private Button ChooseWordButton, GoBack_Button;
    @FXML
    private ComboBox<String> ComboBox_UserNamesPlayer1, ComboBox_UserNamesPlayer2;

    public ControllerMultiplayerChooseUsername(UserGameData data) {
        this.data = data;
    }


    @FXML
    private void initialize() {
        data.getUserNames().sort(Comparator.comparing(UserName::getUserName));
        data.getUserNames().stream().forEach((name) -> {
            ComboBox_UserNamesPlayer1.getItems().add(name.getUserName());
            ComboBox_UserNamesPlayer2.getItems().add(name.getUserName());
        });
    }

    @FXML
    private void ChooseWord_ButtonPressed(ActionEvent event) throws IOException {
        boolean userNamesValid = false;
        if (event.getSource() == ChooseWordButton) {

            if (HMUtilityClass.checkUserNameValid(ComboBox_UserNamesPlayer1.getValue()) &&
                    HMUtilityClass.checkUserNameValid(ComboBox_UserNamesPlayer2.getValue()) &&
                    !ComboBox_UserNamesPlayer1.getValue().equals(ComboBox_UserNamesPlayer2.getValue())
            ) {
                userNamesValid = true;
                data.setPlayerName1(ComboBox_UserNamesPlayer1.getValue());
                data.setPlayerName2(ComboBox_UserNamesPlayer2.getValue());

                int userNamesCount = data.getUserNames().size();
                if (!data.getUserNames().parallelStream().anyMatch(s -> (data.getPlayerName1().equals(s.getUserName())))) {
                    data.getUserNames().add(new UserName(ComboBox_UserNamesPlayer1.getValue()));
                }
                if (!data.getUserNames().parallelStream().anyMatch(s -> (data.getPlayerName2().equals(s.getUserName())))) {
                    data.getUserNames().add(new UserName(ComboBox_UserNamesPlayer2.getValue()));
                }

                // write back user names to harddrive
                if (data.getUserNames().size() != userNamesCount) {
                    FileWriter fw = new FileWriter("src/main/resources/UserNames.txt");

                    data.getUserNames().stream().forEach(name -> {
                        try {
                            fw.write(name.getUserName() + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    fw.close();
                }
            }

            if (!userNamesValid) {
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input Error");
                alert.setHeaderText("Please insert two different valid Usernames with letters from A to Z,\nnumbers 0 to 9 or \"_\"" +
                        " which contains only " + UserName.getUserNameMaxLength() + " letters.\nThank you!");
                logger.error("Input Error: User did not choose a valid name for one of the multi players");
                alert.setResizable(false);
                alert.showAndWait();
            } else {
                ControllerPlayerOneChooseMysteryWord myController = new ControllerPlayerOneChooseMysteryWord(data);
                FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogPlayerOneChooseWord.fxml"));
                root.setController(myController);
                Scene scene = new Scene(root.load());
                Stage window = (Stage) ChooseWordButton.getScene().getWindow();
                window.setScene(scene);
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
}

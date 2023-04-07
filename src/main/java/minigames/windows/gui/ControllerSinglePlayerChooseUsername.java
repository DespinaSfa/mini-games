package minigames.windows.gui;

import minigames.hangman.business.logic.SelectedGame;
import minigames.hangman.business.logic.UserName;

import java.io.FileWriter;
import java.util.Comparator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ControllerSinglePlayerChooseUsername {

    private static final Logger logger = LogManager.getLogger(ControllerSinglePlayerChooseUsername.class);

    private UserGameData data;

    @FXML
    private Button StartGameButton;

    @FXML
    private ComboBox<String> ComboBox_UserNames;

    public ControllerSinglePlayerChooseUsername(UserGameData data) {
        this.data = data;
    }

    @FXML
    private void initialize() {
        data.getUserNames().sort(Comparator.comparing(UserName::getUserName));
        data.getUserNames().stream().forEach((name) -> {
            ComboBox_UserNames.getItems().add(name.getUserName());
        });
    }

    @FXML
    private void StartGame_ButtonPressed(ActionEvent event) throws IOException {
        boolean userNamesValid = false;
        if (event.getSource() == StartGameButton) {

            if (HMUtilityClass.checkUserNameValid(ComboBox_UserNames.getValue())) {
                userNamesValid = true;
                data.setPlayerName1(ComboBox_UserNames.getValue());

                int userNamesCount = data.getUserNames().size();
                if (!data.getUserNames().parallelStream().anyMatch(s -> (data.getPlayerName1().equals(s.getUserName())))) {
                    data.getUserNames().add(new UserName(ComboBox_UserNames.getValue()));
                }

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
            } else {
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input Error");
                alert.setHeaderText("Please insert a valid Username with letters from A to Z,\nnumbers 0-9 or \"_\"" +
                        " which contains only " + UserName.getUserNameMaxLength() + " letters. Thank you!");
                logger.error("Input Error - User didn't insert a valid Username with letters from A to Z");
                alert.setResizable(false);
                alert.showAndWait();
            }
            if (userNamesValid) {

                if (data.getGameSelection() == SelectedGame.HangmanSinglePlayer) {
                    ControllerHMChooseGameMode myController = new ControllerHMChooseGameMode(data);
                    FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogHMChooseGameMode.fxml"));
                    root.setController(myController);
                    Scene scene = new Scene(root.load());
                    Stage window = (Stage) StartGameButton.getScene().getWindow();
                    window.setScene(scene);
                } else {
                    ControllerBSChooseGameMode myController = new ControllerBSChooseGameMode(data);
                    FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogBSChooseGameMode.fxml"));
                    root.setController(myController);
                    Scene scene = new Scene(root.load());
                    Stage window = (Stage) StartGameButton.getScene().getWindow();
                    window.setScene(scene);
                }
            }
        }
    }
}

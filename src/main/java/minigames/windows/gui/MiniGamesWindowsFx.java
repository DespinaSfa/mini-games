package minigames.windows.gui;

import minigames.hangman.business.logic.UserName;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MiniGamesWindowsFx extends Application  {

    public void configureGame() {}
    public void playGame() {};
    public boolean playAgain() { return false;};

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<UserName> userNames = new ArrayList<>();
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/UserNames.txt"));
        lines.stream().forEach((name) -> {userNames.add(new UserName(name));});

        UserGameData miniGameUserData = new UserGameData(userNames);

        Controller myController = new Controller(miniGameUserData);
        //FMXLoader loads an object hierarchie from a XML document
        //The arg of the constructor FMXLoader returns an URL object for the given ressource
        FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogHomescreen.fxml"));
        root.setController(myController);
        Scene scene = new Scene(root.load());

        primaryStage.setTitle("MiniGames by Shaniquaaaa");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

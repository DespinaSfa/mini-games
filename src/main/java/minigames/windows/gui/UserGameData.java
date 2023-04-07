package minigames.windows.gui;

import minigames.hangman.business.logic.HangmanWinner;
import minigames.hangman.business.logic.Hangman;
import minigames.hangman.business.logic.SelectedGame;
import minigames.hangman.business.logic.UserName;
import javafx.scene.Scene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserGameData {
    private static final Logger logger = LogManager.getLogger(UserGameData.class);

    private String PlayerName1;
    private String PlayerName2;
    private SelectedGame GameSelection;
    private Hangman HmGameLogic;
    private Hangman HmGameLogicMP2;
    private Scene scenePlayer2;
    private Scene scenePlayer1;
    private List<UserName> userNames;
    private HangmanWinner HmWinner;

    public UserGameData(List<UserName> userNames) {
        PlayerName1 = "Unknown";
        PlayerName2 = "Unknown";
        GameSelection = SelectedGame.NoGameSelected;
        this.userNames = userNames;
        HmWinner = HangmanWinner.NoWinner;
    }

    public String getPlayerName1() {
        return PlayerName1;
    }

    public void setPlayerName1(String playerName1) {
        PlayerName1 = playerName1;
        logger.info("Player 1: " + getPlayerName1());
    }

    public String getPlayerName2() {
        return PlayerName2;
    }

    public void setPlayerName2(String playerName2) {
        PlayerName2 = playerName2;
        logger.info("Player 2: " + getPlayerName2());
    }

    public SelectedGame getGameSelection() {
        return GameSelection;
    }

    public void setGameSelection(SelectedGame gameSelection) {
        GameSelection = gameSelection;
    }

    public Hangman getHmGameLogic() {return HmGameLogic;}

    public void setHmGameLogic(Hangman hmGameLogic) {HmGameLogic = hmGameLogic;}

    public Hangman getHmGameLogicMP2() {return HmGameLogicMP2;}

    public void setHmGameLogicMP2(Hangman hmGameLogicMP2) {HmGameLogicMP2 = hmGameLogicMP2;}

    public Scene getScenePlayer2() {return scenePlayer2;}

    public void setScenePlayer2(Scene scene) {this.scenePlayer2 = scene;}

    public Scene getScenePlayer1() {return scenePlayer1;}

    public void setScenePlayer1(Scene scene) {this.scenePlayer1 = scene;}

    public HangmanWinner getHmWinner() {return HmWinner;}

    public void setHmWinner(HangmanWinner hmWinner) {HmWinner = hmWinner;}

    public List<UserName> getUserNames() {
        return userNames;
    }
}
package minigames.windows.gui;

import minigames.hangman.ui.HangmanBasicUI;
import javafx.application.Application;

public class HangmanWindows implements HangmanBasicUI {
    String[] args;
    public void configureGame(String[] args) {
        this.args = args;
    }
    public void playGame() {
        Application.launch(MiniGamesWindowsFx.class, this.args); // This function will call MiniGamesWindowsFx
    }
}

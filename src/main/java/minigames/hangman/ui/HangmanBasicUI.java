package minigames.hangman.ui;

// This is the base class for any UI. The console UI and the Windows GUI will be derived
// from this base class.
public interface HangmanBasicUI {
    void configureGame(String[] args);
    void playGame();
}
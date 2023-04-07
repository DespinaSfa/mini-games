package minigames.hangman.console.ui;

//Class to check if input from user is valid
public class InputException extends Exception {
    public InputException() {
        super("Please enter a number from 0 to 2");
    }
}
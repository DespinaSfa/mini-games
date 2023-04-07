package minigames.hangman.console.ui;

import minigames.hangman.business.logic.Difficulties;
import minigames.hangman.business.logic.Hangman;
import minigames.hangman.business.logic.Multiplayer;
import minigames.hangman.business.logic.SinglePlayer;
import minigames.hangman.ui.HangmanBasicUI;
import minigames.hangman.business.logic.SelectedGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class HangmanConsole implements HangmanBasicUI {
    private static final Logger logger = LogManager.getLogger(HangmanConsole.class);

    protected Hangman gameBusinessLogic;
    protected SelectedGame playerMode;

    public void configureGame(String[] args) {
        playerMode = getUserInputPlayerMode();
        if (playerMode == SelectedGame.HangmanSinglePlayer) {
            gameBusinessLogic = new SinglePlayer(level());
        } else
            gameBusinessLogic = new Multiplayer();
    }

    public void playGame() {
        do {
            if (playerMode == SelectedGame.HangmanSinglePlayer) {
                try {
                    gameBusinessLogic.initGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                do {
                    String userInput = getInputLetter();
                    gameBusinessLogic.setMysteryWordCharacter(userInput);
                    gameBusinessLogic.calculateMove(); // play one round with given input and calculate new game state
                    System.out.println(gameBusinessLogic.getResultWord() + " Level: " + gameBusinessLogic.getCurrentLevel());
                } while (gameBusinessLogic.getCurrentLevel() < gameBusinessLogic.getLevelEnd() && gameBusinessLogic.getResultWord().contains("_"));

                if (gameBusinessLogic.getCurrentLevel() >= gameBusinessLogic.getLevelEnd()) {
                    logger.info("User Lost!");
                    System.out.println("You LOST. Game over.");
                } else {
                    logger.info("User won!");
                    System.out.println("Congratulations, you WON.");
                }
            }
            if (playerMode == SelectedGame.HangmanMultiPlayer) {
                // Not implemented by intention! The console in general is not part of the task. We implemented it for
                // demonstration of our architecture!
                // Place code for multiplayer here
            }
        }
        while (playAgain());
    }

    public boolean playAgain() {
        boolean retVal = false;
        if (getUserInputRestartOrQuit().equals("R"))
            retVal = true;
        return retVal;
    }

    private SelectedGame getUserInputPlayerMode() {
        System.out.println("Please choose game mode: ");
        System.out.println("0 - Single player game");
        System.out.println("1 - Multiplayer game");
        Scanner input = new Scanner(System.in);
        String inputPlayerMode = input.nextLine();

        if (inputPlayerMode.equals("0"))        // 0 = Single Player Mode
            return SelectedGame.HangmanSinglePlayer;
        else
            return SelectedGame.HangmanMultiPlayer;
    }


    /* If user is A SINGLE PLAYER he needs to choose a difficulty level e get a word based on this level
       this class takes a difficulty level as parameter and selects a word inside the chosen level
       In case of a SINGLE PLAYER - User will choose a level */
    private Difficulties level() {
        System.out.println("Please choose a level: ");
        System.out.println("0 - EASY");
        System.out.println("1 - MEDIUM");
        System.out.println("2 - HARD");

        Scanner input = new Scanner(System.in);
        String inputLevel;
        boolean isValidInput = false;
        do {
            inputLevel = input.nextLine();
            isValidInput = checkInput(inputLevel);
        } while (!isValidInput);

        switch (Integer.parseInt(inputLevel)) {
            case 0:
                return (Difficulties.EASY);
            case 1:
                return (Difficulties.MEDIUM);
            default:
                return (Difficulties.HARD);
        }
    }


    // helper function - only return true if user input does throw any exceptions
    private boolean checkInput(String userInput) {
        int validInput;
        try {
            validInput = Integer.parseInt(userInput);
            checkIntInput(validInput);
        } catch (NumberFormatException nfe) {
            return false;
        } catch (InputException iex) {
            return false;
        }
        return true;
    }

    // helper function - check if int given by user lies within the desired range
    private void checkIntInput(int userInput) throws InputException {
        if (userInput < 0 || userInput > 2) {
            throw new InputException();
        }
    }

    // Ask for input letter and check if input is a letter from 'A' to 'Z'
    public String getInputLetter() {
        Scanner input = new Scanner(System.in);
        String guessInput = "";
        while (guessInput.length() != 1 || guessInput.charAt(0) < 65 || guessInput.charAt(0) > 90) {
            guessInput = input.nextLine().toUpperCase();
        }
        logger.info("Input from user is valid");
        return guessInput;
    }

    private String getUserInputRestartOrQuit() {
        Scanner input = new Scanner(System.in);
        String userResponse = "";
        while ((userResponse.length() != 1 || userResponse.charAt(0) != 81) && (userResponse.length() != 1 || userResponse.charAt(0) != 82)) {
            System.out.println("Would you like to replay or quit? Please enter: ");
            System.out.println("Quit = q \nReplay = r");
            userResponse = input.nextLine().toUpperCase();
        }
        logger.info("User would like to: " + userResponse + " game");
        return userResponse;
    }
}
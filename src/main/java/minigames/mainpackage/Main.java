package minigames.mainpackage;

import minigames.hangman.ui.HangmanBasicUI;
import minigames.hangman.ui.UIFactory;
import minigames.hangman.ui.UIType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        HangmanBasicUI letsPlay;
        UIType uiType = UIType.WINDOWS;

        // parsing program arguments
        if (args.length >= 2) {
            if (args[0].compareToIgnoreCase("-ui") == 0) {
                if (args[1].compareToIgnoreCase("console") == 0) {
                    uiType = UIType.CONSOLE;
                }
            }
        }
        logger.info("Starting game");
        letsPlay = UIFactory.createUI(uiType);
        letsPlay.configureGame(args);
        letsPlay.playGame();
    }
}

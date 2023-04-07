package minigames.hangman.business.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SinglePlayer extends Hangman {

    private static final Logger logger = LogManager.getLogger(SinglePlayer.class);

    Words wordlists = new Words();
    private Difficulties difficulty;

    public SinglePlayer(Difficulties difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulties getDifficulty() {
        return difficulty;
    }

    public void initGame() {
        String mysteryWord = wordlists.generateWord(difficulty);
        logger.info("Mystery word of length {} generated", mysteryWord.length());
        try {
            super.initGame(mysteryWord);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}




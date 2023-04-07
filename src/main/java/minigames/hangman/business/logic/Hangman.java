package minigames.hangman.business.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Hangman {
    private String mysteryWord;
    private String resultWord;
    private String searchCharacter;
    private int currentLevel;

    public Hangman() {
        currentLevel = 0;
        mysteryWord = "";
        searchCharacter = "";
        resultWord = "";
    }

    private static final Logger logger = LogManager.getLogger(Hangman.class);

    public int getCurrentLevel() {
        return currentLevel;
    }

    public static int getLevelEnd() {
        return 10;
    }

    public String getResultWord() {
        return resultWord;
    }

    public static int getMaxMysteryWordLength() {
        return 20;
    }

    public String getMysteryWord() {
        return mysteryWord;
    }

    public abstract void initGame();

    public void initGame(String mysteryWord) {
        this.currentLevel = 0;
        this.mysteryWord = mysteryWord.toUpperCase();
        this.searchCharacter = "";
        this.resultWord = "";
        if (mysteryWord.length() <= getMaxMysteryWordLength() && !mysteryWord.isEmpty()) {
            Pattern pattern = Pattern.compile("[A-Z]*");
            Matcher matcher = pattern.matcher(this.mysteryWord);
            if (!matcher.matches()) {
                logger.error("Illegal mystery word");
                System.exit(-1);
            }
            for (int i = 0; i < this.mysteryWord.length(); i++)
                this.resultWord = this.resultWord.concat("_");
        }
        logger.info("Mystery word set; Game initialized");
    }

    public boolean setMysteryWordCharacter(String character) {
        boolean retVal = false;
        if (character.length() == 1) {
            Pattern pattern = Pattern.compile("[a-z|A-Z]*");
            Matcher matcher = pattern.matcher(character);
            if (matcher.matches()) {
                searchCharacter = character.toUpperCase(Locale.ROOT);
                retVal = true;
            }
        }
        return retVal;
    }

    // Calculate new level of one round of the game after all user input is collected.
    public boolean calculateMove() {
        boolean retVal = false;
        if (!this.searchCharacter.isEmpty() && currentLevel < getLevelEnd() && this.resultWord.contains("_")) {
            boolean found = false;
            char[] res = resultWord.toCharArray();
            for (int i = 0; i < mysteryWord.length(); i++)
            {
                if (mysteryWord.charAt(i) == searchCharacter.charAt(0)) {
                    res[i] = searchCharacter.charAt(0);
                    found = true;
                }
            }
            resultWord = String.valueOf(res);
            if (!found) currentLevel++;
            retVal = true;
        }
        return retVal;
    }
}
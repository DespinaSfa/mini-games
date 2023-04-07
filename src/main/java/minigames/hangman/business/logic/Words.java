package minigames.hangman.business.logic;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Words {

    private ArrayList<String> easyWords;
    private ArrayList<String> mediumWords;
    private ArrayList<String> hardWords;

    public Words() {
        // Initialize mystery word lists
        this.easyWords = new ArrayList(Arrays.asList("pencil", "fitness", "colour", "music"));
        this.mediumWords = new ArrayList(Arrays.asList("subway", "kayak", "length", "minigames", "hangman"));
        this.hardWords = new ArrayList(Arrays.asList("oxygen", "physics", "squirrel", "cynicism"));
    }

    // Returns a mystery word according to its difficulty
    public String generateWord(Difficulties level) {
        Random random = new Random();
        int randomNumber;

        if (level == Difficulties.EASY) {
            randomNumber = random.nextInt(this.getEasyWords().size() - 1);
            return this.easyWords.get(randomNumber).toUpperCase();
        } else if (level == Difficulties.MEDIUM) {
            randomNumber = random.nextInt(this.getMediumWords().size() - 1);
            return this.mediumWords.get(randomNumber).toUpperCase();
        } else {
            randomNumber = random.nextInt(this.getHardWords().size() - 1);
            return this.hardWords.get(randomNumber).toUpperCase();
        }
    }

    public ArrayList<String> getEasyWords() {
        return easyWords;
    }
    public ArrayList<String> getMediumWords() {
        return mediumWords;
    }
    public ArrayList<String> getHardWords() {
        return hardWords;
    }

}
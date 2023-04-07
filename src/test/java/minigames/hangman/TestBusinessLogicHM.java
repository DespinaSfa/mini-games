package minigames.hangman;

import minigames.hangman.business.logic.Difficulties;
import minigames.hangman.business.logic.Hangman;
import minigames.hangman.business.logic.SinglePlayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBusinessLogicHM {

    SinglePlayer game;
    //Executed before each test
    @Before
    public void setUpTests() {
        game = new SinglePlayer(Difficulties.HARD);
    }

    @Test
    public void testMaxLevels() {
        assertEquals(10, Hangman.getLevelEnd());
    }

    @Test
    public void testMaxMisteryWordLength() {
        assertEquals(20, Hangman.getMaxMysteryWordLength());
    }

    @Test
    public void testMaxMisteryWord() {
        game.initGame("ABCDEFGHIJKLMNOPQRST");
        assertEquals("____________________", game.getResultWord());
    }

    @Test
    public void testCalculateMove_OutOfLevel() {
        game.initGame("ABC");
        for (int loop=0; loop < Hangman.getLevelEnd(); loop++) {
            game.setMysteryWordCharacter("d");
            game.calculateMove();
        }
        assertEquals(false, game.calculateMove());
    }

    @Test
    public void testCalculateMove_ContainLetter() {
        game.initGame("Home");
        game.setMysteryWordCharacter("o");
        assertEquals(true, game.calculateMove());

    }

    @Test
    public void testCalculateMove_ContainLetterUpdateWord() {
        game.initGame("Home");
        //game.repromptUserInput("m");
        game.setMysteryWordCharacter("m");
        game.calculateMove();
        assertEquals("__M_", game.getResultWord());
    }

    @Test
    public void testCalculateMove_DoesNotContainLetter() {
        game.initGame("Home");
        //game.repromptUserInput("a");
        game.setMysteryWordCharacter("a");
        game.calculateMove();
        assertEquals("____", game.getResultWord());
    }
}

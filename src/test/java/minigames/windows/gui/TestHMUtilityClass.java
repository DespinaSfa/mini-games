package minigames.windows.gui;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHMUtilityClass {

    @Test
    public void testIsValidLetter_Number() {
        assertEquals(false, HMUtilityClass.checkValidCharacter("5"));
    }

    @Test
    public void testIsValidLetter_LowerCase() {
        assertEquals(true, HMUtilityClass.checkValidCharacter("a"));
    }

    @Test
    public void testIsValidLetter_DifChar() {
        assertEquals(false, HMUtilityClass.checkValidCharacter("@"));
    }

    @Test
    public void testIsValidLetter_MoreChars() {
        assertEquals(false, HMUtilityClass.checkValidCharacter("abc"));
    }
}

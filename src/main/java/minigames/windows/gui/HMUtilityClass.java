package minigames.windows.gui;

import minigames.hangman.business.logic.UserName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HMUtilityClass {

    static public boolean checkUserNameValid(String name) {
        boolean retVal = false;
        if (name != null && !name.isEmpty() && name.length() <= UserName.getUserNameMaxLength()) {
            Pattern pattern = Pattern.compile("^[a-z|A-Z][a-z|A-Z|0-9|_]*");
            Matcher matcher = pattern.matcher(name);

            if (matcher.matches()) {
                retVal = true;
            }
        }
        return retVal;
    }

    // Check if the String has only one character and contains only characters for a-z or A-Z
    static public boolean checkValidCharacter(String word) {
        boolean retval = false;
        if ((word.length() == 1) && ((word.charAt(0) >= 65 && word.charAt(0) <= 90) || // Upper case letters
                (word.charAt(0) >= 97 && word.charAt(0) <= 122))) {              // Lower case letters
            retval = true;
        }
        return retval;
    }
}

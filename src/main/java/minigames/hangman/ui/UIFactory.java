package minigames.hangman.ui;

import minigames.hangman.console.ui.HangmanConsole;
import minigames.windows.gui.HangmanWindows;

public class UIFactory {
    public static HangmanBasicUI createUI(UIType uiType) {
        HangmanBasicUI retVal;
        switch (uiType) {
            case CONSOLE:
                retVal = new HangmanConsole();
                break;
            default:
                retVal = new HangmanWindows();
        }
        return retVal;
    }
}

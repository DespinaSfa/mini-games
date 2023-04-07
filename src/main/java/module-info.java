module MiniGames {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.logging.log4j;
    requires javafx.media;
    requires org.testng;

    opens minigames.mainpackage;
    opens minigames.hangman.business.logic;
    exports minigames.windows.gui;
    exports minigames.hangman.business.logic;
    opens minigames.windows.gui;
    exports minigames.minesweeper.business.logic to javafx.graphics;
    opens minigames.minesweeper.timer;
    //exports minigames.minesweeper to javafx.graphics;
}

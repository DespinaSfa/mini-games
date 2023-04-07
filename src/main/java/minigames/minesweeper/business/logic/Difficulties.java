package minigames.minesweeper.business.logic;

public enum Difficulties {
    EASY(9, 9, 10),
    MEDIUM(16, 16, 40),
    HARD(30, 16, 99),
    ;

    public final int ROW;
    public final int COL;
    public final int AMOUNT_OF_BOMBS;

    Difficulties(int row, int col, int amount_of_bombs) {
        this.ROW = row;
        this.COL = col;
        this.AMOUNT_OF_BOMBS = amount_of_bombs;
    }
}

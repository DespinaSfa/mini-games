package minigames.minesweeper;

import minigames.minesweeper.business.logic.MineSweeper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBusinessLogicMS {


    //Checks if parameters are set correct
    @Test
    public void gameBoardSetup() {
        int setRow = 9, setCol = 9, setAmountOfBombs = 10;

        MineSweeper test = new MineSweeper(setRow, setCol, setAmountOfBombs);

        assertEquals(test.getRow(), setRow);
        assertEquals(test.getCol(), setCol);
        assertEquals(test.getAmountOfBombs(), setAmountOfBombs);
    }



    //Checks if standard settings of a tile are right
    @Test
    public void tileSetup() {
        MineSweeper test = new MineSweeper(2, 2, 0);

        assertFalse(test.gameBoard[0][0].getFlag());
        assertFalse(test.gameBoard[0][0].getClickedTile());
        assertFalse(test.gameBoard[0][0].getBomb());
        assertEquals(test.gameBoard[0][0].getNearbyBombs(), 0);
        assertEquals(test.gameBoard[0][0].getX(), 0);
        assertEquals(test.gameBoard[0][0].getY(), 0);
    }



    //Tests placeBombs()-Method if the number of random placed bombs is right
    @Test
    public void numberOfBombs() {
        MineSweeper test = new MineSweeper(5, 5, 20);
        test.placeBombs();

        int bombCount = 0;
        for (int x = 0; x < test.getRow(); x++) {
            for (int y = 0; y < test.getCol(); y++) {
                if(test.gameBoard[x][y].getBomb()) {
                    bombCount++;
                }
            }
        }
        assertEquals(test.getAmountOfBombs(), bombCount, "must be the same");
    }



    //Tests setNumberNearbyBombs()-Method if random placed bombs have tile number 9
    @Test
    public void numberBombNine() {
        MineSweeper test = new MineSweeper(9, 9, 15);
        test.placeBombs();
        test.setNumberNearbyBombs();

        for (int x = 0; x < test.getRow(); x++) {
            for (int y = 0; y < test.getCol(); y++) {
                if(test.gameBoard[x][y].getBomb()) {
                    assertEquals(test.gameBoard[x][y].getNearbyBombs(), 9);
                }
            }
        }
    }



    //Tests setNumberNearbyBombs()-Method if tiles around bombs have correct nearby-bomb-Number
    @Test
    public void numberTile() {
        MineSweeper test = new MineSweeper(3, 3, 2);
        test.gameBoard[0][0].setBomb(true);
        test.gameBoard[0][1].setBomb(true);
        test.setNumberNearbyBombs();

        assertEquals(test.gameBoard[0][0].getNearbyBombs(), 9, "bomb-tile");
        assertEquals(test.gameBoard[0][1].getNearbyBombs(), 9, "bomb-tile");
        assertEquals(test.gameBoard[0][2].getNearbyBombs(), 1, "nearby one bomb");
        assertEquals(test.gameBoard[1][0].getNearbyBombs(), 2, "nearby two bombs");
        assertEquals(test.gameBoard[1][1].getNearbyBombs(), 2, "nearby two bombs");
        assertEquals(test.gameBoard[1][2].getNearbyBombs(), 1, "nearby one bomb");
        assertEquals(test.gameBoard[2][0].getNearbyBombs(), 0, "nearby no bomb");
        assertEquals(test.gameBoard[2][1].getNearbyBombs(), 0, "nearby no bomb");
        assertEquals(test.gameBoard[2][2].getNearbyBombs(), 0, "nearby no bomb");
    }



    /*Tests choseTile() -> checkTileNumberZero()-Method if zero tiles around clicked tile
      are also clicked and others don't
     */
    @Test
    public void checkZero() {
        MineSweeper test = new MineSweeper(4, 4, 2);
        test.gameBoard[0][3].setBomb(true);
        test.gameBoard[2][2].setBomb(true);
        test.setNumberNearbyBombs();
        test.checkTileNumberZero(0,0);

        while (test.checkZero.size() > 0) {
            test.checkZero.remove(0);
            test.checkZero.remove(0);
            test.inputCount -= 2;
            test.checkTileNumberZero(test.listX, test.listY);
        }

        assertTrue(test.gameBoard[0][0].getClickedTile());
        assertTrue(test.gameBoard[0][1].getClickedTile());
        assertTrue(test.gameBoard[0][2].getClickedTile());
        assertTrue(test.gameBoard[1][0].getClickedTile());
        assertTrue(test.gameBoard[1][1].getClickedTile());
        assertTrue(test.gameBoard[1][2].getClickedTile());
        assertTrue(test.gameBoard[2][0].getClickedTile());
        assertTrue(test.gameBoard[2][1].getClickedTile());
        assertTrue(test.gameBoard[3][0].getClickedTile());
        assertTrue(test.gameBoard[3][1].getClickedTile());

        assertFalse(test.gameBoard[0][3].getClickedTile());
        assertFalse(test.gameBoard[1][3].getClickedTile());
        assertFalse(test.gameBoard[2][2].getClickedTile());
        assertFalse(test.gameBoard[2][3].getClickedTile());
        assertFalse(test.gameBoard[3][2].getClickedTile());
        assertFalse(test.gameBoard[3][3].getClickedTile());
    }



    /*Tests placeFlag()-Method
     * -> clicked Tile - no flag possible
     */
    @Test
    public void flag() {
        MineSweeper test = new MineSweeper(5, 5, 0);

        test.gameBoard[0][0].setClickedTile(true);
        test.placeFlag(0,0);
        test.placeFlag(1,1);

        assertFalse(test.gameBoard[0][0].getFlag(), "clicked Tile can't have flag");
        assertTrue(test.gameBoard[1][1].getFlag());
    }
}





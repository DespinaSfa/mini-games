package minigames.minesweeper.business.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


public class MineSweeper {

    private static final Logger logger = LogManager.getLogger(MineSweeper.class);

    //SETUP
    public Tile[][] gameBoard;       //game-board
    private int AMOUNT_OF_BOMBS;      //bombs on game-board
    public int ROW;                  //rows
    public int COL;                  //columns
    public int inputCount = 0;       //for checking nearby zeros
    public int bombCount;

    public ArrayList<Integer> checkZero = new ArrayList(); //for checking nearby zeros


    /* Constructor creates:
     * GameBoard for every object
     * Parameters x, y, bomb, nearbyBombs, flag, clickedTile for every Tile inside the GameBoard
     */
    public MineSweeper() {
    }


    /* Constructor creates:
     * GameBoard for every object
     * Sets Parameter ROW, COL and AMOUNT_OF_BOMBS
     * Parameters x, y, bomb, nearbyBombs, flag, clickedTile for every Tile inside the GameBoard
     * For test purposes
     */
    public MineSweeper(int row, int col, int amountOfBombs) {
        this.ROW = row;
        this.COL = col;
        this.AMOUNT_OF_BOMBS = amountOfBombs;

        gameBoard = new Tile[ROW][COL];
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                gameBoard[x][y] = new Tile(x, y, false, 0, false, false);
            }
        }
    }



    public final void createGameBoard(int row, int col, int amountOfBombs) {
        this.ROW = row;
        this.COL = col;
        this.AMOUNT_OF_BOMBS = amountOfBombs;

        gameBoard = new Tile[ROW][COL];
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                gameBoard[x][y] = new Tile(x, y, false, 0, false, false);
            }
        }
    }



    //Placing the bombs randomly
    public final void placeBombs() {
        for (int i = 0; i < AMOUNT_OF_BOMBS; i++) {
            placeBomb();
        }
    }

    public void placeBomb() {

        int x = (int) Math.floor(Math.random() * ROW);  //x = random number from 0 to < COL
        int y = (int) Math.floor(Math.random() * COL); //y = random number from 0 to < ROW

        //checks whether there is a bomb in the specific tile and places one if not
        if (!gameBoard[x][y].getBomb()) {
            gameBoard[x][y].setBomb(true);
        } else {
            placeBomb();    //the process continues until all available bombs are placed
        }
    }



    //Sets number of Bombs nearby a Tile
    public final void setNumberNearbyBombs() {
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                int nearbyBombs = 0;
                int numberForBomb = 9;

                if (gameBoard[x][y].getBomb()) {                 //0. Fall: x/y is bomb -> number 9
                    gameBoard[x][y].setNearbyBombs(numberForBomb);
                } else if (x == 0 && y == 0) {                  //1. Fall: Corner 0/0 (oben links)
                    for (int i = 0; i < 2; i++) {
                        for (int z = 0; z < 2; z++) {
                            if (gameBoard[i][z].getBomb()) {
                                nearbyBombs++;
                            }
                        }
                    }
                    gameBoard[x][y].setNearbyBombs(nearbyBombs);
                } else if (x == ROW - 1 && y == 0) {              //2. Fall: Corner ROW-1/0 (oben rechts)
                    for (int i = ROW - 2; i < ROW; i++) {
                        for (int z = 0; z < 2; z++) {
                            if (gameBoard[i][z].getBomb()) {
                                nearbyBombs++;
                            }
                        }
                    }
                    gameBoard[x][y].setNearbyBombs(nearbyBombs);
                } else if (x == 0 && y == COL - 1) {              //3. Fall: Corner 0/COL-1 (unten links)
                    for (int i = 0; i < 2; i++) {
                        for (int z = COL - 2; z < COL; z++) {
                            if (gameBoard[i][z].getBomb()) {
                                nearbyBombs++;
                            }
                        }
                    }
                    gameBoard[x][y].setNearbyBombs(nearbyBombs);
                } else if (x == ROW - 1 && y == COL - 1) {          //4. Fall: Corner ROW-1/COL-1 (unten rechts)
                    for (int i = ROW - 2; i < ROW; i++) {
                        for (int z = COL - 2; z < COL; z++) {
                            if (gameBoard[i][z].getBomb()) {
                                nearbyBombs++;
                            }
                        }
                    }
                    gameBoard[x][y].setNearbyBombs(nearbyBombs);
                } else if (x >= 1 && y == 0) {                  //5. Fall: Top Row x/0
                    for (int i = x - 1; i < x + 2; i++) {
                        for (int z = y; z < y + 2; z++) {
                            if (gameBoard[i][z].getBomb()) {
                                nearbyBombs++;
                            }
                        }
                    }
                    gameBoard[x][y].setNearbyBombs(nearbyBombs);
                } else if (x == ROW - 1 && y >= 1) {              //6. Fall: Right Column ROW-1/y
                    for (int i = ROW - 2; i < ROW; i++) {
                        for (int z = y - 1; z < y + 2; z++) {
                            if (gameBoard[i][z].getBomb()) {
                                nearbyBombs++;
                            }
                        }
                    }
                    gameBoard[x][y].setNearbyBombs(nearbyBombs);
                } else if (x >= 1 && y == COL - 1) {              //7. Fall: Bottom Row x/COL-1
                    for (int i = x - 1; i < x + 2; i++) {
                        for (int z = COL - 2; z < COL; z++) {
                            if (gameBoard[i][z].getBomb()) {
                                nearbyBombs++;
                            }
                        }
                    }
                    gameBoard[x][y].setNearbyBombs(nearbyBombs);
                } else if (x == 0 && y >= 1) {                  //8. Fall: Left Column 0/y
                    for (int i = 0; i < 2; i++) {
                        for (int z = y - 1; z < y + 2; z++) {
                            if (gameBoard[i][z].getBomb()) {
                                nearbyBombs++;
                            }
                        }
                    }
                    gameBoard[x][y].setNearbyBombs(nearbyBombs);
                } else {                                        //9. Fall: Middle x/y
                    for (int i = x - 1; i < x + 2; i++) {
                        for (int z = y + 1; z > y - 2; z--) {
                            if (gameBoard[i][z].getBomb()) {
                                nearbyBombs++;
                            }
                        }
                    }
                    gameBoard[x][y].setNearbyBombs(nearbyBombs);
                }
            }
        }
    }



    //User chooses tile
    public final void chooseTile(int x, int y) {
        if (gameBoard[x][y].getBomb()) {     //chooses wrong tile (bomb)
            loseGame();
        } else if (gameBoard[x][y].getNearbyBombs() == 0) { //chooses correct tile
        } else {
            gameBoard[x][y].setClickedTile(true);
        }
    }



    public int listX;
    public int listY;

    /*Method checks whether a tile is number(1-8), bomb(9) or 0
     * -> if 0 than game hits these tiles too
     */
    public void checkTileNumberZero(int x, int y) {
        gameBoard[x][y].setClickedTile(true);

        if (gameBoard[x][y].getNearbyBombs() == 0) {
            if (x == 0 && y == 0) {                  //1. Fall: Corner 0/0 (oben links)
                for (int i = 0; i < 2; i++) {
                    for (int z = 0; z < 2; z++) {
                        if (!gameBoard[i][z].getClickedTile()) {
                            checkZero.add(inputCount++, i);
                            checkZero.add(inputCount++, z);
                        }
                    }
                }
            } else if (x == ROW - 1 && y == 0) {              //2. Fall: Corner ROW-1/0 (oben rechts)
                for (int i = ROW - 2; i < ROW; i++) {
                    for (int z = 0; z < 2; z++) {
                        if (!gameBoard[i][z].getClickedTile()) {
                            checkZero.add(inputCount++, i);
                            checkZero.add(inputCount++, z);
                        }
                    }
                }
            } else if (x == 0 && y == COL - 1) {              //3. Fall: Corner 0/COL-1 (unten links)
                for (int i = 0; i < 2; i++) {
                    for (int z = COL - 2; z < COL; z++) {
                        if (!gameBoard[i][z].getClickedTile()) {
                            checkZero.add(inputCount++, i);
                            checkZero.add(inputCount++, z);
                        }
                    }
                }

            } else if (x == ROW - 1 && y == COL - 1) {          //4. Fall: Corner ROW-1/COL-1 (unten rechts)
                for (int i = ROW - 2; i < ROW; i++) {
                    for (int z = COL - 2; z < COL; z++) {
                        if (!gameBoard[i][z].getClickedTile()) {
                            checkZero.add(inputCount++, i);
                            checkZero.add(inputCount++, z);
                        }
                    }
                }

            } else if (x >= 1 && y == 0) {                  //5. Fall: Top Row x/0
                for (int i = x - 1; i < x + 2; i++) {
                    for (int z = y; z < y + 2; z++) {
                        if (!gameBoard[i][z].getClickedTile()) {
                            checkZero.add(inputCount++, i);
                            checkZero.add(inputCount++, z);
                        }

                    }
                }

            } else if (x == ROW - 1 && y >= 1) {              //6. Fall: Right Column ROW-1/y
                for (int i = ROW - 2; i < ROW; i++) {
                    for (int z = y - 1; z < y + 2; z++) {
                        if (!gameBoard[i][z].getClickedTile()) {
                            checkZero.add(inputCount++, i);
                            checkZero.add(inputCount++, z);
                        }
                    }
                }

            } else if (x >= 1 && y == COL - 1) {              //7. Fall: Bottom Row x/COL-1
                for (int i = x - 1; i < x + 2; i++) {
                    for (int z = COL - 2; z < COL; z++) {
                        if (!gameBoard[i][z].getClickedTile()) {
                            checkZero.add(inputCount++, i);
                            checkZero.add(inputCount++, z);
                        }
                    }
                }

            } else if (x == 0 && y >= 1) {                  //8. Fall: Left Column 0/y
                for (int i = 0; i < 2; i++) {
                    for (int z = y - 1; z < y + 2; z++) {
                        if (!gameBoard[i][z].getClickedTile()) {
                            checkZero.add(inputCount++, i);
                            checkZero.add(inputCount++, z);
                        }
                    }
                }

            } else {                                        //9. Fall: Middle x/y
                for (int i = x - 1; i < x + 2; i++) {
                    for (int z = y + 1; z > y - 2; z--) {
                        if (!gameBoard[i][z].getClickedTile()) {
                            checkZero.add(inputCount++, i);
                            checkZero.add(inputCount++, z);
                        }
                    }
                }
            }
        }

        if (checkZero.size() > 0) {
            listX = checkZero.get(0);
            listY = checkZero.get(1);
        }
    }



    //Place flag to non-clicked tile
    public final void placeFlag(int x, int y) {
        //If neighbors are revealed and tile is not revealed, place a flag
        if (!gameBoard[x][y].getFlag() && !gameBoard[x][y].getClickedTile()) {
            gameBoard[x][y].setFlag(true);
        }
    }



    //Lose game
    public final String loseGame() {
        return "You lost the Game!";
    }



    //Win game
    public boolean winGame() {
        int countClickedTiles = 0;

        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {

                if (gameBoard[x][y].getClickedTile()) {
                    countClickedTiles++;
                }
            }
        }
        if ((ROW * COL) - AMOUNT_OF_BOMBS == countClickedTiles) {
            return true;
        } else {
            return false;
        }
    }



    //Getter-/Setter-Methods for UnitTests
    public Tile getTile(int x, int y) {
        return gameBoard[x][y];
    }

    public int getAmountOfBombs() {
        return AMOUNT_OF_BOMBS;
    }

    public int getRow() {
        return ROW;
    }

    public int getCol() {
        return COL;
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }
}
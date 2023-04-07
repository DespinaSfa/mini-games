package minigames.minesweeper.business.logic;


public class Tile {
    private boolean bombs;          //has tile (x/y) bomb
    final private int x;                  //position x in game-board
    final private int y;                  //position y in game-board
    private int nearbyBombs;        //number of near bombs
    private boolean clickedTile;    //is tile at (x/y) clicked
    private boolean flag;           //has tile at (x/y) flag



    //Constructor for the tile-settings
    public Tile(int x, int y, boolean bombs, int nearbyBombs, boolean flag, boolean clickedTile){
        this.x = x;
        this.y = y;
        this.bombs = bombs;
        this.nearbyBombs = nearbyBombs;
        this.flag = flag;
        this.clickedTile = clickedTile;
    }



    public int getX(){
        return x;
    }


    public int getY(){
        return y;
    }


    public boolean getBomb(){
        return bombs;
    }

    public void setBomb(boolean bombs){
        this.bombs = bombs;
    }


    public int getNearbyBombs() {
        return nearbyBombs;
    }

    public void setNearbyBombs(int nearbyBombs) {
        this.nearbyBombs = nearbyBombs;
    }


    public boolean getClickedTile(){
        return clickedTile;
    }

    public void setClickedTile(boolean clickedTile) {
        this.clickedTile = clickedTile;
    }


    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

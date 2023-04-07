package minigames.minesweeper.timer;

import minigames.windows.gui.ControllerBSGameMode;

public class BSTimer extends Thread {

    private int seconds;
    private ControllerBSGameMode controller;
    private boolean stopRequested = false;

    public BSTimer(ControllerBSGameMode instance) {
        controller = instance;
    }

    public void requestStop(){
        this.stopRequested = true;
    }

    public boolean isStopRequested(){
        return this.stopRequested;
    }

    @Override
    public void run() {
        while (!isStopRequested()) {
                try {
                    Thread.sleep(1000);
                    seconds++;
                    controller.setTimer(seconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}

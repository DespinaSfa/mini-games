package minigames.windows.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import minigames.minesweeper.timer.BSTimer;
import minigames.minesweeper.business.logic.MineSweeper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ControllerBSGameMode {

    private UserGameData data;

    @FXML
    private GridPane grid;
    private Button button;
    @FXML
    private Button GoBackButton;
    private Text text;
    private final int tileSize = 30;

    @FXML
    private TextField timer;
    private BSTimer thread1;

    @FXML
    private TextField bombCounter;

    private static final Logger logger = LogManager.getLogger(BSTimer.class);

    MineSweeper game = new MineSweeper();



    public void init(UserGameData data, int row, int col, int amountOfBombs) {
        this.data = data;

        //create game in class MineSweeper
        game.createGameBoard(row, col, amountOfBombs);
        game.placeBombs();
        game.setNumberNearbyBombs();
        game.setBombCount(amountOfBombs);

        grid.getChildren().clear(); //clear GridPane for new game

        //create grid with buttons
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                button = new Button();
                button.setPrefSize(tileSize, tileSize);
                button.setFocusTraversable(false);

                grid.setAlignment(Pos.CENTER);
                grid.setStyle("-fx-background-color: #6666FF");

                grid.add(button, x, y);
                handler(button);
            }
        }

        // start of Timer threading
        thread1 = new BSTimer(this);
        thread1.start();

        //show number of bombs in textfield
        bombCounter.setText(String.valueOf(amountOfBombs));
        logger.info("Thread has started.");

        logger.info("User started new game.");
    }



    //Event Handler when button pressed
    public void handler(Button button) {
        button.setOnAction(event -> {
            int row = GridPane.getColumnIndex((Node) event.getSource());
            int col = GridPane.getRowIndex((Node) event.getSource());

            game.chooseTile(row, col);

            //if chosen tile is a bomb
            if (game.getTile(row, col).getBomb()) {
                Image image = new Image(new File("src/main/resources/TestBomb.png").toURI().toString());
                ImageView view = new ImageView(image);
                view.setFitHeight(12);
                view.setFitWidth(12);
                view.setPreserveRatio(true);
                button.setGraphic(view);

                thread1.requestStop();

                logger.info("Thread has stopped.");

                logger.info("User lost MineSweeper game!");


                //endScene if a bomb is clicked
                ControllerBSEndScene myController = new ControllerBSEndScene(data);
                FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogBSEndScene.fxml"));
                root.setController(myController);
                Scene scene = null;
                try {
                    scene = new Scene(root.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage window = (Stage) GoBackButton.getScene().getWindow();
                window.setScene(scene);


            //if chosen tile is a number
            } else if (game.gameBoard[row][col].getNearbyBombs() > 0 && game.gameBoard[row][col].getNearbyBombs() < 9 && !game.gameBoard[row][col].getFlag()) {
                text = new Text(Integer.toString(game.gameBoard[row][col].getNearbyBombs()));
                Font font = Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 15);
                button.setText(text.getText());
                button.setFont(font);
                button.setStyle("-fx-background-color: #bbbaba");


            //if chosen tile is zero
            } else if (game.getTile(row, col).getNearbyBombs() == 0 && !game.getTile(row, col).getClickedTile()) {
                zeroButton(button, row, col);
            }
            logger.info("User clicked Tile: (" + row + "/" + col + ")");

            //Check if player wins
            if (game.winGame()) {
                testWin();
            }
        });


        //Place and Remove flags
        button.setOnMouseClicked(event -> {
            int row = GridPane.getColumnIndex((Node) event.getSource());
            int col = GridPane.getRowIndex((Node) event.getSource());
            boolean isFlag = game.gameBoard[row][col].getFlag();

            //Place flags
            if (event.getButton() == MouseButton.SECONDARY && !game.gameBoard[row][col].getClickedTile() && !isFlag && game.bombCount > 0) {
                Image image = new Image(new File("src/main/resources/flag.png").toURI().toString());
                ImageView view = new ImageView(image);
                view.setFitHeight(12);
                view.setFitWidth(12);
                view.setPreserveRatio(true);
                button.setGraphic(view);
                game.gameBoard[row][col].setFlag(true);

                logger.info("User placed flag at: (" + row + "/" + col + ")");

                //bombCount
                game.bombCount--;
                bombCounter.setText(String.valueOf(game.bombCount));

                //Check if player wins
                if (game.winGame()) {
                    testWin();
                }

            //Remove flags
            } else if (isFlag && event.getButton() == MouseButton.SECONDARY && game.bombCount < game.getAmountOfBombs()) {
                button.setGraphic(null);
                game.gameBoard[row][col].setFlag(false);

                logger.info("User removed flag at: (" + row + "/" + col + ")");

                //bombCount
                game.bombCount++;
                bombCounter.setText(String.valueOf(game.bombCount));
            }
        });
    }



    //Tests if user wins
    public void testWin() {
        logger.info("User won the game!");
        ControllerBSWinnerScene myController = new ControllerBSWinnerScene(data);
        FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogBSWinnerScene.fxml"));
        root.setController(myController);
        Scene scene = null;
        try {
            scene = new Scene(root.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage window = (Stage) GoBackButton.getScene().getWindow();
        window.setScene(scene);
    }



    //Checks if tiles around clicked one are also zero
    public void zeroButton(Button button, int row, int col) {
        button.setStyle("-fx-background-color: #bbbaba; ");
        Font font = Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 15);
        button.setFont(font);
        game.checkTileNumberZero(row, col);

        if (game.getTile(row, col).getNearbyBombs() > 0 && game.getTile(row, col).getNearbyBombs() < 9) {
            text = new Text(Integer.toString(game.getTile(row, col).getNearbyBombs()));
            text.setStyle("-fx-font: 5 arial;");
            button.setText(text.getText());
        }

        if (game.checkZero.size() > 0) {
            game.checkZero.remove(0);
            game.checkZero.remove(0);
            game.inputCount -= 2;
            zeroButton((Button) getNodeByCoordinate(game.listX, game.listY), game.listX, game.listY);
        }
    }



    //get button by coordinates
    private Node getNodeByCoordinate(Integer row, Integer column) {
        for (Node node : grid.getChildren()) {
            if (Objects.equals(GridPane.getColumnIndex(node), row) && Objects.equals(GridPane.getRowIndex(node), column)) {
                return node;
            }
        }
        return null;
    }



    //Timer
    public void setTimer(int seconds) {
        int minutes = seconds / 60;
        int restSeconds = seconds % 60;

        String timeString = minutes + ":" + restSeconds;

        timer.setText(timeString);
    }



    //return to Start
    @FXML
    private void GoBack_ButtonPressed(ActionEvent event) throws Exception {
        if (event.getSource() == GoBackButton) {
            thread1.requestStop();
            logger.info("Thread has stopped");

            ControllerDialogSelectGame myController = new ControllerDialogSelectGame(data);
            FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("DialogSelectGame.fxml"));
            root.setController(myController);
            Scene scene = new Scene(root.load());
            Stage window = (Stage) GoBackButton.getScene().getWindow();
            window.setScene(scene);
        }
    }
}



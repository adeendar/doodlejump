package doodlejump;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * The Game class is the top-level logic class responsible for
 * creating a game pane, the doodle character, the score label,
 * and the platform handler class. It also defines methods responsible
 * for the game to continue or end based on different conditions.
 */
public class Game {
    private Pane gamePane;
    private PlatformHandler platformHandler;
    private Doodle doodle;
    private Timeline timeline;
    private BorderPane root;
    private ScoreLabel myScore;

    /**
     * The Game constructor sets the game Pane, creates the doodle character,
     * the platform handler, and the score label.
     * @param root the main graphical root of the game
     */
    public Game(BorderPane root) {
        this.gamePane = new Pane();
        this.gamePane.setStyle("-fx-background-color: beige");
        this.gamePane.setPrefSize(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        root.setCenter(gamePane);
        this.doodle = new Doodle(Constants.STARTING_X, Constants.STARTING_Y, gamePane);
        this.platformHandler = new PlatformHandler(gamePane, doodle);
        platformHandler.generatePlatforms();
        this.setUpTimeline();
        this.gamePane.setFocusTraversable(true);
        gamePane.setOnKeyPressed((e) -> this.handleKeyInput(e));
        this.root = root;
        this.myScore = new ScoreLabel(gamePane);
    }

    /**
     * The handleKeyInput method is responsible for moving the
     * doodle left or right depending on the key pressed.
     * @param e The event handler responsible for keyboard interaction
     */
    public void handleKeyInput(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        switch (keyPressed) {
            case LEFT:
                this.doodle.moveLeft();
                this.doodle.checkXLoc();
                break;
            case RIGHT:
                this.doodle.moveRight();
                this.doodle.checkXLoc();
                break;
            default:
                break;
        }
    }

    /**
     * The setUpTimeline method is responsible for creating the
     * timeline for the game to run.
     */
    public void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION),
                (ActionEvent e) -> this.update());
        this.timeline = new Timeline(kf);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    /**
     * This method checks if the doodle is at the
     * midpoint of the game pane and updates the platforms'
     * positions to achieve the effect of "scrolling" up.
     */
    public void scrollPlatform() {
        ArrayList<Platform> platformList = platformHandler.getArrayList();
        if (doodle.getYLoc() < Constants.PANE_HEIGHT / 2) {
            double difference = Constants.PANE_HEIGHT / 2 - this.doodle.getYLoc();
            this.myScore.updateLabel(1);
            for (int i = 0; i < platformList.size(); i++) { //keep as a for loop. for-each doesn't work
                Platform indexedPlatform = platformList.get(i);
                indexedPlatform.setYLoc(indexedPlatform.getYLoc() + difference);
                if (indexedPlatform.getYLoc() > Constants.PANE_HEIGHT) {
                    indexedPlatform.removePlatform();
                    platformList.remove(indexedPlatform);
                }
            }
            this.doodle.setPosition(Constants.PANE_HEIGHT/2);
            this.platformHandler.generatePlatforms();
        }
    }

    /**
     * This method checks if the doodle has crossed out of the game pane
     * and removes it from the game if it has.
     */
    public void gameOver () {
        if (this.doodle.checkIfGameOver()) {
            this.timeline.stop();
            Label gameOverLabel = new Label("GAME OVER!");
            gameOverLabel.setTranslateX(Constants.CENTER - Constants.CENTER_X_OFFSET);
            gameOverLabel.setTranslateY(Constants.PANE_HEIGHT/2);
            this.gamePane.getChildren().add(gameOverLabel);
            this.gamePane.setFocusTraversable(false);
        }
    }

    /**
     * The update method is called according to the game's
     * timeline, and updates the doodle's position as well as
     * calls the gameOver method to check the doodle's y position.
     */
    public void update() {
        doodle.updatePosition();
        this.scrollPlatform();
        this.gameOver();
        ArrayList<Platform> platformList = platformHandler.getArrayList();
        if (doodle.getVelocity() > 0) {
            for (Platform platform : platformList) {
                if (doodle.checkIntersection(platform.getBounds())) {
                    platform.react();
                }
            }
        }
    }
}

package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * The MovingPlatform class inherits from its superclass (Platform) and
 * adds the special capability of moving along the game screen.
 */
public class MovingPlatform extends Platform {

    private boolean direction; //true = moving right, false = moving left

    /**
     * This constructor calls super to instantiate a new object of Platform
     * and animates the moving platform.
     * @param x the x location of the moving platform
     * @param y the y location of the moving platform
     * @param doodle the doodle character
     * @param gamePane the game's main graphical pane
     */
    public MovingPlatform (double x, double y, Doodle doodle, Pane gamePane) {
        super(x, y, gamePane, doodle);
        this.animatePlatform();
        this.setPlatformColor(Color.DODGERBLUE);
    }

    /**
     * This method sets up a new timeline for the movingPlatform to animate
     * across the screen.
     */
    public void animatePlatform () {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION),
                (ActionEvent e) -> this.update());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    /**
     * The update method updates the positon of the movingPlatform based
     * on its given coordinates.
     */
    public void update(){
        if (this.direction) {
            this.setXLoc(this.getXLoc() + Constants.PLATFORM_VELOCITY);
            if (this.getXLoc() > Constants.PANE_WIDTH - Constants.PLATFORM_WIDTH) {
                this.direction = false;
            }
        }
        else {
            this.setXLoc(this.getXLoc() - Constants.PLATFORM_VELOCITY);
            if (this.getXLoc() < 0) {
                this.direction = true;
            }
        }
        }
}

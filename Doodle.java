package doodlejump;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The Doodle class is a wrapper class for the JavaFX rectangle.
 * This class creates the doodle character and defines a variety of methods
 * to manipulate its position.
 */
public class Doodle {
    private Rectangle doodle;
    private double velocity;
    private double position;

    /**
     * The doodle constructor creates a new doodle object and adds it to the
     * game pane.
     * @param x the given x location of the doodle
     * @param y the given y location of the doodle
     * @param gamePane the game pane
     */
    public Doodle(double x, double y, Pane gamePane){
        this.doodle = new Rectangle(x,y,Constants.DOODLE_WIDTH,Constants.DOODLE_HEIGHT);
        this.doodle.setFill(Color.YELLOWGREEN);
        gamePane.getChildren().add(doodle);
        this.velocity = 0;
        this.position = y;
    }

    /**
     * This method gets the y location of the doodle.
     * @return y location of the doodle
     */
    public double getYLoc() {
        return this.doodle.getY();
    }

    /**
     * This method moves the doodle left a given constant based
     * on its current position
     */
    public void moveLeft() {
        this.doodle.setX(this.doodle.getX() - 20);
    }

    /**
     * This method movews the doodle right based on a given constant
     */
    public void moveRight() {
        this.doodle.setX(this.doodle.getX() + 20);
    }

    /**
     * This method checks if the doodle is out of bounds
     * of the game pane, and adds a "wrapping" capability
     * so the doodle wraps around to the other side of the screen.
     */
    public void checkXLoc () {
        if (this.doodle.getX() > 400 - Constants.DOODLE_WIDTH) {
            this.doodle.setX(0);
        }
        if (this.doodle.getX() < 0) {
            this.doodle.setX(400 - Constants.DOODLE_WIDTH);
        }
    }

    /**
     * The checkIfGameOver method checks if the
     * doodle's position is below the game frame,
     * and returns a boolean based on this to tell if it has
     * fallen off a platform.
     * @return whether the doodle is in the game pane
     */
    public boolean checkIfGameOver () {
        if (this.position > Constants.GAME_OVER) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method is called in the Game's timeline so
     * that the doodle update it's position in the game.
     */
    public void updatePosition(){
        this.velocity += Constants.GRAVITY*Constants.DURATION;
        this.position += this.velocity*Constants.DURATION;
        this.doodle.setY(this.position);
        this.doodle.toFront();
    }

    /**
     * This method that returns a boolean that is used to
     * check if the doodle intersects with a platform.
     * @param bounds the bounds of platform
     * @return whether the doodle has intersected with a platform
     */
    public boolean checkIntersection(Bounds bounds){
        if (this.doodle.intersects(bounds)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Sets the doodle's velocity to some given value
     * @param newVelocity the new velocity of the doodle
     */
    public void setVelocity (double newVelocity) {
        this.velocity = newVelocity;
    }

    /**
     * This method gets the velocity of the doodle
     * @return the velocity of the doodle
     */
    public double getVelocity(){
        return velocity;
    }

    /**
     * This method sets the doodle's position to a new given
     * position.
     * @param newPosition the doodle's new position
     */
    public void setPosition(double newPosition){
        this.position = newPosition;
        this.doodle.setY(newPosition);

    }
}

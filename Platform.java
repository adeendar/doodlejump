package doodlejump;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The Platform class is the wrapper class for the JavaFX Rectangle
 * Object. This class defines a standard platform and methods to
 * manipulate its properties. It is the superclass for the special
 * platforms.
 */
public class Platform extends Rectangle {

    private Rectangle platform;
    private Color platformColor;
    private Doodle myDoodle;
    private Pane pane;

    /**
     * The constructor generates a new platform based on inputed
     * x and y coordinates and passes in the doodle and gamePane so
     * that the platform knows about these objects.
     * @param xLoc the x location of the platform
     * @param yLoc the y location of the platform
     * @param myPane the game of the pane
     * @param doodle the doodle character in the game
     */
    public Platform (double xLoc, double yLoc, Pane myPane, Doodle doodle) {
        this.platform = new Rectangle(xLoc, yLoc, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
        this.platformColor = Color.FORESTGREEN;
        this.myDoodle = doodle;
        this.platform.setFill(this.platformColor);
        this.pane = myPane;
        myPane.getChildren().add(this.platform);
    }

    /**
     * This method gets the y location of the platform since
     * you can't use FX methods on wrapper class objects
     * @return the y location of the platform
     */
    public double getYLoc(){
        return this.platform.getY();
    }

    /**
     * This method gets the xlocation of the platform since
     * you can't use FX methods on wrapper class objects
     * @return the x location of the platform
     */
    public double getXLoc(){
        return this.platform.getX();
    }

    /**
     * This method changes the y location input based on a given parameter
     * @param yLoc the new y location
     */
    public void setYLoc(double yLoc){
        this.platform.setY(yLoc);
    }

    /**
     * This method changes the x location input based on a given parameter
     * @param xLoc the new x location
     */
    public void setXLoc (double xLoc){
        this.platform.setX(xLoc);
    }

    /**
     * This method allows the subclasses to change the platform's color
     * @param newColor the new color of the platform
     */
    public void setPlatformColor (Color newColor) {
        this.platform.setFill(newColor);
    }

    /**
     * This method gets the bounds of the platform
     * @return the bounds of the platform
     */
    public Bounds getBounds(){
        return this.platform.getLayoutBounds();
    }

    /**
     * This method removes the platform from the game Pane.
     */
    public void removePlatform(){
        this.pane.getChildren().remove(platform);
    }

    /**
     * This method is overriden by Platform's subclasses in order
     * to specify platform capabilites.
     */
    public void react () {
            this.myDoodle.setVelocity(Constants.REBOUND_VELOCITY);
        }
}

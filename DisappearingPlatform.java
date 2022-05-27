package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The DisappearingPlatform class inherits from its superclass (Platform)
 * and adds the special capability of disappearing when interacting with the doodle.
 */
public class DisappearingPlatform extends Platform{

    private Doodle myDoodle;
    private boolean status;

    /**
     * The DisappearingPlatform method creates a new platform by calling
     * the superclass constructor and takes in the doodle and the gamePane as
     * parameters so that it knows about them.
     * @param xLoc the x location of the platform
     * @param yLoc the y location of the platform
     * @param doodle the doodle character
     * @param gamePane the game pane
     */
    public DisappearingPlatform (double xLoc, double yLoc, Doodle doodle, Pane gamePane) {
        super(xLoc, yLoc, gamePane, doodle);
        this.myDoodle = doodle;
        this.setPlatformColor(Color.SADDLEBROWN);
        this.status = true; // boolean describing whether the react method should be called.
    }

    /**
     * The overriden react method checks if the doodle has intersected with
     * the disappearing platform and subsequently removes the platform from the game.
     */
    @Override
    public void react () {
        if (this.status) {
            this.myDoodle.setVelocity(Constants.REBOUND_VELOCITY);
            this.removePlatform();
            this.status = false;
        }
    }
}

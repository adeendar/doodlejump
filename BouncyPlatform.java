package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * The BouncyPlatform class inherits from the Platform
 * class (its superclass) and adds the special capability
 * of an extra bounce when the doodle reacts with it.
 */
public class BouncyPlatform extends Platform {

    private Doodle myDoodle;

    /**
     * The BouncyPLatform constructor creates a new platform by calling its superclass
     * and sets the platform color to a different color.
     * @param x the x location of the platform
     * @param y the y location of the platform
     * @param doodle the doodle character
     * @param gamePane the gamePane
     */
    public BouncyPlatform (double x, double y, Doodle doodle, Pane gamePane) {
        super(x, y,gamePane, doodle);
        this.myDoodle = doodle;
        this.setPlatformColor(Color.MEDIUMORCHID);
    }

    /**
     * The BouncyPlatform class' react method adds an extra bounce for the doodle.
     */
    @Override
    public void react() {
        this.myDoodle.setVelocity(Constants.BOUNCY_REBOUND_VELOCITY);
    }

}

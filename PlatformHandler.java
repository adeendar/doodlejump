package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * The PlatformHandler class is the top-level logic class for
 * the different platforms. This class creates a base platform
 * for the doodle to start on and defines methods to create multiple platforms.
 */
public class PlatformHandler extends Rectangle {

    private Platform base;
    private ArrayList<Platform> gamePlatforms;
    private Pane gamePane;
    private Doodle myDoodle;

    public PlatformHandler(Pane gamePane, Doodle doodle) {
        this.myDoodle = doodle;
        this.gamePlatforms = new ArrayList<>();
        this.gamePane = gamePane;
        this.base = new Platform(Constants.STARTING_X, Constants.STARTING_Y, gamePane, myDoodle);
        this.base.setPlatformColor(Color.GREEN);
        this.gamePlatforms.add(this.base);
    }

    /**
     * This method returns the arraylist used to store the
     * platforms.
     * @return the platform arrayList
     */
    public ArrayList<Platform> getArrayList() {
        return this.gamePlatforms;
    }

    /**
     * This method creates randomly platforms of random type
     * and defines coordinates for their spawning based
     * on different parameters as well as how high the doodle
     * can jump.
     */
    public void generatePlatforms() {
        Platform mostRecent = this.gamePlatforms.get(this.gamePlatforms.size() - 1); // the most recently added platform; ArrayList[0]
        while ((mostRecent.getYLoc() > 0)) {
            double low = Math.max(0, mostRecent.getXLoc() - Constants.X_OFFSET_MAX);
            double high = Math.min(Constants.PANE_WIDTH - Constants.PLATFORM_WIDTH, mostRecent.getXLoc() + Constants.X_OFFSET_MAX);
            double x = (Math.random() * (high - low) + low);

            low = mostRecent.getYLoc() - Constants.PLATFORM_HEIGHT;
            high = mostRecent.getYLoc() - Constants.Y_OFFSET_MAX;
            double y = (Math.random() * (high - low) + low);

            Platform generatedPlatform = this.randPlatform(x,y);
            mostRecent = generatedPlatform; //sets the newest platform to the new generated platform
        }
    }

    /**
     * This method creates the different types of platforms
     * semi-randonmly with standard platforms being most likely to be
     * generated.
     * @param x the newly defined x coordinate
     * @param y the newly defined y coordinate
     * @return the new platform to be generated
     */
    public Platform randPlatform (double x, double y) {
        int counter = (int) (Math.random() * 10);
        Platform platToBeReturned;
        switch (counter) {
            case 0: case 1:
                platToBeReturned = new BouncyPlatform(x, y, myDoodle, gamePane);
                break;

                case 2: case 3:
                platToBeReturned = new DisappearingPlatform(x, y, myDoodle, gamePane);
                break;

                case 4: case 5:
                platToBeReturned = new MovingPlatform(x, y, myDoodle, gamePane);
                break;

                default:
                platToBeReturned = new Platform(x, y, gamePane, myDoodle);
                break;
        }
        this.gamePlatforms.add(platToBeReturned);
        return platToBeReturned;
    }
}



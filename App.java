package doodlejump;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class where your DoodleJump game will start.
 * The main method of this application calls launch, a JavaFX method
 * which eventually calls the start method below. You will need to fill
 * in the start method to start your game!
 *
 * The App class sets the scene and stage for the game and also instantiates
 * an instance of the top-level graphical class.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        PaneOrganizer main = new PaneOrganizer();
        Scene scene = new Scene(main.getRoot(), Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        stage.setScene(scene);
        stage.show();
        // Instantiate top-level object, set up the scene, and show the stage here.
    }

    /*
     * Here is the mainline! No need to change this.
     */
    public static void main(String[] argv) {
        // launch is a static method inherited from Application.
        launch(argv);
    }
}

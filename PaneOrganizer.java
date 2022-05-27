package doodlejump;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * The PaneOrganizer class is responsible for instantiating
 * the root node for the game and constructs an instance of
 * game, passing the root as a parameter.
 */
public class PaneOrganizer {
    private BorderPane root;
    private Game game;

    /**
     * The PaneOrganizer constructor creates a root BorderPane
     * for the game, instantiates a new instance of Game, and also
     * creates the quit button.
     */
    PaneOrganizer(){
        this.root = new BorderPane();
        this.game = new Game(root);
        this.makeButton();
    }

    /**
     * The make button method creates the quit button for the program
     * and adds it to the game's root node.
     */
    public void makeButton () {
        Pane buttonPane = new Pane();
        buttonPane.setStyle("-fx-background-color: lightgrey");
        buttonPane.setPrefSize(Constants.BUTTON_PANE_WIDTH, Constants.BUTTON_PANE_HEIGHT);
        Button quitButton = new Button("Quit");
        quitButton.setTranslateX(Constants.CENTER);
        buttonPane.getChildren().add(quitButton);
        quitButton.setOnAction((ActionEvent e) -> System.exit(0));
        root.setBottom(buttonPane);
        quitButton.setFocusTraversable(false);
    }

    /**
     * The getRoot method returns the root graphical node of the
     * game.
     * @return the root node
     */
    public Pane getRoot(){
        return this.root;
    }

}




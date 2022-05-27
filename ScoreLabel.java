package doodlejump;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * The scoreLabel class sets up the score label and updates
 * the score.
 */
public class ScoreLabel {

    private Label myLabel;
    private int score;

    /**
     * The ScoreLabel constructor sets the score value to 0
     * and creates the score label, adding it to the pane.
     * @param gamePane
     */
    public ScoreLabel(Pane gamePane) {
        this.score = 0;
        this.myLabel = new Label("Score: "+score+" ");
        gamePane.getChildren().add(myLabel);
    }

    /**
     * The updateLabel increments the player's score
     * based on how high the doodle gets.
     * @param increment the amount to increase the score
     */
    public void updateLabel(int increment) {
        this.score += increment;
        this.myLabel.setText("Score: "+score+" ");
    }
}

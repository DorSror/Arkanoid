import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Dor Sror
 * A score indicator that implements sprite, shows the current score.
 * Holds a reference to the current count of the score.
 */
public class ScoreIndicator implements Sprite {
    private Point textPoint;
    private int fontSize;
    private Counter currentScore;

    /**
     * Constructs a new score indicator.
     * @param textPoint the upper-left corner of the square where the text will be written.
     * @param fontSize the size of the text's font.
     * @param currentScore the reference to the current score count.
     */
    public ScoreIndicator(Point textPoint, int fontSize, Counter currentScore) {
        this.textPoint = textPoint;
        this.fontSize = fontSize;
        this.currentScore = currentScore;
    }

    /**
     * Draws the sprite on a given draw surface.
     * @param d the given draw surface.
     */
    public void drawOn(DrawSurface d) {
        String scoreText = "Score: " + Integer.toString(this.currentScore.getValue());
        d.setColor(Color.BLACK);
        d.drawText((int) this.textPoint.getX(), (int) this.textPoint.getY(), scoreText, this.fontSize);
    }

    /**
     * Notify the sprite that time has passed. Does nothing in this class.
     */
    public void timePassed() {
        return;
    }

    /**
     * Adds the score indicator to the game.
     * @param gameLevel The Game object - GameLevel.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}

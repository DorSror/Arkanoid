import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Dor Sror
 * The LevelIndicator class, implements Sprite interface - a sprite that holds the current level's name
 * and draws it on a DrawSurface.
 */
public class LevelIndicator implements Sprite {
    private final Point point;
    private final int fontSize;
    private final String levelName;

    /**
     * Constructs a new level indicator.
     * @param textPoint the upper-left corner of the square where the text will be written.
     * @param fontSize the size of the text's font.
     * @param levelName the reference to the current score count.
     */
    public LevelIndicator(Point textPoint, int fontSize, String levelName) {
        this.point = textPoint;
        this.fontSize = fontSize;
        this.levelName = levelName;
    }

    /**
     * Draws the sprite on a given draw surface.
     * @param d the given draw surface.
     */
    public void drawOn(DrawSurface d) {
        String levelText = "Level: " + this.levelName;
        d.setColor(Color.BLACK);
        d.drawText((int) this.point.getX(), (int) this.point.getY(), levelText, this.fontSize);
    }

    /**
     * Notify the sprite that time has passed. Does nothing in this class.
     */
    public void timePassed() {
        return;
    }

    /**
     * Adds the level indicator to the game.
     * @param gameLevel The Game object - GameLevel.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Dor Sror - 207271875
 * The EndScreen class, implements Animation interface - an ending screen that shows your final score
 * and wether you won or lost.
 */
public class EndScreen implements Animation {
    private final int score;
    private final boolean gameWon;

    /**
     * The EndScreen constructor.
     * @param score the final score of the game.
     * @param won if the game was won or not.
     */
    public EndScreen(int score, boolean won) {
        this.score = score;
        this.gameWon = won;
    }

    /**
     * Draws one frame of the animation.
     * @param d the drawing surface the animation will be drawn on.
     */
    public void doOneFrame(DrawSurface d) {
        if (this.gameWon) {
            this.winFrame(d);
        } else {
            this.defeatFrame(d);
        }
    }

    /**
     * Draws a win frame of the screen.
     * @param d the drawing surface the animation will be drawn on.
     */
    public void winFrame(DrawSurface d) {
        d.setColor(new Color(240, 240, 240));
        d.fillRectangle(-5, -5, 810, 810);
        d.setColor(Color.BLUE);
        d.drawText(145, 240, "You Won!", 120);
        d.setColor(Color.MAGENTA);
        String scoreText = "Your final score is: " + Integer.toString(this.score);
        d.drawText(200, 350, scoreText, 40);
        d.setColor(Color.GREEN);
        d.drawText(170, 400, "Press 'Space' to Terminate", 40);
    }

    /**
     * Draws a defeat frame of the screen.
     * @param d the drawing surface the animation will be drawn on.
     */
    public void defeatFrame(DrawSurface d) {
        d.setColor(new Color(35, 35, 35));
        d.fillRectangle(-5, -5, 810, 810);
        d.setColor(Color.RED);
        d.drawText(145, 240, "You Lost!", 120);
        String scoreText = "Your final score is: " + Integer.toString(this.score);
        d.setColor(Color.ORANGE);
        d.drawText(210, 350, scoreText, 40);
        d.setColor(Color.YELLOW);
        d.drawText(170, 400, "Press 'Space' to Terminate", 40);
    }

    /**
     * Returns if an animation should stop.
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }
}

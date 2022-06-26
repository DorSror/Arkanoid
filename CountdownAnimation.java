import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * @author Dor Sror
 * The CountdownAnimation class, implements Animation interface - a countdown animation that counts down
 * from a given number for a given amount of time, on top of a game's level.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private final GUI gui;

    /**
     * The CountdownAnimation constructor.
     * @param numOfSeconds the number of seconds we want the countdown to be.
     * @param countFrom the starting number of the countdown.
     * @param gameScreen the sprite collection that holds all the sprites of the game.
     * @param gui the GUI the animation will be drawn on.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, GUI gui) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.gui = gui;
    }

    /**
     * Draws one frame of the animation.
     * @param d the drawing surface the animation will be drawn on.
     */
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        long startTime = System.currentTimeMillis();
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(0, 0, 800, 600);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE);
        DrawSurface ds = this.gui.getDrawSurface();
        ds.setColor(Color.DARK_GRAY);
        ds.fillRectangle(0, 0, 800, 600);
        this.gameScreen.drawAllOn(ds);
        ds.setColor(Color.WHITE);
        ds.drawText(ds.getWidth() / 2 - 30, ds.getHeight() / 2, Integer.toString(this.countFrom), 128);
        this.gui.show(ds);
        long usedTime = System.currentTimeMillis() - startTime;
        sleeper.sleepFor((long) (1000 * this.numOfSeconds / this.countFrom) - usedTime);
        this.numOfSeconds -= this.numOfSeconds / this.countFrom;
        this.countFrom--;
    }

    /**
     * Returns if an animation should stop.
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.countFrom == 0;
    }
}

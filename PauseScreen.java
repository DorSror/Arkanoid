import biuoop.DrawSurface;

/**
 * @author Dor Sror
 * The PauseScreen class, implements Animation interface - a pause screen that shows a white background
 * with text on top of it.
 */
public class PauseScreen implements Animation {
    /**
     * Draws one frame of the animation.
     * @param d the drawing surface the animation will be drawn on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * Returns if an animation should stop.
     * @return true if the animation should stop, false otherwise.
     *          note that it will always be false in this class.
     */
    public boolean shouldStop() {
        return false;
    }
}

import biuoop.DrawSurface;

/**
 * @author Dor Sror
 * The Animation interface - holds all methods that are needed for drawing animations.
 */
public interface Animation {
    /**
     * Draws one frame of the animation.
     * @param d the drawing surface the animation will be drawn on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Returns if an animation should stop.
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}

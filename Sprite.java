import biuoop.DrawSurface;

/**
 * @author Dor Sror
 * The sprite interface - holds 2 methods.
 */
public interface Sprite {
    /**
     * Draws the sprite on a given draw surface.
     * @param d the given draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}
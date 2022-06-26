import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Dor Sror
 * The KeyPressStoppableAnimation class, implements Animation interface - draws an animation until a key
 * is pressed, and stops it accordingly.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor ks;
    private final String key;
    private final Animation animation;
    private boolean stop = false;
    private boolean isAlreadyPressed = true;

    /**
     * The KeyPressStoppableAnimation constructor.
     * @param sensor the keyboard sensor of the game.
     * @param key the key that stops the animation.
     * @param animation the animation that runs the game.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.ks = sensor;
        this.key = key;
        this.animation = animation;
    }

    /**
     * Draws one frame of the animation.
     * @param d the drawing surface the animation will be drawn on.
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.ks.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.ks.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * Returns if an animation should stop.
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}

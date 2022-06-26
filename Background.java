import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dor Sror
 * The Background class - holds a list of sprites that create a background.
 */
public class Background implements Sprite {
    private List<Sprite> backgroundSpriteList;

    /**
     * Adds a sprite to the background.
     * @param sprite the sprite we want to add.
     */
    public void addSprite(Sprite sprite) {
        if (this.backgroundSpriteList == null) {
            this.backgroundSpriteList = new ArrayList<>();
        }
        this.backgroundSpriteList.add(sprite);
    }

    /**
     * Draws the sprite on a given draw surface.
     * @param d the given draw surface.
     */
    public void drawOn(DrawSurface d) {
        if (this.backgroundSpriteList != null) {
            for (Sprite backgroundElement : this.backgroundSpriteList) {
                backgroundElement.drawOn(d);
            }
        }
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {
        return;
    }
}

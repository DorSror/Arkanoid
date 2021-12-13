import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * @author Dor Sror - 207271875
 * The game's sprite collection - holds a list of sprites.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Adds the given sprite to the game's environment.
     * If there is no list present then it creates one.
     * @param s the given collidable.
     */
    public void addSprite(Sprite s) {
        if (this.spriteList == null) {
            this.spriteList = new ArrayList<>();
        }
        this.spriteList.add(s);
    }

    /**
     * Removes a sprite from the sprite collection.
     * Since removing it directly causes an exception, we will
     * initialize a new list that does not contain the sprite that
     * we wish to remove, and set it as the current sprite collection.
     * @param s the sprite we wish to delete.
     */
    public void removeSprite(Sprite s) {
        if (this.spriteList != null) {
            List<Sprite> spriteListNew = new ArrayList<>();
            for (Sprite sprite : this.spriteList) {
                if (!sprite.equals(s)) {
                    spriteListNew.add(sprite);
                }
            }
            this.spriteList = spriteListNew;
        }
    }

    /**
     * Calls timePassed() on all sprites in the sprites list.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : this.spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all the sprites on a given draw surface.
     * @param d the given draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spriteList) {
            sprite.drawOn(d);
        }
    }
}

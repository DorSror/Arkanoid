import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dor Sror - 207271875
 * Represents a Block made by a Rectangle shape and color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle shape;
    private java.awt.Color color;
    private List<HitListener> hitListeners;
    private boolean drawOutlines = false;

    /**
     * Constructor - constructs a Block object using Rectangle shape.
     * @param shape the rectangular shape of the block.
     */
    public Block(Rectangle shape) {
        this.shape = shape;
    }

    /**
     * Sets the color of the block to color.
     * @param newColor the new block's color.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * Returns the "collision shape" of the object.
     * @return the block's shape.
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Notify the object that we collided with at collisionPoint with a
     * given velocity.
     * The return if the new velocity expected after the hit (based on
     * the force of the object inflicted on the ball).
     * @param hitter the ball that hit the block.
     * @param collisionPoint the point of the collision.
     * @param currentVelocity the current velocity of the object.
     * @return velocity after collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point upperLeft = this.shape.getUpperLeft();
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();
        double upperRightX = upperLeft.getX() + this.shape.getWidth();
        double lowerLeftY = upperLeft.getY() + this.shape.getHeight();
        if (collisionPoint.getX() == upperLeft.getX() || collisionPoint.getX() == upperRightX) {
            newDx = -newDx;
        }
        if (collisionPoint.getY() == upperLeft.getY() || collisionPoint.getY() == lowerLeftY) {
            newDy = -newDy;
        }
        this.notifyHit(hitter);
        return new Velocity(newDx, newDy);
    }

    /**
     * Draws the block on a drawing surface - fills the block with its corresponding color and draws a black rectangle
     * around it.
     * @param d the drawing surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        if (this.drawOutlines) {
            d.setColor(Color.BLACK);
            d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                    (int) this.shape.getWidth(), (int) this.shape.getHeight());
        }
    }

    /**
     * As of the assignment's requirement, this method currently does nothing.
     */
    public void timePassed() {
        return;
    }

    /**
     * Adds the block to the game's collidable and sprite lists.
     * @param gameLevel The game object created by Ass3Game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * Removes the block from the game, by removing it from both
     * collidables and sprites.
     * @param gameLevel The game we wish to remove the block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Adds a hit listener to the block.
     * If there are no listeners present, meaning the list is null,
     * it will create a new one.
     * @param hl the hit listener we want to add to the block.
     */
    public void addHitListener(HitListener hl) {
        if (this.hitListeners == null) {
            this.hitListeners = new ArrayList<>();
        }
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener to the block, if there is a list of hit listeners.
     * @param hl the hit listener we want to remove from the block.
     */
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners != null) {
            this.hitListeners.remove(hl);
        }
    }

    /**
     * Notifies all the hit listeners of this block that a ball hit it.
     * @param hitter the ball that hit this block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        if (this.hitListeners == null) {
            return;
        }
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Determines if a block should be drawn with outlines or not.
     * By default is set to true.
     * @param shouldDrawOutlines true if we want the block to be drawn with outlines.
     */
    public void drawOutlines(boolean shouldDrawOutlines) {
        this.drawOutlines = shouldDrawOutlines;
    }
}

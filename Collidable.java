/**
 * @author Dor Sror
 * The collidable interface - holds 2 methods.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     * @return the rectangular shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with at collisionPoint with a
     * given velocity.
     * The return if the new velocity expected after the hit (based on
     * the force of the object inflicted on us)
     * @param hitter the ball that hit the block.
     * @param collisionPoint the point of the collision.
     * @param currentVelocity the current velocity of the object.
     * @return velocity after collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity); //ball l.130 changed, paddle l.71
}
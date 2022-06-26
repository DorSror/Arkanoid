/**
 * @author Dor Sror
 * The collision info of the collision - holds the point of collision and the object involved in the collision.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collidable;

    /**
     * Constructs a CollisionInfo object.
     * @param collisionPoint the point of collision.
     * @param collidable the collidable object that has been collided with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * Returns the point at which the collision occurs.
     * @return the point of collision.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}
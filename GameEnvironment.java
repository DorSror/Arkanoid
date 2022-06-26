import java.util.ArrayList;
import java.util.List;

/**
 * @author Dor Sror
 * The game's environment - holds a list of collidable objects.
 */
public class GameEnvironment {
    private List<Collidable> collidableList;

    /**
     * Adds the given collidable to the game's environment.
     * If there is no list present then it creates one.
     * @param c the given collidable.
     */
    public void addCollidable(Collidable c) {
        if (this.collidableList == null) {
            this.collidableList = new ArrayList<>();
        }
        this.collidableList.add(c);
    }

    /**
     * Removes a collidable from this game environment's collidable
     * list.
     * @param c the collidable we wish to remove.
     */
    public void removeCollidable(Collidable c) {
        if (this.collidableList != null) {
            this.collidableList.remove(c);
        }
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory object's path from start to end.
     * @return the CollisionInfo of the closest collision - null if there is no collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollidable = null;
        Point closestCollision = null;
        List<Point> collisions;
        for (Collidable collidable : this.collidableList) {
            collisions = collidable.getCollisionRectangle().intersectionPoints(trajectory);
            if (closestCollision == null && collisions.size() != 0) {
                closestCollision = collisions.get(0);
                closestCollidable = collidable;
            }
            if (collisions != null) {
                for (Point collision : collisions) {
                    if (collision.distance(trajectory.start()) < closestCollision.distance(trajectory.start())) {
                        closestCollision = collision;
                        closestCollidable = collidable;
                    }
                }
            }
        }
        if (closestCollision == null) {
            return null;
        }
        return new CollisionInfo(closestCollision.truncatePoint(), closestCollidable);
    }

}
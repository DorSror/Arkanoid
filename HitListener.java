/**
 * @author Dor Sror - 207271875
 * The hit listener interface - holds 1 method.
 */
public interface HitListener {
    /**
     * This method is called whenever a block is being hit by a ball.
     * @param beingHit the ball that is being hit.
     * @param hitter the ball that hits the ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
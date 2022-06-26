/**
 * @author Dor Sror
 * A listener that listens for hits and removes balls from a game.
 * Holds references for a game instance and a counter of the remaining balls.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Constructs a new BallRemover Listener.
     * @param gameLevel The reference to the game.
     * @param removedBlocks The reference to the amount of balls remaining.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBlocks;
    }

    /**
     * Removes the ball that hit the block from the game, and
     * decreases the amount of remaining balls by 1.
     * @param beingHit the block that got hit.
     * @param hitter the ball that hit said block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}

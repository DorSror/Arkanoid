/**
 * @author Dor Sror
 * A listener that listens for hits and removes blocks from a game.
 * Holds references for a game instance and a counter of the remaining balls.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructs a new BlockRemover Listener.
     * @param gameLevel The reference to the game.
     * @param removedBlocks The reference to the amount of blocks remaining (I've
     * decided to count the amount of blocks remaining on the screen, but since we
     * aren't allowed to change the methods, the name stayed the same).
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Removes this listener from the block that was hit,
     * removes the block that was hit from the game, and
     * decreases the amount of remaining blocks by 1.
     * @param beingHit the block that got hit.
     * @param hitter the ball that hit said block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(1);
    }
}

/**
 * @author Dor Sror
 * A listener that listens for hits and changes the score accordingly.
 * Holds a references for a game's current score count.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener.
     * @param scoreCounter the reference to the game's score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Increases the score by 5.
     * @param beingHit the block that was hit, not used in this method.
     * @param hitter the ball that hit beingHit, not used in this method.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}
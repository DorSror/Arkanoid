import java.util.List;

/**
 * @author Dor Sror
 * The LevelInformation interface - holds all methods and information about a level.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * Returns a list with all the initial velocities of the balls.
     * @return list of velocities for each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed at which the paddle moves.
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     * @return width of the paddle.
     */
    int paddleWidth();

    /**
     * Returns the level name as a string.
     * The level name will be displayed at the top of the screen.
     * @return the level's name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return a background sprite that contains all the sprites that create said background.
     */
    Sprite getBackground();

    /**
     * Returns a list of blocks that make up this level, each block contains
     * its size, color and location.
     * @return a list of blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * Note that This number should be <= blocks.size().
     * @return the number of blocks that needs to be removed for the level to be cleared.
     */
    int numberOfBlocksToRemove();
}
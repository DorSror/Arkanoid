import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Dor Sror
 * The game object - creates an 'Arkanoid' game instance, initializes and runs it.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private final GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private final Counter score;
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final LevelInformation info;

    /**
     * The GameLevel Constructor.
     * @param info the level's information.
     * @param ks the keyboard sensor of the game.
     * @param ar the animation runner.
     * @param gui the GUI of the game.
     * @param startingScore the starting score of the game.
     */
    public GameLevel(LevelInformation info, KeyboardSensor ks, AnimationRunner ar, GUI gui, int startingScore) {
        this.info = info;
        this.runner = ar;
        this.keyboard = ks;
        this.gui = gui;
        this.score = new Counter();
        this.score.increase(startingScore);
    }

    /**
     * Adds a collidable to the game's environment.
     * @param c the collidable added.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the game's sprites list.
     * @param s the sprite added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes a new game: sets all of Game's members and calls other methods to
     * initialize the balls, paddle and blocks. Also initializes a score indicator.
     */
    public void initialize() {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.remainingBlocks = new Counter();
        this.remainingBlocks.increase(this.info.numberOfBlocksToRemove());
        this.remainingBalls = new Counter();
        this.remainingBalls.increase(this.info.numberOfBalls());
        this.addSprite(this.info.getBackground());
        this.initializeBallsAndPaddle();
        this.initializeBlocks();
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Point(350, 20), 20, this.score);
        scoreIndicator.addToGame(this);
        LevelIndicator levelIndicator = new LevelIndicator(new Point(600, 20), 20, this.info.levelName());
        levelIndicator.addToGame(this);
    }

    /**
     * Runs the game -- starts the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, this.gui));
        this.runner.run(this);
    }

    /**
     * Initializes all the blocks on the screen - including the bounds.
     * Adds listeners according to our need of the game - each block that should
     * be removed on impact gets a BlockRemover listener and ScoreTrackingListener,
     * and the lower bound (bound4) gets a BallRemover listener.
     */
    public void initializeBlocks() {
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        Block bound1 = new Block(new Rectangle(new Point(0, 0), 800, 25));
        Block bound2 = new Block(new Rectangle(new Point(0, 25), 25, 575));
        Block bound3 = new Block(new Rectangle(new Point(775, 25), 25, 575));
        Block bound4 = new Block(new Rectangle(new Point(25, 575), 750, 25)); // death block
        Block[] bounds = new Block[]{bound1, bound2, bound3, bound4};
        for (Block bound : bounds) {
            bound.setColor(Color.LIGHT_GRAY);
            bound.drawOutlines(true);
            bound.addToGame(this);
        }
        bound4.addHitListener(ballRemover);
        for (Block block : this.info.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
    }

    /**
     * Removes a collidable from Game's environment.
     * @param collidable the collidable we wish to remove.
     */
    public void removeCollidable(Collidable collidable) {
        this.environment.removeCollidable(collidable);
    }

    /**
     * Removes a sprite from Game's sprite collection.
     * @param sprite the sprite we wish to remove.
     */
    public void removeSprite(Sprite sprite) {
        this.sprites.removeSprite(sprite);
    }

    /**
     * Initializes the paddle and balls according to the level information.
     */
    public void initializeBallsAndPaddle() {
        Paddle paddle = new Paddle(new Rectangle(new Point(400 - Math.floor(this.info.paddleWidth() >> 1), 550),
                this.info.paddleWidth(), 25), Color.CYAN, this.keyboard);
        paddle.setSpeed(this.info.paddleSpeed());
        paddle.addToGame(this);
        for (int i = 0; i < this.info.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 543), 4, Color.WHITE);
            ball.setVelocity(this.info.initialBallVelocities().get(i));
            ball.setGameEnvironment(this.environment);
            ball.setPaddle(paddle);
            ball.drawOutlines(true);
            ball.addToGame(this);
        }
    }

    /**
     * Draws one frame of the animation.
     * @param d the drawing surface the animation will be drawn on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(0, 0, 800, 600);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
        }
        if (this.keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen();
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, pauseScreen));
        }
    }

    /**
     * Returns if an animation should stop.
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.remainingBlocks.getValue() == 0 || this.remainingBalls.getValue() == 0;
    }

    /**
     * Returns the amount of remaining balls in the game.
     * @return the amount of remaining balls in the game.
     */
    public int getBallsInt() {
        return this.remainingBalls.getValue();
    }

    /**
     * Returns the amount of remaining blocks in the game.
     * @return the amount of remaining blocks in the game.
     */
    public int getBlocksInt() {
        return this.remainingBlocks.getValue();
    }

    /**
     * Returns the score of the game.
     * @return the score of the game.
     */
    public int getScore() {
        return this.score.getValue();
    }
}
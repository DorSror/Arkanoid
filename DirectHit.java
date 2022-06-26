import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dor Sror
 * The DirectHit class, implements LevelInformation - the first default level of the game.
 */
public class DirectHit implements LevelInformation {
    /**
     * Returns the number of balls in the level.
     * In this level, it will be 1.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Returns a list with all the initial velocities of the balls.
     * Each ball will always start with a speed of 3sqrt(3).
     * @return list of velocities for each ball.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialVelocities = new ArrayList<>();
        initialVelocities.add(Velocity.fromAngleAndSpeed(0, 3 * Math.sqrt(3)));
        return initialVelocities;
    }

    /**
     * Returns the speed at which the paddle moves.
     * The speed of the paddle in this level is 10.
     * @return the speed of the paddle.
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * Returns the width of the paddle.
     * In this level, it will be 150.
     * @return width of the paddle.
     */
    public int paddleWidth() {
        return 150;
    }

    /**
     * Returns the level name as a string.
     * The level name will be displayed at the top of the screen.
     * This level's name is `Direct Hit`.
     * @return the level's name.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns a sprite with the background of the level.
     * The background should be of a night, a sea with mountains and a moon above. A target drawn over
     * the one block in the sky.
     * @return a background sprite that contains all the sprites that create said background.
     */
    public Sprite getBackground() {
        Background background = new Background();
        Block canvas = new Block(new Rectangle(new Point(-5, -5), 810, 610));
        canvas.setColor(new Color(35, 35, 35));
        Ball mountain1 = new Ball(new Point(100, 500), 140, new Color(65, 225, 65));
        Ball mountain2 = new Ball(new Point(250, 500), 105, new Color(55, 200, 55));
        Ball mountain3 = new Ball(new Point(600, 520), 80, new Color(65, 225, 65));
        Ball mountain4 = new Ball(new Point(700, 520), 50, new Color(55, 200, 55));
        Ball mountain5 = new Ball(new Point(360, 600), 155, new Color(65, 225, 65));
        Block sea = new Block(new Rectangle(new Point(-5, 510), 810, 200));
        sea.setColor(Color.BLUE);
        Ball moon = new Ball(new Point(700, 100), 50, Color.WHITE);
        Ball moonShadow = new Ball(new Point(650, 120), 80, new Color(35, 35, 35));
        Ball target1 = new Ball(new Point(400, 200), 100, Color.MAGENTA);
        Ball target1Shadow = new Ball(new Point(400, 200), 98, new Color(35, 35, 35));
        Ball target2 = new Ball(new Point(400, 200), 75, Color.MAGENTA);
        Ball target2Shadow = new Ball(new Point(400, 200), 73, new Color(35, 35, 35));
        Ball target3 = new Ball(new Point(400, 200), 50, Color.MAGENTA);
        Ball target3Shadow = new Ball(new Point(400, 200), 48, new Color(35, 35, 35));
        Block targetVertical = new Block(new Rectangle(new Point(398, 75), 5, 250));
        targetVertical.setColor(Color.MAGENTA);
        Block targetHorizontal = new Block(new Rectangle(new Point(275, 198), 250, 5));
        targetHorizontal.setColor(Color.MAGENTA);
        Sprite[] backgroundElements = new Sprite[]{canvas, mountain1, mountain2, mountain3, mountain4, mountain5, sea,
                moon, moonShadow, target1, target1Shadow, target2, target2Shadow, target3, target3Shadow,
                targetVertical, targetHorizontal};
        for (Sprite backgroundElement : backgroundElements) {
            background.addSprite(backgroundElement);
        }
        return background;
    }

    /**
     * Returns a list of blocks that make up this level, each block contains
     * its size, color and location.
     * @return a list of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(375, 175), 50, 50));
        block.setColor(Color.RED);
        block.drawOutlines(true);
        blocks.add(block);
        return blocks;
    }

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * Note that This number should be <= blocks.size().
     * @return the number of blocks that needs to be removed for the level to be cleared.
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}

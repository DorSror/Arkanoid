import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dor Sror - 207271875
 * The WideEasy class, implements LevelInformation - the second default level of the game.
 */
public class WideEasy implements LevelInformation {
    /**
     * Returns the number of balls in the level.
     * In this level, it will be 10.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * Returns a list with all the initial velocities of the balls.
     * Each ball will always start with a speed of 3sqrt(3).
     * @return list of velocities for each ball.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialVelocities = new ArrayList<>();
        double speed = 3 * Math.sqrt(3);
        for (int i = 1; i <= 5; i++) {
            initialVelocities.add(Velocity.fromAngleAndSpeed(i * 10, speed));
            initialVelocities.add(Velocity.fromAngleAndSpeed(-i * 10, speed));
        }
        return initialVelocities;
    }

    /**
     * Returns the speed at which the paddle moves.
     * The speed of the paddle in this level is 2.
     * @return the speed of the paddle.
     */
    public int paddleSpeed() {
        return 2;
    }

    /**
     * Returns the width of the paddle.
     * In this level, it will be 500.
     * @return width of the paddle.
     */
    public int paddleWidth() {
        return 625;
    }

    /**
     * Returns the level name as a string.
     * The level name will be displayed at the top of the screen.
     * This level's name is `Wide Easy`.
     * @return the level's name.
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns a sprite with the background of the level.
     * The background should be of a bright sunny day, a green ground and two green hills.
     * @return a background sprite that contains all the sprites that create said background.
     */
    public Sprite getBackground() {
        Background background = new Background();
        Block canvas = new Block(new Rectangle(new Point(-5, -5), 810, 610));
        canvas.setColor(new Color(130, 200, 255));
        Ball plain1 = new Ball(new Point(600, 1150), 800, new Color(80, 255, 80));
        Ball plain2 = new Ball(new Point(-450, 4000), 3600, new Color(67, 210, 67));
        Block soil = new Block(new Rectangle(new Point(-5, 475), 810, 200));
        Block road = new Block(new Rectangle(new Point(-5, 495), 810, 40));
        road.setColor(new Color(60, 60, 60));
        Block tile1 = new Block(new Rectangle(new Point(20, 505), 100, 20));
        Block tile2 = new Block(new Rectangle(new Point(155, 505), 100, 20));
        Block tile3 = new Block(new Rectangle(new Point(290, 505), 100, 20));
        Block tile4 = new Block(new Rectangle(new Point(425, 505), 100, 20));
        Block tile5 = new Block(new Rectangle(new Point(560, 505), 100, 20));
        Block tile6 = new Block(new Rectangle(new Point(695, 505), 100, 20));
        for (Block block : new Block[]{tile1, tile2, tile3, tile4, tile5, tile6}) {
            block.setColor(new Color(220, 220, 220));
        }
        soil.setColor(new Color(60, 200, 60));
        Ball sun1 = new Ball(new Point(85, 75), 135, new Color(245, 225, 160));
        Ball sun2 = new Ball(new Point(85, 75), 130, new Color(235, 195, 100));
        Ball sun3 = new Ball(new Point(85, 75), 125, Color.YELLOW);
        Ball cloud1 = new Ball(new Point(600, 100), 35, new Color(215, 215, 215));
        Ball cloud2 = new Ball(new Point(620, 85), 35, new Color(215, 215, 215));
        Ball cloud3 = new Ball(new Point(640, 110), 35, new Color(215, 215, 215));
        Ball cloud4 = new Ball(new Point(660, 80), 35, new Color(215, 215, 215));
        Ball cloud5 = new Ball(new Point(690, 95), 35, new Color(215, 215, 215));
        Sprite[] backgroundElements = new Sprite[]{canvas, plain1, plain2, soil, road, tile1, tile2, tile3, tile4,
                tile5, tile6, sun1, sun2,  sun3, cloud1, cloud2, cloud3, cloud4, cloud5};
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
        Color[] colorArray = new Color[]{Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.MAGENTA, Color.MAGENTA,
                Color.PINK, Color.PINK};
        for (int i = 0; i < 15; i++) {
            Block block = new Block(new Rectangle(new Point(25 + (i * 50), 275), 50, 25));
            block.setColor(colorArray[i]);
            block.drawOutlines(true);
            blocks.add(block);
        }
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

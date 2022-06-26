import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dor Sror
 * The FinalFour class, implements LevelInformation - the fourth default level of the game.
 */
public class FinalFour implements LevelInformation {
    /**
     * Returns the number of balls in the level.
     * In this level, it will be 3.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * Returns a list with all the initial velocities of the balls.
     * Each ball will always start with a speed of 3sqrt(3).
     * @return list of velocities for each ball.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialVelocities = new ArrayList<>();
        initialVelocities.add(Velocity.fromAngleAndSpeed(45, 3 * Math.sqrt(3)));
        initialVelocities.add(Velocity.fromAngleAndSpeed(-45, 3 * Math.sqrt(3)));
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
     * This level's name is `Final Four`.
     * @return the level's name.
     */
    public String levelName() {
        return "Final Four";
    }

    /**
     * Returns a sprite with the background of the level.
     * The background should be of earth at the bottom, and while going up, a change in the atmosphere's layers
     * up to space, where each layer houses at least one object in it.
     * @return a background sprite that contains all the sprites that create said background.
     */
    public Sprite getBackground() {
        Background background = new Background();
        this.setSpace(background);
        this.setExosphere(background);
        this.setThermosphere(background);
        this.setMesosphere(background);
        this.setStratosphere(background);
        this.setTroposphere(background);
        background.addSprite(new Ball(new Point(400, 4300), 3760, new Color(90, 185, 130)));
        return background;
    }

    /**
     * Adds a space and starts to the given background object.
     * @param background the given background object.
     */
    public void setSpace(Background background) {
        Block space = new Block(new Rectangle(new Point(-5, -5), 810, 810));
        space.setColor(new Color(36, 36, 36));
        background.addSprite(space);
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                Ball star1 = new Ball(new Point(50 + (i * 70), 42 + (j * 40)), 1, Color.WHITE);
                Ball star2 = new Ball(new Point(18 + (i * 70), 60 + (j * 40)), 1, Color.WHITE);
                background.addSprite(star1);
                background.addSprite(star2);
            }
        }
    }

    /**
     * Adds an exosphere and a satellite to the given background object.
     * @param background the given background object.
     */
    public void setExosphere(Background background) {
        background.addSprite(new Ball(new Point(400, 4300), 4150, new Color(60, 100, 255)));
        Block satellite = new Block(new Rectangle(new Point(480, 200), 30, 12));
        satellite.setColor(new Color(0, 50, 220));
        Block bridge = new Block(new Rectangle(new Point(492, 194), 6, 24));
        bridge.setColor(new Color(62, 62, 62));
        Block wing1 = new Block(new Rectangle(new Point(489, 176), 12, 18));
        Block wing2 = new Block(new Rectangle(new Point(489, 218), 12, 18));
        wing1.setColor(new Color(35, 165, 255));
        wing2.setColor(new Color(35, 165, 255));
        Block tail = new Block(new Rectangle(new Point(477, 197), 3, 18));
        tail.setColor(new Color(62, 62, 62));
        Ball dish = new Ball(new Point(522, 206), 12, new Color(240, 240, 240));
        Block dishShadow = new Block(new Rectangle(new Point(522, 194), 12, 24));
        dishShadow.setColor(new Color(60, 100, 255));
        Sprite[] satelliteElements = new Sprite[]{bridge, satellite, tail, wing1, wing2, dish, dishShadow};
        for (Sprite satelliteElement : satelliteElements) {
            background.addSprite(satelliteElement);
        }
    }

    /**
     * Adds a thermosphere and small auroras to the given background object.
     * @param background the given background object.
     */
    public void setThermosphere(Background background) {
        background.addSprite(new Ball(new Point(400, 4300), 4050, new Color(90, 150, 255)));
        Ball aurora1 = new Ball(new Point(200, 310), 25, new Color(135, 255, 180));
        Ball aurora1Shadow = new Ball(new Point(190, 330), 33, new Color(90, 150, 255));
        Ball aurora2 = new Ball(new Point(230, 305), 32, new Color(135, 255, 205));
        Ball aurora2Shadow = new Ball(new Point(210, 330), 30, new Color(90, 150, 255));
        Ball aurora3 = new Ball(new Point(255, 310), 27, new Color(135, 255, 180));
        Ball aurora3Shadow = new Ball(new Point(252, 325), 28, new Color(90, 150, 255));
        Ball aurora4 = new Ball(new Point(540, 315), 25, new Color(170, 120, 255));
        Ball aurora4Shadow = new Ball(new Point(550, 295), 33, new Color(90, 150, 255));
        Ball aurora5 = new Ball(new Point(560, 315), 32, new Color(180, 115, 255));
        Ball aurora5Shadow = new Ball(new Point(570, 295), 30, new Color(90, 150, 255));
        Ball aurora5Shadow2 = new Ball(new Point(550, 295), 20, new Color(90, 150, 255));
        Ball aurora6 = new Ball(new Point(602, 310), 27, new Color(160, 135, 255));
        Ball aurora6Shadow = new Ball(new Point(605, 295), 28, new Color(90, 150, 255));
        Ball[] auroras = new Ball[]{aurora1, aurora1Shadow, aurora2, aurora2Shadow, aurora3, aurora3Shadow, aurora4,
                aurora4Shadow, aurora5, aurora5Shadow, aurora5Shadow2, aurora6, aurora6Shadow};
        for (Ball aurora : auroras) {
            background.addSprite(aurora);
        }
    }

    /**
     * Adds a mesosphere and meteors to the given background object.
     * @param background the given background object.
     */
    public void setMesosphere(Background background) {
        background.addSprite(new Ball(new Point(400, 4300), 3940, new Color(120, 200, 255)));
        Ball meteor1 = new Ball(new Point(110, 420), 4, new Color(235, 95, 70));
        Ball meteor2 = new Ball(new Point(138, 392), 4, new Color(235, 95, 70));
        Ball meteor3 = new Ball(new Point(132, 412), 4, new Color(235, 95, 70));
        Ball meteor4 = new Ball(new Point(140, 440), 4, new Color(235, 95, 70));
        Ball meteor5 = new Ball(new Point(115, 385), 4, new Color(235, 95, 70));
        Ball meteor6 = new Ball(new Point(98, 392), 4, new Color(235, 95, 70));
        Ball[] meteors = new Ball[]{meteor1, meteor2, meteor3, meteor4, meteor5, meteor6};
        for (Ball meteor : meteors) {
            for (int i = 7; i > 0; i--) {
                background.addSprite(new Ball(new Point(meteor.getX() - ((7 - i) * 4),
                        meteor.getY() - ((7 - i) * 4)), i, new Color(240, 158, 120)));
            }
            background.addSprite(meteor);
        }
    }

    /**
     * Adds a stratosphere and clouds to the given background object.
     * @param background the given background object.
     */
    public void setStratosphere(Background background) {
        background.addSprite(new Ball(new Point(400, 4300), 3840, new Color(150, 207, 255)));
        Ball cloud0 = new Ball(new Point(350, 490), 15, new Color(215, 215, 215));
        Ball cloud1 = new Ball(new Point(360, 485), 15, new Color(215, 215, 215));
        Ball cloud2 = new Ball(new Point(370, 495), 15, new Color(215, 215, 215));
        Ball cloud3 = new Ball(new Point(380, 480), 15, new Color(215, 215, 215));
        Ball cloud4 = new Ball(new Point(392, 490), 15, new Color(215, 215, 215));
        Ball cloud5 = new Ball(new Point(580, 500), 15, new Color(215, 215, 215));
        Ball cloud6 = new Ball(new Point(590, 495), 15, new Color(215, 215, 215));
        Ball cloud7 = new Ball(new Point(600, 505), 15, new Color(215, 215, 215));
        Ball cloud8 = new Ball(new Point(610, 490), 15, new Color(215, 215, 215));
        Ball cloud9 = new Ball(new Point(622, 500), 15, new Color(215, 215, 215));
        Ball[] cloud = new Ball[]{cloud1, cloud2, cloud3, cloud4, cloud5, cloud6, cloud7, cloud8, cloud9, cloud0};
        for (Ball cloudElement : cloud) {
            background.addSprite(cloudElement);
        }
    }

    /**
     * Adds a troposphere and tiny mountains to the given background object.
     * @param background the given background object.
     */
    public void setTroposphere(Background background) {
        background.addSprite(new Ball(new Point(400, 4300), 3780, new Color(110, 185, 255)));
        Ball mountain1 = new Ball(new Point(180, 600), 60, new Color(90, 185, 130));
        Ball mountain2 = new Ball(new Point(300, 600), 70, new Color(90, 185, 130));
        Ball mountain3 = new Ball(new Point(720, 600), 55, new Color(90, 185, 130));
        background.addSprite(mountain1);
        background.addSprite(mountain2);
        background.addSprite(mountain3);
    }

    /**
     * Returns a list of blocks that make up this level, each block contains
     * its size, color and location.
     * @return a list of blocks.
     */
    public List<Block> blocks() {
        Color[] colorArr = new Color[] {Color.DARK_GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.MAGENTA,
                Color.GREEN, new Color(145, 105, 255)};
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < colorArr.length; i++) {
            for (int j = 0; j < 15; j++) {
                Block block = new Block(new Rectangle(new Point(25 + (j * 50), 120 + (i * 25)),
                        50, 25));
                block.setColor(colorArr[i]);
                block.drawOutlines(true);
                blocks.add(block);
            }
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

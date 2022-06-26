import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dor Sror
 * The Green3 class, implements LevelInformation - the third default level of the game.
 */
public class Green3 implements LevelInformation {
    /**
     * Returns the number of balls in the level.
     * In this level, it will be 2.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 2;
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
        return initialVelocities;
    }

    /**
     * Returns the speed at which the paddle moves.
     * The speed of the paddle in this level is 15.
     * @return the speed of the paddle.
     */
    public int paddleSpeed() {
        return 15;
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
     * This level's name is `Green 3`.
     * @return the level's name.
     */
    public String levelName() {
        return "Green 3";
    }

    /**
     * Returns a sprite with the background of the level.
     * The background should be of three buildings on a green background, each having windows and two antennae.
     * @return a background sprite that contains all the sprites that create said background.
     */
    public Sprite getBackground() {
        Background background = new Background();
        Block canvas = new Block(new Rectangle(new Point(-5, -5), 810, 810));
        canvas.setColor(new Color(0, 135, 10));
        background.addSprite(canvas);
        Block building1 = new Block(new Rectangle(new Point(100, 200), 160, 430));
        Block building2 = new Block(new Rectangle(new Point(290, 80), 160, 550));
        Block building3 = new Block(new Rectangle(new Point(540, 360), 160, 310));
        Block[] buildings = new Block[]{building1, building2, building3};
        for (Block building : buildings) {
            building.setColor(new Color(50, 50, 50));
            double buildingX = building.getCollisionRectangle().getUpperLeft().getX();
            double buildingY = building.getCollisionRectangle().getUpperLeft().getY();
            Block antenna = new Block(new Rectangle(new Point(buildingX + 73, buildingY - 150), 14, 150));
            Ball antennaGlow1 = new Ball(new Point(buildingX + 80, buildingY - 150), 20, new Color(245, 100, 90));
            Ball antennaGlow2 = new Ball(new Point(buildingX + 80, buildingY - 150), 13, new Color(230, 145, 75));
            Ball antennaGlow3 = new Ball(new Point(buildingX + 80, buildingY - 150), 8, new Color(255, 250, 75));
            Ball antennaGlow4 = new Ball(new Point(buildingX + 80, buildingY - 150), 4, new Color(240, 240, 240));
            Block antennaHighBase = new Block(new Rectangle(new Point(buildingX + 63, buildingY - 45), 34, 45));
            Block antennaLowBase = new Block(new Rectangle(new Point(buildingX + 8, buildingY - 6), 144, 6));
            antenna.setColor(new Color(90, 90, 90));
            antennaLowBase.setColor(new Color(60, 60, 60));
            antennaHighBase.setColor(new Color(70, 70, 70));
            Sprite[] upperBuildingElements = new Sprite[]{antenna, antennaGlow1, antennaGlow2, antennaGlow3,
                    antennaGlow4, antennaHighBase, antennaLowBase};
            for (Sprite upperBuildingElement : upperBuildingElements) {
                background.addSprite(upperBuildingElement);
            }
            background.addSprite(building);
            for (int i = 0; i < (building.getCollisionRectangle().getWidth() - 10) / 30; i++) {
                for (int j = 0; j < (building.getCollisionRectangle().getHeight() - 10) / 60; j++) {
                    Block window = new Block(new Rectangle(new Point(buildingX + 10 + (30 * i),
                            buildingY + 10 + (60 * j)), 20, 50));
                    window.setColor(new Color(220, 220, 220));
                    background.addSprite(window);
                }
            }
        }
        return background;
    }

    /**
     * Returns a list of blocks that make up this level, each block contains
     * its size, color and location.
     * @return a list of blocks.
     */
    public List<Block> blocks() {
        Color[] colorArr = new Color[] {new Color(100, 100, 100), Color.RED, Color.YELLOW, Color.BLUE, Color.MAGENTA};
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < colorArr.length; i++) {
            for (int j = 0; j < 10 - i; j++) {
                Block block = new Block(new Rectangle(new Point(275 + (i * 50) + (j * 50), 150 + (i * 25)),
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

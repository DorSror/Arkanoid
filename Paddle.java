import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Dor Sror - 207271875
 * Represents a Paddle made from a Rectangle shape and color. Holds a keyboard sensor for the game's GUI.
 */
public class Paddle implements Collidable, Sprite {
    private Rectangle shape;
    private final java.awt.Color color;
    private final KeyboardSensor keyboard;
    private int speed;

    /**
     * Constructs a paddle from given Rectangular shape, color and gui.
     * @param shape the given rectangular shape.
     * @param color the given color.
     * @param ks the given keyboard sensor.
     */
    public Paddle(Rectangle shape, java.awt.Color color, KeyboardSensor ks) {
        this.shape = shape;
        this.color = color;
        this.keyboard = ks;
    }

    /**
     * Sets the speed of the paddle.
     * @param speedSet the speed we want to set.
     */
    public void setSpeed(int speedSet) {
        this.speed = speedSet;
    }

    /**
     * Moves the paddle 10 pixels to the left.
     * If the upper left corner's x value is 25 then it cannot move, and it will simply return.
     */
    public void moveLeft() {
        if (this.shape.getUpperLeft().getX() == 25) {
            return;
        }
        Point point = new Point(this.shape.getUpperLeft().getX() - this.speed, this.shape.getUpperLeft().getY());
        if (point.getX() <= 25) {
            this.shape = new Rectangle(new Point(25, point.getY()), this.shape.getWidth(), this.shape.getHeight());
            return;
        }
        this.shape = new Rectangle(point, this.shape.getWidth(), this.shape.getHeight());
    }

    /**
     * Moves the paddle 10 pixels to the right.
     * If the upper right corner's x value is 775 then it cannot move, and it will simply return.
     */
    public void moveRight() {
        if (this.shape.getUpperLeft().getX() + this.shape.getWidth() == 775) {
            return;
        }
        Point point = new Point(this.shape.getUpperLeft().getX() + this.speed, this.shape.getUpperLeft().getY());
        if (point.getX() >= 775) {
            this.shape = new Rectangle(new Point(775 - this.shape.getWidth(), point.getY()), this.shape.getWidth(),
                    this.shape.getHeight());
            return;
        }
        this.shape = new Rectangle(point, this.shape.getWidth(), this.shape.getHeight());
    }

    /**
     * Returns the "collision shape" of the object.
     * @return the block's shape.
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Notify the object that we collided with at collisionPoint with a given velocity.
     * The return if the new velocity expected after the hit, based on the location of the impact across the
     * paddle: if the collision is on the upper 1/2/4/5-th fifth of the paddle, the new velocity will be the same speed
     * but on a 300 + n*30 degree.
     * Otherwise, the collision was on the 3rd fifth of the paddle and therefore the velocity isam (dx, -dy).
     * If the collision wasn't on the upper part of the paddle then it was on one of its sides and therefore the new
     * velocity is (-dx, dy).
     * @param hitter the ball that hit the block.
     * @param collisionPoint the point of the collision.
     * @param currentVelocity the current velocity of the object.
     * @return the velocity after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point upperLeft = this.shape.getUpperLeft();
        double speedVal = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        for (int i = 0; i < 5; i++) {
            if (collisionPoint.getY() == 550 && collisionPoint.getX()
                    >= (upperLeft.getX() + (i * (this.shape.getWidth() / 5)))
                    && collisionPoint.getX()
                    <= (upperLeft.getX() + ((i + 1) * (this.shape.getWidth() / 5)))) {
                if (i == 2) {
                    return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                }
                return Velocity.fromAngleAndSpeed(300 + (i * 30), speedVal);
            }
        }
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
    }

    /**
     * Draws the paddle on a drawing surface - fills the paddle with its corresponding color and draws a black
     * rectangle around it. Also draws 4 lines across it to show the 5 fifths of the paddle.
     * @param d the drawing surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
        for (int i = 1; i < 5; ++i) {
            d.drawLine((int) (this.shape.getUpperLeft().getX() + (i * (this.shape.getWidth() / 5))),
                    (int) this.shape.getUpperLeft().getY(),
                    (int) (this.shape.getUpperLeft().getX() + (i * (this.shape.getWidth() / 5))),
                    (int) this.shape.getUpperLeft().getY() + 10);
        }
    }

    /**
     * Checks if the player presses the left arrow key and/or the right arrow key. If so, it moves the paddle to the
     * direction with the corresponding key. Notice that if both keys are pressed the paddle does not move.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Adds the paddle to the game's collidable and sprite lists.
     * @param gameLevel The game object created by Ass3Game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }
}

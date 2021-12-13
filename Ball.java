import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Dor Sror - 207271875
 * Represents a Ball made by a Point center, radius r, color and velocity.
 */
public class Ball implements Sprite {
    private Point center;
    private final int r;
    private final Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Paddle paddle;
    private boolean drawOutlines = false;

    /**
     * Ball constructor - constructs a Ball using a Point, radius and color.
     * @param center the center Point of Ball.
     * @param r the radius of Ball.
     * @param color the color of Ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Ball constructor - constructs a Ball using x, y of the center Point,
     * radius and color.
     * @param centerX the x value of the center Point of Ball.
     * @param centerY the y value of the center Point of Ball.
     * @param r the radius of Ball.
     * @param color the color of Ball.
     */
    public Ball(double centerX, double centerY, int r, java.awt.Color color) {
        this.center = new Point(centerX, centerY);
        this.r = r;
        this.color = color;
    }

    /**
     * Accesses the x value of the center Point of Ball and casts it to int.
     * @return x value of Ball's center, casted to int.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Accesses the y value of the center Point of Ball and casts it to int.
     * @return y value of Ball's center, casted to int.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Accesses the radius value of Ball.
     * @return r the radius of Ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Accesses the color of Ball.
     * @return the color of Ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on a given DrawSurface. Notice this can be used only with
     * the package biuoop-1.4.jar.
     * @param surface the DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        if (this.drawOutlines) {
            surface.setColor(Color.BLACK);
            surface.drawCircle(this.getX(), this.getY(), this.getSize());
        }
    }

    /**
     * Sets the velocity of Ball to a new Velocity.
     * @param v the new Velocity of Ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of Ball to a new velocity created with dx, dy.
     * @param dx the change in position on the `x` axis.
     * @param dy the change in position on the `y` axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Accesses the Velocity of Ball.
     * @return the Velocity of Ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Applies the velocity to the center of Ball and sets it to be the new
     * center.
     */
    public void moveOneStepAss2() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Calculates the trajectory of the ball - if the trajectory has a collision with a collidable object, it will
     * change its velocity according to the collision and collidable type. After that it will call MoveOneStepAss2 to
     * move the ball one step.
     */
    public void moveOneStep() {
        Point nextPoint = new Point(this.center.getX() + this.velocity.getDx(), this.center.getY()
                + this.velocity.getDy());
        Line trajectory = new Line(this.center, nextPoint);
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity));
        } else {
            this.moveOneStepAss2();
        }
    }

    /**
     * Sets the ball's matching paddle to the game's paddle. This is used to check if the paddle contains the ball.
     * @param playerPaddle the game's paddle.
     */
    public void setPaddle(Paddle playerPaddle) {
        this.paddle = playerPaddle;
    }

    /**
     * Moves the ball one step using moveOneStep, and checks if the ball is inside the paddle and is out of bounds due
     * to a collision with two collidables at once.
     */
    public void timePassed() {
        this.moveOneStep();
        if (this.paddle.getCollisionRectangle().containsPoint(this.center) || this.center.getY() > 575) {
            this.center = new Point(this.center.getX(), 545);
        }
        if (this.center.getX() < 25) {
            this.center = new Point(30, this.center.getY());
        } else if (this.center.getX() > 775) {
            this.center = new Point(770, this.center.getY());
        }
    }

    /**
     * Sets the ball's game environment to gameEnvironment.
     * @param gameEnvironmentNew The game's GameEnvironment object.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironmentNew) {
        this.gameEnvironment = gameEnvironmentNew;
    }

    /**
     * Adds the ball to the game.
     * @param gameLevel The Game object we wish to add the ball to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Removes the ball from the game.
     * @param gameLevel The Game object we wish to remove the ball from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    /**
     * Determines if a ball should be drawn with outlines or not.
     * By default is set to true.
     * @param shouldDrawOutlines true if we want the block to be drawn with outlines.
     */
    public void drawOutlines(boolean shouldDrawOutlines) {
        this.drawOutlines = shouldDrawOutlines;
    }
}
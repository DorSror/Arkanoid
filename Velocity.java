/**
 * @author Dor Sror
 * Represents the Velocity of an Object - Velocity specifies the change in
 * position on the `x` and the `y` axes.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * The Velocity constructor.
     * @param dx the change in position on the `x` axis.
     * @param dy the change in position on the `y` axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns a Velocity using angle and speed.
     * @param angle the angle of the Velocity.
     * @param speed the speed of the Velocity.
     * @return new Velocity created from dx, dy using angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(angle * Math.PI / 180);
        double dy = speed * -Math.cos(angle * Math.PI / 180);
        return new Velocity(dx, dy);
    }

    /**
     * Accesses the dx value of Velocity.
     * @return the dx value of Velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Accesses the dy value of Velocity.
     * @return the dy value of Velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Takes a points with position (x, y) and returns a new point with the
     * position (x + dx, y + dy).
     * @param p the given Point with position (x, y).
     * @return the point with the position (x + dx, y + dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
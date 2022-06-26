/**
 * @author Dor Sror
 * Represents a Point made by two coordinates - x, y.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * The Point constructor.
     * @param x the x value of Point.
     * @param y the y value of Point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the distance of this point to the other point.
     * @param other the Point you want to compare distance from.
     * @return the distance between the points 'this' and 'other'.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2)
                + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * Check if two points are equal.
     * @param other the Point you want to compare similarity to.
     * @return true if the points are the same, false otherwise.
     */
    public boolean equals(Point other) {
        return (this.x == other.x && this.y == other.y);
    }

    /**
     * Accesses the x value of Point.
     * @return this.x the x value of Point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Accesses the y value of Point.
     * @return this.y the y value of Point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Truncates the x and y values of the point.
     * @return a new Point made from the truncated x and y values.
     */
    public Point truncatePoint() {
        double xTruncated = Math.floor(this.getX());
        double yTruncated = Math.floor(this.getY());
        if (this.getX() % 1 > 0.5) {
            xTruncated += 1;
        }
        if (this.getY() % 1 > 0.5) {
            yTruncated += 1;
        }
        return new Point(xTruncated, yTruncated);
    }
}
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dor Sror
 * A rectangle represented by a Point (upper left point of the rectangle), width and height.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Constructs a new rectangle with given Point, width and height.
     * @param upperLeft the given point of the rectangle.
     * @param width the given width of the rectangle.
     * @param height the given height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a list of intersection points of the rectangle with a specified line.
     * Checks for each line of the rectangle if it intersects with the rectangle and add the intersection points to
     * the list.
     * Notice the list can be empty.
     * @param line the specified line.
     * @return the list of intersection points with the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointsList = new ArrayList<>();
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperRight.getX(), upperRight.getY() + height);
        Point intersection = line.intersectionWith(new Line(upperLeft, upperRight));
        if (intersection != null) {
            pointsList.add(intersection);
        }
        intersection = line.intersectionWith(new Line(upperLeft, lowerLeft));
        if (intersection != null) {
            pointsList.add(intersection);
        }
        intersection = line.intersectionWith(new Line(upperRight, lowerRight));
        if (intersection != null) {
            pointsList.add(intersection);
        }
        intersection = line.intersectionWith(new Line(lowerLeft, lowerRight));
        if (intersection != null) {
            pointsList.add(intersection);
        }
        return pointsList;
    }

    /**
     * Returns the width of the rectangle.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper left corner of the rectangle.
     * @return the upper left corner of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Checks if the rectangle contains a point (and the point is not on its perimeter).
     * @param point the given point.
     * @return true if the point is contained inside the rectangle, false otherwise.
     */
    public Boolean containsPoint(Point point) {
        double upperLeftX = this.getUpperLeft().getX();
        double upperLeftY = this.getUpperLeft().getY();
        return point.getX() > upperLeftX && point.getX() < upperLeftX + this.getWidth()
                && point.getY() > upperLeftY && point.getY() < upperLeftY + this.getHeight();
    }
}
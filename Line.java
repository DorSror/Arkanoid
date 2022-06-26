import java.util.List;

/**
 * @author Dor Sror
 * Represents a Line made by two points - start and end.
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Line constructor - constructs a Line using two given Points.
     * @param start the starting Point of Line.
     * @param end the ending Point of Line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Line constructor - constructs a line using four coordinates for two
     * points.
     * @param x1 the x value of Point start.
     * @param y1 the y value of Point start.
     * @param x2 the x value of Point end.
     * @param y2 the y value of Point end.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of Line.
     * @return distance between the start and the end of Line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle Point of Line.
     * @return the middle Point of Line.
     */
    public Point middle() {
        double middleX = (this.end.getX() + this.start.getX()) / 2;
        double middleY = (this.end.getY() + this.start.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * Accesses the starting Point of Line.
     * @return this.start the starting Point of Line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Accesses the ending Point of Line.
     * @return this.end the ending Point of Line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     * The method in which it is determined if both lines intersect is
     * orientations - the sign of (P - Q)*(R - P) where P, Q, R are points and
     * the multiplication of two points is defined as (Px * Qy) - (Py * Qx).
     * @param other the Line you want to check intersection with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double tsX = this.start.getX();
        double tsY = this.start.getY();
        double teX = this.end.getX();
        double teY = this.end.getY();
        double osX = other.start.getX();
        double osY = other.start.getY();
        double oeX = other.end.getX();
        double oeY = other.end.getY();

        // Calculating the orientations
        double o1 = Math.signum((teY - tsY) * (osX - teX) - (teX - tsX) * (osY - teY));
        double o2 = Math.signum((teY - tsY) * (oeX - teX) - (teX - tsX) * (oeY - teY));
        double o3 = Math.signum((oeY - osY) * (tsX - oeX) - (oeX - osX) * (tsY - oeY));
        double o4 = Math.signum((oeY - osY) * (teX - oeX) - (oeX - osX) * (teY - oeY));

        // The cases where the lines are co-linear
        if ((o1 == 0 && o2 == 0) || (o3 == 0 && o4 == 0)) {
            boolean statement1 = Math.min(osX, oeX) <= Math.max(tsX, teX) && Math.min(tsX, teX) <= Math.max(osX, oeX);
            boolean statement2 = Math.min(osY, oeY) <= Math.max(tsY, teY) && Math.min(tsY, teY) <= Math.max(osY, oeY);
            return statement1 && statement2;
        }

        // General case
        return o1 != o2 && o3 != o4;
    }

    /**
     * Checks if two parallel Lines (this and other) have more than one
     * intersection.
     * @param other the Line you want to check intersection with.
     * @return true if more than one intersection exists, false otherwise.
     */
    private boolean multipleIntersections(Line other) {
        double borderMin = Math.min(this.start.getY(), this.end.getY());
        double borderMax = Math.max(this.start.getY(), this.end.getY());
        double check = other.start.getY();
        if (this.start.getX() == this.end.getX()) {
            return check < borderMax && check > borderMin;
        }
        borderMin = Math.min(this.start.getX(), this.end.getX());
        borderMax = Math.max(this.start.getX(), this.end.getX());
        check = other.start.getX();
        return check < borderMax && check > borderMin;
    }

    /**
     * Gives the intersection point of two lines that are known to intersect
     * and are not vertical.
     * @param other the Line you want to check intersection with.
     * @return the Point of intersection, null if there is more than one.
     */
    private Point intersectionPoint(Line other) {
        double slopeThis = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double slopeOther = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double constantThis = this.start.getY() - slopeThis * this.start.getX();
        double constantOther = other.start.getY() - slopeOther * other.start.getX();
        if (slopeThis == slopeOther) {
            if (multipleIntersections(other)) {
                return null;
            }
            if (this.start.equals(other.start)) {
                return other.start();
            }
            return other.end();
        }
        double pointX = (constantOther - constantThis) / (slopeThis - slopeOther);
        double pointY = (slopeThis * pointX) + constantThis;
        return new Point(pointX, pointY);
    }

     /**
     * Gives the intersection point between Lines this and other.
     * @param other the Line you want to check intersection with.
     * @return the Point of intersection, or null if there is no intersection
     * Point or if there are more than one.
     */
    public Point intersectionWith(Line other) {
        double dxThis = this.end.getX() - this.start.getX();
        double dyThis = this.end.getY() - this.start.getY();
        double dxOther = other.end.getX() - other.start.getX();
        double dyOther = other.end.getY() - other.start.getY();

        // Check for intersection
        if (!this.isIntersecting(other)) {
            return null;
        }

        // Check for special cases where at least one of the Lines are vertical
        if (dxThis == 0 && dxOther != 0) {
            return new Point(this.end.getX(), (dyOther / dxOther) * (other.start.getX() - this.end.getX())
                    + other.start.getY());
        }
        if (dxThis != 0 && dxOther == 0) {
            return new Point(other.end.getX(), (dyThis / dxThis) * (this.start.getX() - other.end.getX())
                    + this.start.getY());
        }
        if (dxThis == 0 && dxOther == 0) {
            if (multipleIntersections(other)) {
                return null;
            }
            if (this.start.equals(other.start)) {
                return other.start();
            }
            return other.end();
        }
        return intersectionPoint(other);
    }

    /**
     * Checks if two lines are equal - meaning both the start and end of this
     * Line are equal to start and end (or end and start).
     * @param other the Line you want to check equality with.
     * @return true if the Lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return this.start.equals(other.end) && this.end.equals(other.start);
    }

    /**
     * If this line does not intersect with the given rectangle, returns null.
     * Otherwise, returns the closest intersection point ot the start of the line.
     * @param rect the given rectangle.
     * @return the point of the closest intersection to this line's start.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.size() == 0) {
            return null;
        } else if (intersectionPoints.size() == 1) {
            return intersectionPoints.get(0);
        }
        if (intersectionPoints.get(0).distance(this.start) <= intersectionPoints.get(1).distance(this.start)) {
            return intersectionPoints.get(0);
        }
        return intersectionPoints.get(1);
    }
}
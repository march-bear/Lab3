package world.squares;

import world.points.Point;

public abstract class Square {
    protected final Point bottomLeftPoint;
    protected final Point topRightPoint;
    protected final String name;

    Square(String name, Point bottomLeftPoint, Point topRightPoint) {
        this.bottomLeftPoint = bottomLeftPoint;
        this.topRightPoint = topRightPoint;
        this.name = name;
    }

    public Point getBottomLeftPoint() {
        return bottomLeftPoint;
    }

    public Point getTopRightPoint() {
        return topRightPoint;
    }

    public String getName() {
        return name;
    }

    boolean isIncludedPoint(Point point) {
        return (point.getX() >= bottomLeftPoint.getX() && point.getY() >= bottomLeftPoint.getY() &&
                point.getX() <= topRightPoint.getX() && point.getY() <= topRightPoint.getY());
    }

    boolean isIntersecting(Square other) {
        return (isIncludedPoint(other.bottomLeftPoint) || isIncludedPoint(other.topRightPoint) ||
                isIncludedPoint(new Point(other.bottomLeftPoint.getX(), other.topRightPoint.getY())) ||
                isIncludedPoint(new Point(other.topRightPoint.getX(), other.bottomLeftPoint.getY())));
    }
}

package world.squares;

import world.points.Point;

public abstract class Square {
    protected Point bottomLeftPoint;
    protected Point topRightPoint;
    protected String name;

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
        return (point.x >= bottomLeftPoint.x && point.y >= bottomLeftPoint.y &&
                point.x <= topRightPoint.x && point.y <= topRightPoint.y);
    }

    boolean isIntersecting(Square other) {
        return (isIncludedPoint(other.bottomLeftPoint) || isIncludedPoint(other.topRightPoint) ||
                isIncludedPoint(new Point(other.bottomLeftPoint.x, other.topRightPoint.y)) ||
                isIncludedPoint(new Point(other.topRightPoint.x, other.bottomLeftPoint.y)));
    }
}

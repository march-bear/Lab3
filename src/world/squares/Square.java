package world.squares;

import world.points.Point;

public abstract class Square {
    protected final Point bottomLeftPoint;
    protected final Point topRightPoint;
    protected final String name;

    Square(String name) {
        this(name, new Point(), new Point());
    }

    Square(String name, Point bottomLeftPoint, Point topRightPoint) {
        int bottomLeftPointX = bottomLeftPoint.getX();
        int bottomLeftPointY = bottomLeftPoint.getY();
        int topRightPointX = topRightPoint.getX();
        int topRightPointY = topRightPoint.getY();

        if (bottomLeftPoint.equals(topRightPoint)) {
            if (topRightPointX != Point.maxXY) topRightPointY++;
            else bottomLeftPointX--;

            if (topRightPointY != Point.maxXY) topRightPointY++;
            else bottomLeftPointY--;
        } else {
            if (bottomLeftPointX > topRightPointX) {
                int tmp = bottomLeftPointX;
                bottomLeftPointX = topRightPointX;
                topRightPointX = tmp;
            }

            if (bottomLeftPointY > topRightPointY) {
                int tmp = bottomLeftPointY;
                bottomLeftPointY = topRightPointY;
                topRightPointY = tmp;
            }
        }
        this.bottomLeftPoint = new Point(bottomLeftPointX, bottomLeftPointY);
        this.topRightPoint = new Point(topRightPointX, topRightPointY);
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

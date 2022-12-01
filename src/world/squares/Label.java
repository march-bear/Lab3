package world.squares;

import world.points.Point;
import world.squares.Square;

public class Label extends Area {
    final Point centerPoint;
    final int radius;

    public Label(String name) {
        this(name, new Point(Point.randomXY(), Point.randomXY()));
    }
    private Label(String name, Point centerPoint) {
        this(name, centerPoint, 1);
    }

    Label(String name, Point centerPoint, int radius) {
        super(name, new Point(centerPoint.getX() - radius, centerPoint.getY() - radius),
                new Point(centerPoint.getX() + radius, centerPoint.getY() + radius));
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }
}

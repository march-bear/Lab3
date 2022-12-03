package world.squares;

import world.Correctors;
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
        this.centerPoint = new Point(
                Correctors.correctInt(centerPoint.getX(), 1, Point.maxXY - 1),
                Correctors.correctInt(centerPoint.getY(), 1, Point.maxXY - 1)
                );
        this.radius = radius;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }
}

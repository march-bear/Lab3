package world.squares;

import world.points.Point;
import world.squares.Square;

public class Label extends Square {
    int xCenter;
    int yCenter;
    int radius;

    Label(String name, int xCenter, int yCenter) {
        this(name, xCenter, yCenter, 1);
    }

    Label(String name, int xCenter, int yCenter, int radius) {
        super(name, new Point(xCenter - radius, yCenter - radius),
                new Point(xCenter + radius, yCenter + radius));
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.radius = radius;
    }
}

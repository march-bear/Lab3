package world.squares;

import world.points.Point;
import world.squares.Square;

public class Area extends Square {
    protected final double audibility;
    protected final double visibility;

    public Area(String name, Point bottomLeftPoint, Point topRightPoint) {
        this(name, bottomLeftPoint, topRightPoint, 1.0, 1.0);
    }

    public Area(String name, Point bottomLeftPoint, Point topRightPoint, double audibility, double visibility) {
        super(name, bottomLeftPoint, topRightPoint);
        if (audibility > 1.0)
            audibility = 1.0;
        else if (audibility < 0.0)
            audibility = 0.0;

        if (visibility > 1.0)
            visibility = 1.0;
        else if (visibility < 0.0)
            visibility = 0.0;

        this.audibility = audibility;
        this.visibility = visibility;
    }

    public double getAudibility() {
        return audibility;
    }

    public double getVisibility() {
        return visibility;
    }
}

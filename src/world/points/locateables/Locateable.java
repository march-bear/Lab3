package world.points.locateables;

import world.squares.Square;
import world.points.Point;

public abstract class Locateable extends Point {
    protected String name;
    protected Square square;

    public Locateable(int x, int y, String name, Square square) {
        super(x, y);
        this.name = name;
        this.square = square;
    }

    public String getName() {
        return name;
    }

    public Square getSquare() {
        return square;
    }

    public int distanceTo(Locateable other) {
        return 12;
    }
}

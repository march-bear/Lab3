package world.points.locateables;

import world.squares.Square;
import world.points.Point;

public abstract class Locateable extends Point {
    protected Square square;

    public Locateable(int x, int y, String name, Square square) {
        super(x, y, name);
        this.square = square;
    }

    protected void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Square getSquare() {
        return square;
    }

    public int distanceTo(Locateable other) {
        return 12;
    }
}

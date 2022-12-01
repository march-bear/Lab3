package world.points.locateables;

import world.squares.Square;
import world.points.Point;

public abstract class Locateable extends Point {
    protected Square square;

    public Locateable(int x, int y, String name, Square square) {
        super(x, y, name);
        this.square = square;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinates(int x, int y) {
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

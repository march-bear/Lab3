package world.points.locateables.creatures;

import world.points.locateables.Locateable;
import world.squares.Square;

public abstract class Creature extends Locateable implements IMove, IMakeSound {
    int speed;

    public Creature(String name, Square square) {
        this(name, square, 3);
    }

    public Creature(String name, Square square, int speed) {
        this(randomXY(), randomXY(), name, square, speed);
    }

    public Creature(int x, int y, String name, Square square, int speed) {
        super(x, y, name, square);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}

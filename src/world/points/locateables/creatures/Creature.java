package world.points.locateables.creatures;

import world.Correctors;
import world.points.locateables.Locateable;
import world.squares.Square;

import java.util.Objects;

public abstract class Creature extends Locateable implements IMove {
    protected int speed;

    public Creature(String name, Square square) {
        this(name, square, 1);
    }

    public Creature(String name, Square square, int speed) {
        this(randomX(square), randomY(square), name, square, speed);
    }

    public Creature(int x, int y, String name, Square square, int speed) {
        super(x, y, name, square);
        this.speed = Correctors.correctInt(speed, 1, maxXY / 10 + 1);
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void move() {

    }

    @Override
    public boolean move(int deltaX, int deltaY) {
        return false;
    }

    @Override
    public String toString() {
        return "Существо " + name + "\n" +
                "Координаты: (" + x + ", " + y + ")\n" +
                "Локация: " + square.getName() + "\n" +
                "Скорость передвижения: " + speed;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj))
            return ((Creature) obj).speed == this.speed;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, name, square, speed);
    }
}

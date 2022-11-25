package world.points.locateables.creatures;

import world.points.locateables.SoundType;
import world.points.locateables.Sound;
import world.squares.Square;

public class Penguin extends Creature {

    public Penguin(String name, Square square) {
        super(name, square);
    }

    public Penguin(String name, Square square, int speed) {
        super(name, square, speed);
    }

    public Penguin(int x, int y, String name, Square square, int speed) {
        super(x, y, name, square, speed);
    }

    @Override
    public Sound makeSound() {
        return new Sound(x, y, "Крик пингвина " + name, SoundType.PENGUIN_SCREAM, (int) ((Math.random() * 2) + 2));
    }

    @Override
    public void move() {

    }
}

package world.points.locateables;

import world.points.locateables.creatures.CreatureType;
import world.squares.Square;

import java.util.ArrayList;

public final class GroupOfCreatures<T> extends Locateable {
    final private ArrayList<T> creatures;
    final private CreatureType type;

    public GroupOfCreatures(String name, Square square, CreatureType type) {
        this((int) (Math.random() * (maxXY + 1)), (int) (Math.random() * (maxXY + 1)), name, square, type);
    }

    public GroupOfCreatures(int x, int y, String name, Square square, CreatureType type) {
        super(x, y, name, square);
        this.type = type;
        creatures = new ArrayList<T>();
    }

    public CreatureType getType() {
        return type;
    }

    public void addCreature(T creature) {
        creatures.add(creature);
    }

    public void removeCreature(T creature) {
        creatures.remove(creature);
    }

    public int getNumberOfCreatures() {
        return creatures.size();
    }

    public T getCreature(int index) {
        return creatures.get(index);
    }
}

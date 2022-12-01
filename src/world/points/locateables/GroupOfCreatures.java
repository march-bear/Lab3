package world.points.locateables;

import world.points.locateables.creatures.CreatureType;
import world.squares.Square;

import java.util.ArrayList;

public final class GroupOfCreatures<T> extends Locateable {
    final private ArrayList<T> creatures;

    public GroupOfCreatures(String name, Square square) {
        this(randomX(square), randomY(square), name, square);
    }

    public GroupOfCreatures(int x, int y, String name, Square square) {
        super(x, y, name, square);
        creatures = new ArrayList<T>();
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

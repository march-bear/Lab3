package world.points.locateables;

import world.Correctors;
import world.points.locateables.creatures.Creature;
import world.points.locateables.creatures.IMove;
import world.squares.Square;

import java.util.ArrayList;

public final class GroupOfCreatures<T> extends Locateable implements IMove {
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

    public int getNumberOfCreatures() {
        return creatures.size();
    }

    public T getCreature(int index) {
        return creatures.get(index);
    }

    @Override
    public void move() {
        int speed = ((Creature) creatures.get(0)).getSpeed();
        int deltaX = (int) (Math.random() * (2 * speed + 1) - speed);
        int deltaY = (speed - Math.abs(deltaX)) * (int) Math.pow(-1, Math.random() * 1 + 1);

        if (this.x + deltaX != Correctors.correctInt(this.x + deltaX,
                square.getBottomLeftPoint().getX(), square.getTopRightPoint().getX()))
            deltaX = -deltaX;

        if (this.x + deltaY != Correctors.correctInt(this.x + deltaY,
                square.getBottomLeftPoint().getY(), square.getTopRightPoint().getY()))
            deltaY = -deltaY;

        this.setPosition(this.x + deltaX, this.y + deltaY);
    }

    @Override
    public boolean move(int deltaX, int deltaY) {
        boolean flag = this.x + deltaX == Correctors.correctInt(this.x + deltaX,
                square.getBottomLeftPoint().getX(), square.getTopRightPoint().getX()) ||
                this.x + deltaY == Correctors.correctInt(this.x + deltaY,
                        square.getBottomLeftPoint().getY(), square.getTopRightPoint().getY());

        setPosition(this.x + deltaX, this.y + deltaY);
        return flag;
    }
}

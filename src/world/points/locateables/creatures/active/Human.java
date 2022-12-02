package world.points.locateables.creatures.active;

import world.points.locateables.SoundType;
import world.points.locateables.Sound;
import world.points.locateables.creatures.Creature;
import world.squares.Label;
import world.squares.MainlandAntarctica;

public final class Human extends ActiveCreature {
    public Human(String name) {
        this(name, 0, 0);
    }
    public Human(String name, int x, int y) {
        super(100, name, MainlandAntarctica.getInstance(), 10, x, y, 2);
    }

    @Override
    public void move() {

    }

    @Override
    public void attackTarget(ActiveCreature target) {

    }

    @Override
    public void useSpecialAttack(ActiveCreature target) {

    }

    @Override
    public String toString() {
        return "человек " + name + "\n" +
                "Координаты: (" + x + ", " + y + ")\n" +
                "Локация: " + square.getName() + "\n" +
                "Скорость передвижения: " + speed + "\n" +
                "HP: " + hp + "\n" +
                "Состояние: " + condition.getName();
    }
}

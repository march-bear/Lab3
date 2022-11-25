package world.points.locateables.creatures.active;

import world.points.locateables.SoundType;
import world.points.locateables.Sound;
import world.points.locateables.creatures.Creature;
import world.squares.Label;
import world.squares.MainlandAntarctica;

public final class Human extends ActiveCreature {
    Human(String name, int x, int y) {
        super(100, name, MainlandAntarctica.getInstance(), 10, x, y, 2);
    }

    @Override
    public Sound makeSound() {
        return new Sound(this.x, this.y, "Речь " + this.name, SoundType.SPEECH, 3);
    }

    @Override
    public void move() {

    }

    @Override
    public void goTo(Label target) {

    }

    @Override
    public void goTo(Creature target) {

    }

    @Override
    public void attackTarget(ActiveCreature target) {

    }

    @Override
    public void useSpecialAttack(ActiveCreature target) {

    }
}

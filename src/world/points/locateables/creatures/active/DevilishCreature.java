package world.points.locateables.creatures.active;

import world.points.locateables.SoundType;
import world.points.locateables.Sound;
import world.points.locateables.creatures.Creature;
import world.squares.Label;
import world.squares.Square;

public class DevilishCreature extends ActiveCreature {
    final double bloodlust;
    final int senseOfSmell;

    public DevilishCreature(String name, Square square, int x, int y, double bloodlust, int senseOfSmell) {
        super(45, name, square, (int) (Math.random() * 2 + 1), x, y, 1);
        this.bloodlust = bloodlust;
        this.senseOfSmell = senseOfSmell;
    }

    public DevilishCreature(String name, Square square, double bloodlust, int senseOfSmell) {
        this(name, square, randomX(square), randomY(square), bloodlust, senseOfSmell);
    }

    public DevilishCreature(String name, Square square) {
        this(name, square, 0.5, 1);
    }

    @Override
    public Sound makeSound() {
        return new Sound(x, y, "Крик дьявольской твари", SoundType.MONSTER_SCREAM, 6);
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
        target.loseHP(this.attackDamage);
    }

    @Override
    public void useSpecialAttack(ActiveCreature target) {
        target.loseHP(this.attackDamage * 2);
    }
}

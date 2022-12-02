package world.points.locateables.creatures.active;

import world.points.locateables.Locateable;
import world.points.locateables.creatures.Condition;
import world.points.locateables.creatures.Creature;
import world.squares.Label;
import world.squares.Square;

import java.util.Objects;

public abstract class ActiveCreature extends Creature {
    protected int hp;
    protected final int attackDamage;
    protected Condition condition = Condition.HEALTHY;

    ActiveCreature(int hp, String name, Square square) {
        this(hp, name, square, hp / 5 + ((hp < 5) ? 1 : 0));
    }

    ActiveCreature(int hp, String name, Square square, int attackDamage) {
        this(hp, name, square, attackDamage, randomXY(), randomXY(), 1);
    }

    ActiveCreature(int hp, String name, Square square, int attackDamage, int x, int y, int speed) {
        super(x, y, name, square, speed);
        this.hp = hp;
        this.attackDamage = attackDamage;
    }

    public int getHp() {
        return hp;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    private boolean goTo(int x, int y) {
        int deltaX = (Math.abs(x - this.x) >= speed ? speed : 0) * ((x> this.x) ? 1 : -1);
        this.x += deltaX;
        int deltaY = (Math.abs(y - this.y) >= speed - Math.abs(deltaX) ? speed - Math.abs(deltaX) : 0) *
                ((y > this.y) ? 1 : -1);
        this.y += deltaY;
        return this.x == x && this.y == y;
    }
    public boolean goTo(Label target) {
        return goTo(target.getCenterPoint().getX(), target.getCenterPoint().getY());
    }

    public boolean goTo(Locateable target) {
        return goTo(target.getX(), target.getY());
    }

    public abstract void attackTarget(ActiveCreature target);

    protected abstract void useSpecialAttack(ActiveCreature target);

    public void loseHP(int damage) {
        if (damage > 0) {
            if (damage >= this.hp)
                this.hp = 0;
            else
                this.hp -= damage;
        }
    }

    @Override
    public String toString() {
        return "активное существо " + name + "\n" +
                "Координаты: (" + x + ", " + y + ")\n" +
                "Локация: " + square.getName() + "\n" +
                "Скорость передвижения: " + speed + "\n" +
                "HP: " + hp + "\n" +
                "Состояние: " + condition.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj))
            return ((ActiveCreature) obj).getHp() == this.hp &&
                    ((ActiveCreature) obj).getAttackDamage() == this.attackDamage;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, name, square, speed, attackDamage);
    }
}

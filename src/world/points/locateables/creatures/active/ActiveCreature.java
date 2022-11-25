package world.points.locateables.creatures.active;

import world.points.locateables.creatures.Condition;
import world.points.locateables.creatures.Creature;
import world.squares.Label;
import world.squares.Square;

public abstract class ActiveCreature extends Creature {
    int hp;
    int attackDamage;
    Condition condition;

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

    public abstract void goTo(Label target);

    public abstract void goTo(Creature target);

    public abstract void attackTarget(ActiveCreature target);

    public abstract void useSpecialAttack(ActiveCreature target);

    public void loseHP(int damage) {
        if (damage > 0) {
            if (damage >= this.hp)
                this.hp = 0;
            else
                this.hp -= damage;
        }
    }
}

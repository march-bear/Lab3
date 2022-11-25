package world;

import world.points.locateables.GroupOfCreatures;
import world.points.locateables.creatures.*;
import world.points.locateables.creatures.active.ActiveCreature;
import world.points.locateables.creatures.active.DevilishCreature;
import world.points.locateables.creatures.active.Human;
import world.squares.MainlandAntarctica;

import java.util.ArrayList;

public final class World {
    private World instance;
    final private MainlandAntarctica mainland;
    final private GroupOfCreatures<Human> people;
    final private GroupOfCreatures<Penguin> penguins;
    final private ArrayList<DevilishCreature> devilishCreatures;

    private World() {
        mainland = MainlandAntarctica.getInstance();
        people = new GroupOfCreatures<Human>("Люди", mainland, CreatureType.HUMAN);
        penguins = new GroupOfCreatures<Penguin>("Пингвины", mainland, CreatureType.PENGUIN);
        devilishCreatures = new ArrayList<DevilishCreature>();
    }

    public World getInstance() {
        if (instance == null)
            instance = new World();
        return instance;
    }

    public void addGroupOfPeople(Human... people) {
        if (this.people.getNumberOfCreatures() != 0)
            return;
        for (Human human: people) {
            this.people.addCreature(human);
        }
    }

    public void addGroupOfPenguins(Penguin... penguins) {
        if (this.penguins.getNumberOfCreatures() != 0)
            return;
        for (Penguin penguin: penguins) {
            this.penguins.addCreature(penguin);
        }
    }

    public void addMonster() {
        devilishCreatures.add(new DevilishCreature("Тварь" + (devilishCreatures.size() + 1),
                mainland.getIndexArea((int) (Math.random() * (mainland.sizeOfAreas() + 1)))));
    }

    public void startBattle(ActiveCreature creature1, ActiveCreature creature2) {

    }

    public void startBattle(DevilishCreature devilishCreature, GroupOfCreatures<Human> people) {

    }

    public void run() {

    }
}

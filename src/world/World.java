package world;

import world.points.Point;
import world.points.locateables.GroupOfCreatures;
import world.points.locateables.creatures.*;
import world.points.locateables.creatures.active.ActiveCreature;
import world.points.locateables.creatures.active.DevilishCreature;
import world.points.locateables.creatures.active.Human;
import world.squares.Area;
import world.squares.Label;
import world.squares.MainlandAntarctica;

import java.util.ArrayList;

public final class World {
    private static World instance;
    public final MainlandAntarctica mainland;
    private final GroupOfCreatures<Human> people;
    private final GroupOfCreatures<Penguin> penguins;
    private final ArrayList<DevilishCreature> devilishCreatures;
    private int maxNumberOfMonsters = 5;
    public final Label finish;

    private World() {
        mainland = MainlandAntarctica.getInstance();
        finish = new Label("Пещерка пингвинов");
        people = new GroupOfCreatures<Human>("Люди", mainland, CreatureType.HUMAN);
        penguins = new GroupOfCreatures<Penguin>("Пингвины", finish, CreatureType.PENGUIN);
        devilishCreatures = new ArrayList<DevilishCreature>();
    }

    public static synchronized World getInstance() {
        if (instance == null)
            instance = new World();
        return instance;
    }

    public boolean setGroupOfPeople(Human firstHuman, Human... people) {
        if (this.people.getNumberOfCreatures() != 0)
            return false;

        this.people.addCreature(firstHuman);
        for (Human human: people) {
            this.people.addCreature(human);
        }
        return true;
    }

    public Point getGroupOfPeople() {
        return new Point(people.getX(), people.getY(), people.getName());
    }

    public boolean setGroupOfPenguins(Penguin firstPenguin, Penguin... penguins) {
        if (this.penguins.getNumberOfCreatures() != 0)
            return false;

        this.penguins.addCreature(firstPenguin);
        for (Penguin penguin: penguins) {
            this.penguins.addCreature(penguin);
        }
        return true;
    }

    public Point getGroupOfPenguins() {
        return new Point(penguins.getX(), penguins.getY(), penguins.getName());
    }

    // FIXME
    private void addMonster(String name, double bloodlust, int senseOfSmell) {
        devilishCreatures.add(new DevilishCreature(
                name,
                mainland.getArea((int) (Math.random() * (mainland.sizeOfAreas() + 1))), bloodlust, senseOfSmell)
        );
    }

    public int getNumberOfMonster() {
        return devilishCreatures.size();
    }

    public Point getMonster(int index) {
        Point monster = devilishCreatures.get(index);
        return new Point(monster.getX(), monster.getY(), monster.getName());
    }

    public void startBattle(ActiveCreature creature1, ActiveCreature creature2) {

    }

    public void startBattle(DevilishCreature devilishCreature, GroupOfCreatures<Human> people) {

    }

    public void setMaxNumberOfMonsters(int number) {
        if (number > 10)
            this.maxNumberOfMonsters = 10;
        else this.maxNumberOfMonsters = Math.max(number, 3);
    }
    public boolean run() {
        if (this.people.getNumberOfCreatures() == 0) {
            System.out.println("Симуляция не может быть запущена: группа людей не определена");
            return false;
        } else if (this.penguins.getNumberOfCreatures() == 0) {
            System.out.println("Симуляция не может быть запущена: группа пингвинов не определена.");
            return false;
        }

        mainland.addArea(new Area("Земля1", new Point(), new Point()));
        mainland.addArea(new Area("Земля1", new Point(), new Point()));
        mainland.addArea(new Area("Земля1", new Point(), new Point()));

        System.out.println("Подготовка к запуску симуляции...");
        for (int i = 0; i < maxNumberOfMonsters / 2 + 1; ++i) {
            addMonster("Монстр" + (i + 1), Math.random() * 9 + 1, (int) (Math.random() * 3 + 3));
            System.out.println("Добавлена тварь " + devilishCreatures.get(i).getName());
        }

        WorldMap map = new WorldMap(
                this,
                mainland.getTopRightPoint().getY() - mainland.getBottomLeftPoint().getY() + 1,
                mainland.getTopRightPoint().getX() - mainland.getBottomLeftPoint().getX() + 1
        );
        map.print();
        return true;
    }
}

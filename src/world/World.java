package world;

import world.points.Point;
import world.points.locateables.GroupOfCreatures;
import world.points.locateables.Sound;
import world.points.locateables.SoundType;
import world.points.locateables.creatures.*;
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
        people = new GroupOfCreatures<Human>("Люди", mainland);
        penguins = new GroupOfCreatures<Penguin>("Пингвины", finish);
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

    private boolean addMonster(String name, double bloodlust, int senseOfSmell) {
        if (getNumberOfMonster() < maxNumberOfMonsters) {
            devilishCreatures.add(new DevilishCreature(name,
                    mainland.getArea((int) (Math.random() * (mainland.sizeOfAreas() + 1))), bloodlust, senseOfSmell));
            return true;
        }
        return false;
    }

    public int getNumberOfMonster() {
        return devilishCreatures.size();
    }

    public Point getMonster(int index) {
        Point monster = devilishCreatures.get(index);
        return new Point(monster.getX(), monster.getY(), monster.getName());
    }

    private boolean startBattle(Human human, DevilishCreature monster) {
        while (human.getHp() != 0 && monster.getHp() != 0 && monster.getCondition() != Condition.CONFUSED) {
            human.attackTarget(monster);
            monster.attackTarget(human);
        }
        return human.getHp() != 0;
    }

    private boolean startBattle(DevilishCreature monster, GroupOfCreatures<Human> people) {
        for (int i = 0; i < people.getNumberOfCreatures(); ++i) {
            Human curr_human = people.getCreature(i);
            if (startBattle(curr_human, monster))
                break;
        }
        return monster.getHp() == 0;
    }

    public void setMaxNumberOfMonsters(int number) {
        maxNumberOfMonsters = Correctors.correctInt(number, Point.maxXY / 5, Point.maxXY / 5 * 2);
    }

    public boolean run() {
        if (this.people.getNumberOfCreatures() == 0) {
            System.out.println("Симуляция не может быть запущена: группа людей не определена");
            return false;
        } else if (this.penguins.getNumberOfCreatures() == 0) {
            System.out.println("Симуляция не может быть запущена: группа пингвинов не определена.");
            return false;
        }
        System.out.println("Подготовка к запуску симуляции...");

        while (mainland.addArea(new Area("Земля" + (mainland.sizeOfAreas() + 1), new Point(), new Point(),
                ((int) (Math.random() * 101)) / 100.0, ((int) (Math.random() * 101)) / 10.0))) {
            System.out.println("Определена " + mainland.getArea(mainland.sizeOfAreas() - 1) + "\n");
        }

        while (addMonster("Монстр" + (getNumberOfMonster() + 1),
                ((int) (Math.random() * 101)) / 100.0, (int) (Math.random() * 3 + 3))) {
            System.out.println("Добавлена " + devilishCreatures.get(getNumberOfMonster() - 1) + "\n");
        }

        IMakeSound penguinScream = (int x, int y) ->
                new Sound(x, y, "визг пингвина", SoundType.PENGUIN_SCREAM, (int) ((Math.random() * 2) + 2));
        IMakeSound devilishScream = (int x, int y) ->
                new Sound(x, y, "крик страшной твари", SoundType.MONSTER_SCREAM, (int) ((Math.random() * 3) + 4));

        WorldMap map = new WorldMap(
                this,
                mainland.getTopRightPoint().getY() - mainland.getBottomLeftPoint().getY() + 1,
                mainland.getTopRightPoint().getX() - mainland.getBottomLeftPoint().getX() + 1
        );
        map.print();
        return true;
    }
}

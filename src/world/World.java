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

    Point getMonster(int index) {
        Point monster = devilishCreatures.get(index);
        return new Point(monster.getX(), monster.getY(), monster.getName());
    }

    private boolean startBattle(Human human, DevilishCreature monster) {
        while (human.getHp() != 0 && monster.getHp() != 0 && monster.getCondition() != Condition.CONFUSED) {
            human.attackTarget(monster);
            if (monster.getHp() == 0) {
                monster.setCondition(Condition.DISABLED);
                return true;
            }
            monster.attackTarget(human);
            if (human.getHp() == 0) {
                human.setCondition(Condition.DISABLED);
                return false;
            }
        }

        return human.getHp() != 0;
    }

    private boolean startBattle(DevilishCreature monster, GroupOfCreatures<Human> people) {
        System.out.println("ДА БУДЕТ БАААААААААТТЛ");
        for (int i = 0; i < people.getNumberOfCreatures(); ++i) {
            Human curr_human = people.getCreature(i);
            if (startBattle(curr_human, monster)) {
                System.out.println("Победа людей\n");
                break;
            }
        }
        return monster.getCondition() != Condition.CONFUSED && monster.getHp() != 0;
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

        boolean peopleReachedTheTarget = false;
        mainLoop: while (true) {
            for (int i = 0; i < getNumberOfMonster(); ++i) {
                DevilishCreature curr_monster = devilishCreatures.get(i);
                if (curr_monster.getCondition() == Condition.CONFUSED && Math.random() > 0.8)
                    curr_monster.setCondition(Condition.HEALTHY);
                if (curr_monster.getCondition() == Condition.HEALTHY) {
                    int deltaX = Math.abs(people.getX() - curr_monster.getX());
                    int deltaY = Math.abs(people.getY() - curr_monster.getY());
                    if (deltaX == 0 && deltaY == 0) {
                        if (startBattle(curr_monster, people)) {
                            System.out.println("Победа твари\n");
                            break mainLoop;
                        }
                        devilishCreatures.set(i, null);
                    }
                    else if ((deltaX <= 1 && deltaY <= 1) || deltaX * deltaX + deltaY * deltaY <=
                            curr_monster.senseOfSmell * curr_monster.senseOfSmell) {
                        curr_monster.goTo(people);
                    } else {
                        curr_monster.move();
                    }
                }
                if (Math.random() > 0.9)
                    mainland.addSound(devilishScream.makeSound(curr_monster.getX(), curr_monster.getY()));
            }

            penguins.move();
            if (Math.random() > 0.7)
                mainland.addSound(penguinScream.makeSound(penguins.getX(), penguins.getY()));

            if (people.getCreature(0).getMainTarget() != null &&
                    people.goTo(people.getCreature(0).getMainTarget()) || finish.isIncludedPoint(people)) {
                peopleReachedTheTarget = true;
                break;
            } else {
                for (int i = 0; i < mainland.getNumberOfSounds(); ++i) {
                    Sound curr_sound = mainland.getSound(i);
                    if (curr_sound.heard(people)) {
                        if (curr_sound.getType() == SoundType.PENGUIN_SCREAM) {
                            people.getCreature(0).setMainTarget(finish.getCenterPoint());
                            System.out.println("До членов группы " + people.getName() +
                                    " доносятся дребезжащие крики какой-то ужасной твари. Походу, это пингвины. " +
                                    "Определен источник звука");
                            break;
                        }
                        else if (curr_sound.getType() == SoundType.MONSTER_SCREAM)
                            System.out.println("До членов группы " + people.getName() + " доносятся страшные звуки");
                    }
                }
                if (people.getCreature(0).getMainTarget() == null)
                    people.move();
            }

            int i = 0;
            while (i < devilishCreatures.size()) {
                if (devilishCreatures.get(i) == null)
                    devilishCreatures.remove(i);
                else
                    ++i;
            }

            map.update();
            map.print();
            System.out.println();
            mainland.clearSounds();
        }

        return peopleReachedTheTarget;
    }
}

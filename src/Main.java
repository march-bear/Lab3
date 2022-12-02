import world.World;
import world.points.locateables.creatures.Penguin;
import world.points.locateables.creatures.active.Human;

public class Main {
    public static void main(String[] args) {
        World world = World.getInstance();

        world.setGroupOfPeople(
                new Human("Лейк"),
                new Human("Рассказчик"),
                new Human("Карл")
        );

        world.setGroupOfPenguins(
                new Penguin("Круглан", world.finish),
                new Penguin("Рыбак", world.finish),
                new Penguin("Коко", world.finish),
                new Penguin("Тулупчик", world.finish)
        );

        world.setMaxNumberOfMonsters(10);
        world.mainland.setNumberOfAreas(4);
        world.run();
    }
}
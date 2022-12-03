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

        world.setMaxNumberOfMonsters(3);
        world.mainland.setNumberOfAreas(4);
        if (world.run()) {
            System.out.println("Пойдя на звуки, люди вошли в арку, которую, видно,\n" +
                    "только недавно расчистили от завалов. Оставив дневной свет позади,\n" +
                    "они возобновили нашу возню с клочками, тем более что позаимствовали,\n" +
                    "хоть и не без брезгливости, изрядную толику бумаги из тюка на санях.");
        } else {
            System.out.println("Люди не достигли своей цели и были убиты фантомными монстрами.");
        }
        System.out.println("Симуляция остановлена...");

    }
}
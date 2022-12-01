package world;

import world.points.Point;
import world.squares.Label;
import world.squares.MainlandAntarctica;
import world.squares.Square;

import java.util.ArrayList;
import java.util.HashMap;

final class WorldMap {
    public final static HashMap<String, Character> symbols;
    public final int height;
    public final int width;
    private final World world;
    private final MainlandAntarctica mainland;
    private final ArrayList<ArrayList<Character>> field;

    public WorldMap(World world, int height, int width) {
        this.height = height;
        this.width = width;
        this.world = world;
        this.mainland = MainlandAntarctica.getInstance();
        field = new ArrayList<>();

        for (int i = 0; i < height; ++i) {
            field.add(new ArrayList<>());
            for (int j = 0; j < width; ++j) {
                field.get(i).add(symbols.get("empty"));
            }
        }

        update();
    }

    public void update() {
        clear();
        for (int i = 0; i < mainland.sizeOfAreas(); ++i) {
            Square area = mainland.getArea(i);
            for (int y = area.getBottomLeftPoint().getY(); y <= area.getTopRightPoint().getY(); ++y) {
                for (int x = area.getBottomLeftPoint().getX(); x <= area.getTopRightPoint().getX(); ++x) {
                    field.get(y).set(x, symbols.get("specialZone"));
                }
            }
        }

        Label label = world.finish;
        for (int y = label.getBottomLeftPoint().getY(); y <= label.getTopRightPoint().getY(); ++y) {
            for (int x = label.getBottomLeftPoint().getX(); x <= label.getTopRightPoint().getX(); ++x) {
                field.get(y).set(x, symbols.get("label"));
            }
        }
        field.get(label.getCenterPoint().getY()).set(label.getCenterPoint().getX(), symbols.get("labelCenter"));

        for (int i = 0; i < world.getNumberOfMonster(); ++i) {
            Point monster = world.getMonster(i);
            field.get(monster.getY()).set(monster.getX(), symbols.get("monster"));
        }

        Point people = world.getGroupOfPeople();
        if (field.get(people.getY()).get(people.getX()) == 'M')
            field.get(people.getY()).set(people.getX(), symbols.get("battle"));
        else
            field.get(people.getY()).set(people.getX(), symbols.get("people"));

        Point penguins = world.getGroupOfPenguins();
        field.get(penguins.getY()).set(penguins.getX(), symbols.get("penguins"));
    }

    private void clear() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                field.get(i).set(j, symbols.get("empty"));
            }
        }
    }

    public void print() {
        for (ArrayList<Character> strings : field) {
            for (Character ch : strings) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    static {
        symbols = new HashMap<>();
        symbols.put("empty", '`');
        symbols.put("penguin", 'p');
        symbols.put("penguins", 'P');
        symbols.put("human", 'h');
        symbols.put("people", 'H');
        symbols.put("monster", 'M');
        symbols.put("battle", 'B');
        symbols.put("specialZone", '*');
        symbols.put("label", '~');
        symbols.put("labelCenter", '?');
    }
}

package world.points.locateables.creatures;

public enum CreatureType {
    HUMAN("Человек"),
    PENGUIN("Пингвин"),
    DEVILISH_MONSTER("Монстер");

    final String name;

    CreatureType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

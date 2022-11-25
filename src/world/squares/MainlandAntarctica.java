package world.squares;

import world.points.locateables.Sound;
import world.points.Point;

import java.util.ArrayList;

public class MainlandAntarctica extends Area {
    private static MainlandAntarctica instance;
    private final ArrayList<Area> areas;
    private final ArrayList<Label> labels;
    private final ArrayList<Sound> sounds;

    private MainlandAntarctica() {
        super("Антарктида", new Point(0, 0), new Point(Point.maxXY, Point.maxXY));
        areas = new ArrayList<>();
        labels = new ArrayList<>();
        sounds = new ArrayList<>();
    }

    public static MainlandAntarctica getInstance() {
        if (instance == null)
            instance = new MainlandAntarctica();
        return instance;
    }

    public Area getIndexArea(int index) {
        if (index >= areas.size() || index < 0)
            return instance;
        return areas.get(index);
    }

    public void addArea(Area area) {
        areas.add(area);
    }

    public int sizeOfAreas() {
        return areas.size();
    }

    public void addLabel(Label label) {
        labels.add(label);
    }

    public void addSound(Sound sound) {
        sounds.add(sound);
    }

    public void clearSounds() {
        sounds.clear();
    }

    public Area whatArea(Point point) {
        for (Area area : areas) {
            if (area.isIncludedPoint(point)) {
                return area;
            }
        }
        return instance;
    }
}

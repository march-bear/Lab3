package world.points;

import world.squares.Square;

public class Point {
    protected int x;
    protected int y;
    protected String name;
    public final static int maxXY = 25;
    public Point() {
        this(randomXY(), randomXY());
    }

    public Point(int x, int y) {
        this(x, y, "");
    }

    public Point(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void printCoordinates() {
        System.out.println(x + " " + y);
    }

    public static int randomXY() {
        return (int) (Math.random() * (maxXY + 1));
    }

    public static int randomX(Square square) {
        return (int) (Math.random() * (square.getTopRightPoint().getX() -
                square.getBottomLeftPoint().getX() + 1) + square.getBottomLeftPoint().getX());
    }

    public static int randomY(Square square) {
        return (int) (Math.random() * (square.getTopRightPoint().getY() -
                square.getBottomLeftPoint().getY() + 1) + square.getBottomLeftPoint().getY());
    }
}

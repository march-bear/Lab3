package world.points;

public class Point {
    protected int x;
    protected int y;
    public final static int maxXY = 50;
    public Point() {
        this.x = randomXY();
        this.y = randomXY();
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void printCoordinates() {
        System.out.println(x + " " + y);
    }

    public static int randomXY() {
        return (int) (Math.random() * (maxXY + 1));
    }
}

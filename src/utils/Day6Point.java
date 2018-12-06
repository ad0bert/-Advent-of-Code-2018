package utils;

public class Day6Point {
    public int x, y;

    public char c;

    public Day6Point(String input, char c) {
        String[] xy = input.split(", ");
        this.x = Integer.parseInt(xy[0]);
        this.y = Integer.parseInt(xy[1]);
        this.c = c;
    }

    @Override
    public String toString() {

        return this.c + ": [" + this.x + "," + this.y + "]";
    }
}

package main.day17;

public class Day17Point {
    public int x, y;
    public char fill = '#';
    
    public Day17Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Day17Point(int x, int y, char fill) {
        this.x = x;
        this.y = y;
        this.fill = fill;
    }
    
    @Override
    public String toString() {
        return String.valueOf(fill);
    }
}

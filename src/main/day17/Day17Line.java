package main.day17;

public class Day17Line {
    public Day17Point p1, p2;
    
    public Day17Line(String input) {
        String[] split = input.split(", ");
        String[] first = split[0].split("=");
        String[] second = split[1].split("=");
        String[] parts = second[1].split("\\.\\.");
        
        int x1, x2, y1, y2;
        x1 = x2 = y1 = y2 = 0;
        
        // swap x and y because x goes down and y goes right
        if (first[0].equals("x")) {
            y1 = y2 = Integer.parseInt(first[1]);
        } else {
            x1 = x2 = Integer.parseInt(first[1]);
        }
        
        if (second[0].equals("x")) {
            y1 = Integer.parseInt(parts[0]);
            y2 = Integer.parseInt(parts[1]);
        } else {
            x1 = Integer.parseInt(parts[0]);
            x2 = Integer.parseInt(parts[1]);
        }
        
        this.p1 = new Day17Point(x1, y1);
        this.p2 = new Day17Point(x2, y2);
        
    }
    
    @Override
    public String toString() {
        return "p1:[" + this.p1.x + "," + this.p1.y + "] p2:[" + this.p2.x + "," + this.p2.y + "]";
    }
}

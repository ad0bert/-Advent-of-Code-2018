package main.day10;

public class Day10Point {

    public int x, y;
    public int velX, velY;

    public Day10Point(String input) {
        String[] res = input.replaceFirst("position=<", "").replaceFirst("> velocity=<", ",").replaceFirst(">", "")
                .split(",");
        this.x = Integer.parseInt(res[0].trim());
        this.y = Integer.parseInt(res[1].trim());
        this.velX = Integer.parseInt(res[2].trim());
        this.velY = Integer.parseInt(res[3].trim());
    }

    public void move(int multi) {
        this.x += this.velX * multi;
        this.y += this.velY * multi;
    }

    @Override
    public String toString() {
        return "[" + this.x + "," + this.y + "]";
    }

}

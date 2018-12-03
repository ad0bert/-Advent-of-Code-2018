package utils;

public class Day3CoordinateEntry {

    public int number;
    public int x;
    public int y;
    public int a;
    public int b;

    public Day3CoordinateEntry(String input) {
        String[] inputArray = input.split(" ");
        this.number = Integer.parseInt(inputArray[0].substring(1));
        this.x = Integer.parseInt(inputArray[2].split(",")[0]);
        this.y = Integer.parseInt(inputArray[2].split(",")[1].replace(':', ' ').trim());
        this.a = Integer.parseInt(inputArray[3].split("x")[0]);
        this.b = Integer.parseInt(inputArray[3].split("x")[1]);
    }

    @Override
    public String toString() {
        return "#" + this.number + " @ " + this.x + "," + this.y + ": " + this.a + "x" + this.b;
    }

}

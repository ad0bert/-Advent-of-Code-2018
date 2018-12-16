package main.day15;

public class CavePosition {

    public char type;
    public int x, y;
    public Mob mob = null;

    public int dist = -1;

    public char state;

    public CavePosition(int x, int y, char type) {
        this.x = x;
        this.y = y;

        if ((type == 'E') || (type == 'G')) {
            this.type = '.';
            this.mob = new Mob(type, this);
        } else {
            this.type = type;
        }
    }

    @Override
    public String toString() {

        if (this.mob != null) {
            return String.valueOf(this.mob.type);
        }

        return String.valueOf(this.type);
    }
}

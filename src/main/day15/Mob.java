package main.day15;

public class Mob {

    public char type;
    public int health = 200;

    public int attackPower = 3;

    // public CavePosition pos;
    public boolean hasMoved = false;

    public Mob(char type, CavePosition pos) {
        this.type = type;
        // this.pos = pos;
    }

    public boolean tryAttack(CavePosition[][] cave, int x, int y) {
        if ((x > 0) && (y > 0) && (x < cave.length) && (y < cave[0].length)) {
            if ((cave[x][y].mob != null) && (cave[x][y].mob.type != this.type)) {
                cave[x][y].mob.health -= this.attackPower;
                if (cave[x][y].mob.health <= 0) {
                    cave[x][y].mob = null;
                }
                return true;
            }
        }

        return false;
    }

}

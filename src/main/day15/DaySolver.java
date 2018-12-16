package main.day15;

import java.io.File;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    private boolean elveDead = false;

    public DaySolver(String day) {
        super(day);
    }

    public void printCave(CavePosition[][] cave) {
        for (CavePosition[] element : cave) {
            for (CavePosition element2 : element) {
                System.out.print(element2);
            }
            System.out.println();
        }
    }

    private void handleMove(CavePosition[][] cave, CavePosition pos, int attackPower) {
        // int xLen = cave.length;
        // int yLen = cave[0].length;

        int minDist = Integer.MAX_VALUE;
        int xToMove = -1, yToMove = -1;
        int dist = 1;
        dist = findNextEnemy(cave, 1, pos.x - 1, pos.y, pos.mob.type);
        if ((dist < minDist) && (dist > 0)) {
            minDist = dist;
            xToMove = pos.x - 1;
            yToMove = pos.y;
        }

        dist = findNextEnemy(cave, 1, pos.x, pos.y - 1, pos.mob.type);
        if ((dist < minDist) && (dist > 0)) {
            minDist = dist;
            xToMove = pos.x;
            yToMove = pos.y - 1;
        }
        dist = findNextEnemy(cave, 1, pos.x, pos.y + 1, pos.mob.type);
        if ((dist < minDist) && (dist > 0)) {
            minDist = dist;
            xToMove = pos.x;
            yToMove = pos.y + 1;
        }
        dist = findNextEnemy(cave, 1, pos.x + 1, pos.y, pos.mob.type);
        if ((dist < minDist) && (dist > 0)) {
            minDist = dist;
            xToMove = pos.x + 1;
            yToMove = pos.y;
        }

        if (((minDist > 0) && (minDist != Integer.MAX_VALUE))) {
            cave[xToMove][yToMove].mob = pos.mob;
            pos.mob.hasMoved = true;
            pos.mob = null;

            handleAttack(cave, cave[xToMove][yToMove], attackPower);

        }

        cleanupCave(cave);

    }

    private void cleanupCave(CavePosition[][] cave) {
        for (CavePosition[] element : cave) {
            for (CavePosition element2 : element) {
                element2.dist = -1;
            }
        }
    }

    private int findNextEnemy(CavePosition[][] cave, int dist, int x, int y, char friend) {
        if ((x < 0) || (y < 0) || (x > cave.length) || (y > cave[x].length)) {
            return -1;
        }
        if (cave[x][y].type == '#') {
            return -1;
        }
        if (cave[x][y].mob != null) {
            if (cave[x][y].mob.type == friend) {
                return -1;
            } else {
                return dist - 1;
            }
        }

        if (cave[x][y].dist == -1) {
            cave[x][y].dist = dist;
        } else if (cave[x][y].dist > dist) {
            cave[x][y].dist = dist;
        } else {
            return -1;
        }

        int res = 0;
        int bestDist = Integer.MAX_VALUE;
        res = findNextEnemy(cave, dist + 1, x - 1, y, friend);
        if (res > 0) {
            bestDist = res;
        }
        res = findNextEnemy(cave, dist + 1, x, y - 1, friend);
        if ((res > 0) && (res != -1) && (res < bestDist)) {
            bestDist = res;
        }
        res = findNextEnemy(cave, dist + 1, x + 1, y, friend);
        if ((res > 0) && (res != -1) && (res < bestDist)) {
            bestDist = res;
        }
        res = findNextEnemy(cave, dist + 1, x, y + 1, friend);
        if ((res > 0) && (res != -1) && (res < bestDist)) {
            bestDist = res;
        }
        if (bestDist == Integer.MAX_VALUE) {
            return 0;
        }

        return bestDist;
    }

    private boolean handleAttack(CavePosition[][] cave, CavePosition pos, int attackPower) {

        int xLen = cave.length;
        int yLen = cave[0].length;

        Mob selected = null;

        int selectedX = -1;
        int selectedY = -1;

        if (((pos.x - 1) >= 0) && (cave[pos.x - 1][pos.y].mob != null)
                && (cave[pos.x - 1][pos.y].mob.type != pos.mob.type)) {
            selected = cave[pos.x - 1][pos.y].mob;
            selectedX = pos.x - 1;
            selectedY = pos.y;
        }

        if ((((pos.y - 1) >= 0) && (cave[pos.x][pos.y - 1].mob != null))
                && (cave[pos.x][pos.y - 1].mob.type != pos.mob.type)) {
            if (((selected != null) && (selected.health > cave[pos.x][pos.y - 1].mob.health)) || (selected == null)) {
                selected = cave[pos.x][pos.y - 1].mob;
                selectedX = pos.x;
                selectedY = pos.y - 1;
            }
        }

        if ((((pos.y + 1) < yLen) && (cave[pos.x][pos.y + 1].mob != null))
                && (cave[pos.x][pos.y + 1].mob.type != pos.mob.type)) {
            if (((selected != null) && (selected.health > cave[pos.x][pos.y + 1].mob.health)) || (selected == null)) {
                selected = cave[pos.x][pos.y + 1].mob;
                selectedX = pos.x;
                selectedY = pos.y + 1;
            }
        }

        if ((((pos.x + 1) < xLen) && (cave[pos.x + 1][pos.y].mob != null))
                && (cave[pos.x + 1][pos.y].mob.type != pos.mob.type)) {
            if (((selected != null) && (selected.health > cave[pos.x + 1][pos.y].mob.health)) || (selected == null)) {
                selected = cave[pos.x + 1][pos.y].mob;
                selectedX = pos.x + 1;
                selectedY = pos.y;
            }
        }

        if (selected != null) {

            if (selected.type == 'E') {
                selected.health -= 3;
            } else {
                selected.health -= attackPower;
            }

            if (selected.health <= 0) {
                if (selected.type == 'E') {
                    this.elveDead = true;
                }
                cave[selectedX][selectedY].mob = null;
            }
            return true;
        }

        return false;
    }

    @Override
    public void solvePart1() {
        List<List<String>> input = AoCFileReader.readListOfCharList(new File(this.inputFile1));

        CavePosition[][] cave = new CavePosition[input.size()][input.get(0).size()];

        for (int i = 0; i < input.size(); ++i) {
            cave[i] = new CavePosition[input.get(i).size()];
            for (int j = 0; j < input.get(i).size(); ++j) {
                cave[i][j] = new CavePosition(i, j, input.get(i).get(j).charAt(0));
            }
        }
        int rounds = -1;
        boolean running = true;
        while (running) {

            for (CavePosition[] line : cave) {
                for (CavePosition pos : line) {
                    if (pos.mob != null) {
                        if (scanForEnemies(cave, pos)) {
                            running = false;
                            break;
                        }

                        if (!pos.mob.hasMoved) {
                            if (handleAttack(cave, pos, 3)) {
                                continue;
                            }
                        }

                        if (!pos.mob.hasMoved) {
                            handleMove(cave, pos, 3);
                        } else {

                        }

                    }
                }
                if (!running) {
                    break;
                }
            }
            this.cleanupCaveMove(cave);

            // this.printCave(cave);
            rounds++;
        }

        int health = cntRemainingHealth(cave);

        System.out.println(health * rounds);
    }

    private void cleanupCaveMove(CavePosition[][] cave) {
        for (CavePosition[] line : cave) {
            for (CavePosition pos : line) {
                if (pos.mob != null) {
                    pos.mob.hasMoved = false;
                }
            }
        }

    }

    private int cntRemainingHealth(CavePosition[][] cave) {
        int res = 0;
        for (CavePosition[] line : cave) {
            for (CavePosition pos : line) {
                if (pos.mob != null) {
                    res += pos.mob.health;
                }
            }
        }
        return res;
    }

    private boolean scanForEnemies(CavePosition[][] cave, CavePosition currPos) {
        for (CavePosition[] line : cave) {
            for (CavePosition pos : line) {
                if ((pos.mob != null) && (pos.mob.type != currPos.mob.type)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void solvePart2() {
        List<List<String>> input = AoCFileReader.readListOfCharList(new File(this.inputFile2));

        int attackPower = 3;

        while (true) {

            this.runCombat(input, attackPower++);

            if (this.elveDead) {
                this.elveDead = false;
            } else {
                return;
            }

        }
    }

    private void runCombat(List<List<String>> input, int attackPower) {
        CavePosition[][] cave = new CavePosition[input.size()][input.get(0).size()];

        for (int i = 0; i < input.size(); ++i) {
            cave[i] = new CavePosition[input.get(i).size()];
            for (int j = 0; j < input.get(i).size(); ++j) {
                cave[i][j] = new CavePosition(i, j, input.get(i).get(j).charAt(0));
            }
        }
        int rounds = -1;
        boolean running = true;
        while (running) {

            for (CavePosition[] line : cave) {
                for (CavePosition pos : line) {
                    if (pos.mob != null) {
                        if (scanForEnemies(cave, pos)) {
                            running = false;
                            break;
                        }

                        if (!pos.mob.hasMoved) {
                            if (handleAttack(cave, pos, attackPower)) {
                                continue;
                            }
                        }

                        if (!pos.mob.hasMoved) {
                            handleMove(cave, pos, attackPower);
                        }
                        if (this.elveDead) {
                            return;
                        }
                    }
                }
                if (!running) {
                    break;
                }
            }
            this.cleanupCaveMove(cave);

            // this.printCave(cave);
            rounds++;
        }

        int health = cntRemainingHealth(cave);

        System.out.println(health * rounds);
    }

}

package main.day17;

import java.io.File;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    private int findMaxX(List<Day17Line> input) {
        int res = Integer.MIN_VALUE;
        for (Day17Line line : input) {
            if (res < line.p1.x) {
                res = line.p1.x;
            }
            if (res < line.p2.x) {
                res = line.p2.x;
            }
        }
        return res+1;
    }
    
    private int findMaxY(List<Day17Line> input) {
        int res = Integer.MIN_VALUE;
        for (Day17Line line : input) {
            if (res < line.p1.y) {
                res = line.p1.y;
            }
            if (res < line.p2.y) {
                res = line.p2.y;
            }
        }
        return res+1;
    }
    
    @Override
    public void solvePart1() {
        List<Day17Line> input = AoCFileReader.readLineList(new File(this.inputFile1));
        int maxX = findMaxX(input);
        int maxY = findMaxY(input);
        Day17Point[][] world = new Day17Point[maxX][maxY];
        for (int i = 0; i < world.length; ++i) {
            world[i] = new Day17Point[maxY];
            for (int j = 0; j < world[i].length; ++j) {
                if (i == 0 && j == 500) {
                    world[i][j] = new Day17Point(i, j, '+');
                } else {
                    world[i][j] = new Day17Point(i, j, '.');
                }
            }
        }
        
        for (Day17Line line : input) {
            if (line.p1.x != line.p2.x) {
                for (int i = line.p1.x; i <= line.p2.x; ++i) {
                    world[i][line.p1.y].fill = '#';
                }
            } else {
                for (int i = line.p1.y; i <= line.p2.y; ++i) {
                    world[line.p1.x][i].fill = '#';
                }
            }
        }
        
        fillWater(world, 1, 500, true, true);
        int res = 0;
        for (int i = 0; i < world.length; ++i) {
            for (int j = 0; j < world[i].length; ++j) {
                if (world[i][j].fill == '~' || world[i][j].fill == '|') {
                    res++;
                }
            }
        }
        
        System.out.println(res-4); // -4 because minimum y level
    }

    private int fillWater(Day17Point[][] world, int x, int y, boolean first, boolean left) {
        if (x >= world.length) return -1;
        if (y >= world[x].length) return -1;
        if (y < 0 || x < 0) return -1;
        
        if (world[x][y].fill == '#') return 1; 
        if (first && world[x][y].fill == '~') return 2;
        if (world[x][y].fill == '+') return 3;
        if (first && world[x][y].fill == '|') return 4;
        
        int res = 0;

        world[x][y].fill = '|';
        if (first) {    
            res = fillWater(world, x+1, y, true, true);
            if (res == -1 || res == 4) return 4;
            
            int res1 = fillWater(world, x, y-1, false, true);
            res = fillWater(world, x, y+1, false, false);
            
            if ((res1 == -1 || res1 == 4) && res != -1) {
                int offset = y+1;
                while(offset < world[x].length && world[x][offset].fill == '~') {
                    world[x][offset].fill = '|';
                    offset++;
                }
                return 4;
            }
            
            if (res == -1 || res == 4) {
                int offset = y-1;
                while(offset >= 0 && world[x][offset].fill == '~') {
                    world[x][offset].fill = '|';
                    offset--;
                }
                return 4;
             }
        } else {
            if (left) {
                res = fillWater(world, x+1, y, true, true);
                if (res == -1 || res == 4) return 4;
                res = fillWater(world, x, y-1, false, true);
                if (res == -1 || res == 4) {
                   int offset = y+1;
                   while(offset < world[x].length && world[x][offset].fill == '~') {
                       world[x][offset].fill = '|';
                       offset++;
                   }
                   return 4;
                }
            } else {
                res = fillWater(world, x+1, y, true, true);
                if (res == -1 || res == 4) return 4;
                res = fillWater(world, x, y+1, false, false);
                if (res == -1 || res == 4) {
                    int offset = y-1;
                    while(offset >= 0 && world[x][offset].fill == '~') {
                        world[x][offset].fill = '|';
                        offset--;
                    }
                    return 4;
                 }
            }
        }
        
        if (res == 1 || res == 2) {
            world[x][y].fill = '~';
            return 2;
        }
        return -1;
    }
    
    @Override
    public void solvePart2() {
        List<Day17Line> input = AoCFileReader.readLineList(new File(this.inputFile2));
        int maxX = findMaxX(input);
        int maxY = findMaxY(input);
        Day17Point[][] world = new Day17Point[maxX][maxY];
        for (int i = 0; i < world.length; ++i) {
            world[i] = new Day17Point[maxY];
            for (int j = 0; j < world[i].length; ++j) {
                if (i == 0 && j == 500) {
                    world[i][j] = new Day17Point(i, j, '+');
                } else {
                    world[i][j] = new Day17Point(i, j, '.');
                }
            }
        }
        
        for (Day17Line line : input) {
            if (line.p1.x != line.p2.x) {
                for (int i = line.p1.x; i <= line.p2.x; ++i) {
                    world[i][line.p1.y].fill = '#';
                }
            } else {
                for (int i = line.p1.y; i <= line.p2.y; ++i) {
                    world[line.p1.x][i].fill = '#';
                }
            }
        }
        
        fillWater(world, 1, 500, true, true);
        int res = 0;
        for (int i = 0; i < world.length; ++i) {
            for (int j = 0; j < world[i].length; ++j) {
                if (world[i][j].fill == '~') {
                    res++;
                }
            }
        }
        
        System.out.println(res);
    }

}

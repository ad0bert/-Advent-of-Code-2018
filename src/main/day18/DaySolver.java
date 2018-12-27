package main.day18;

import java.io.File;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    private final static int X = 50;
    private final static int Y = 50;
    
    public DaySolver(String day) {
        super(day);
    }
    
    private long cntChars(char[][] world, char toCnt) {
        long res = 0;
        for (char[] line : world) 
            for (char c : line)
                if (c == toCnt)
                    res++;
        return res;
    }
    
    @Override
    public void solvePart1() {
        List<String> input = AoCFileReader.readMulitpleLines(new File(this.inputFile1));
        char[][] world = new char[X][Y];
        for (int i = 0; i < world.length; ++i) {
            world[i] = input.get(i).toCharArray();
        }
        int iterations = 10;
        while(iterations-- != 0) {
            world = getNextWorld(world);
        }
        System.out.println(cntChars(world, '|') * cntChars(world, '#'));
    }

    private char[][] getNextWorld(char[][] world) {
        char[][] nextWorld = new char[X][Y];
        for (int i = 0; i < X; ++i) {
            nextWorld[i] = new char[Y];
            for (int j = 0; j < Y; ++j) {
                nextWorld[i][j] = '*';
            }
        }
        
        for (int i = 0; i < X; ++i) {
            for (int j = 0; j < Y; ++j) {
                nextWorld[i][j] = getNextChar(world, i, j);
            }
        }
        
        return nextWorld;
    }

    private char getNextChar(char[][] world, int i, int j) {
        char c = world[i][j];
        char nextChar = '*';
        switch (c) {
        case '|': 
            if (cntAround(world, i, j, '#') >= 3) {
                nextChar = '#';
            } else {
                nextChar = '|';
            }
            break;
        case '#': 
            if (lumberCheck(world, i, j)) {
                nextChar = '#';
            } else {
                nextChar = '.';
            }
            break;
        case '.': 
            if (cntAround(world, i, j, '|') >= 3) {
                nextChar = '|';
            } else {
                nextChar = '.';
            }
            break;
        }
        
        return nextChar;
    }

    private boolean lumberCheck(char[][] world, int i, int j) {
        boolean hasLamber = false;
        boolean hasTree = false;
        if (i - 1 >= 0) {
            if (j - 1 >= 0) {
                if (world[i-1][j-1] == '|') {
                    hasTree = true;
                }
                if (world[i-1][j-1] == '#') {
                    hasLamber = true;
                }
            }
            
            if (world[i-1][j] == '|') {
                hasTree = true;
            }
            if (world[i-1][j] == '#') {
                hasLamber = true;
            }
            
            if (j + 1 < world[i].length) {
                if (world[i-1][j+1] == '|') {
                    hasTree = true;
                }
                if (world[i-1][j+1] == '#') {
                    hasLamber = true;
                }
            }
        }
        if (j - 1 >= 0) {
            if (world[i][j-1] == '|') {
                hasTree = true;
            }
            if (world[i][j-1] == '#') {
                hasLamber = true;
            }
        }
        if (j + 1 < world[i].length) {
            if (world[i][j+1] == '|') {
                hasTree = true;
            }
            if (world[i][j+1] == '#') {
                hasLamber = true;
            }
        }
        
        if (i + 1 < world.length) {
            if (j - 1 >= 0) {
                if (world[i+1][j-1] == '|') {
                    hasTree = true;
                }
                if (world[i+1][j-1] == '#') {
                    hasLamber = true;
                }
            }
            
            if (world[i+1][j] == '|') {
                hasTree = true;
            }
            if (world[i+1][j] == '#') {
                hasLamber = true;
            }
            
            if (j + 1 < world[i].length) {
                if (world[i+1][j+1] == '|') {
                    hasTree = true;
                }
                if (world[i+1][j+1] == '#') {
                    hasLamber = true;
                }
            }
        }
        return hasLamber && hasTree;
    }

    private int cntAround(char[][] world, int i, int j, char c) {
        int res = 0;
        if (i - 1 >= 0) {
            if (j - 1 >= 0) {
                if (world[i-1][j-1] == c) {
                    res++;
                }
            }
            
            if (world[i-1][j] == c) {
                res++;
            }
            
            if (j + 1 < world[i].length) {
                if (world[i-1][j+1] == c) {
                    res++;
                }
            }
        }
        if (j - 1 >= 0) {
            if (world[i][j-1] == c) {
                res++;
            }
        }
        if (j + 1 < world[i].length) {
            if (world[i][j+1] == c) {
                res++;
            }
        }
        
        if (i + 1 < world.length) {
            if (j - 1 >= 0) {
                if (world[i+1][j-1] == c) {
                    res++;
                }
            }
            
            if (world[i+1][j] == c) {
                res++;
            }
            
            if (j + 1 < world[i].length) {
                if (world[i+1][j+1] == c) {
                    res++;
                }
            }
        }
        return res;
    }

    @Override
    public void solvePart2() {
        List<String> input = AoCFileReader.readMulitpleLines(new File(this.inputFile2));
        char[][] world = new char[X][Y];
        for (int i = 0; i < world.length; ++i) {
            world[i] = input.get(i).toCharArray();
        }
        long iterations = 1000000000;
        long toFind = 188155; // found by looking manually on printed outputs
        while(iterations-- != 0) {
            world = getNextWorld(world);
            if (toFind == cntChars(world, '|') * cntChars(world, '#')) {
                iterations = iterations % 28;
            }
        }
        System.out.println(cntChars(world, '|') * cntChars(world, '#'));
    }

}

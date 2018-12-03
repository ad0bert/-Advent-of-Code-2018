package main.day03;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import main.AbstractSolver;
import utils.AoCFileReader;
import utils.Day3CoordinateEntry;

public class DaySolver extends AbstractSolver {

    private static final int DIMENSION = 1000;
    private static int fabricSquare[][] = new int[DIMENSION][DIMENSION];

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<Day3CoordinateEntry> input = AoCFileReader.readCoordinateList(new File(this.inputFile1));
        init();

        for (Day3CoordinateEntry entry : input) {
            for (int i = entry.x; i < (entry.x + entry.a); ++i) {
                for (int j = entry.y; j < (entry.y + entry.b); ++j) {
                    if (fabricSquare[i][j] == 0) {
                        fabricSquare[i][j] = entry.number;
                    } else {
                        fabricSquare[i][j] = -1;

                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < DIMENSION; ++i) {
            for (int j = 0; j < DIMENSION; ++j) {
                if (fabricSquare[i][j] == -1) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    @Override
    public void solvePart2() {
        List<Day3CoordinateEntry> input = AoCFileReader.readCoordinateList(new File(this.inputFile2));
        init();
        Set<Integer> invalidEntries = new TreeSet<>();
        for (Day3CoordinateEntry entry : input) {
            for (int i = entry.x; i < (entry.x + entry.a); ++i) {
                for (int j = entry.y; j < (entry.y + entry.b); ++j) {
                    if (fabricSquare[i][j] == 0) {
                        fabricSquare[i][j] = entry.number;
                    } else {
                        invalidEntries.add(entry.number);
                        invalidEntries.add(fabricSquare[i][j]);
                        fabricSquare[i][j] = -1;
                    }
                }
            }
        }
        for (int i = 1; i < invalidEntries.size(); ++i) {
            if (!invalidEntries.contains(i)) {
                System.out.println(i);
            }
        }
    }

    private void init() {
        for (int i = 0; i < DIMENSION; ++i) {
            for (int j = 0; j < DIMENSION; ++j) {
                fabricSquare[i][j] = 0;
            }
        }
    }

}

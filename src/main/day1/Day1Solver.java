package main.day1;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.AbstractSolver;
import utils.AoCFileReader;

public class Day1Solver extends AbstractSolver {

    public Day1Solver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<Integer> input = AoCFileReader.readIntegerLineVertical(new File(this.inputFile1));
        int res = 0;
        for (int i : input) {
            res += i;
        }
        System.out.println(res);
    }

    @Override
    public void solvePart2() {
        List<Integer> input = AoCFileReader.readIntegerLineVertical(new File(this.inputFile2));
        int res = 0;
        Set<Integer> uniqueSet = new HashSet<Integer>();
        uniqueSet.add(0);
        boolean running = true;
        while (running) {
            for (int i : input) {
                res += i;
                if (uniqueSet.add(res) == false) {
                    System.out.println(res);
                    running = false;
                    break;
                }
            }
        }
    }

}

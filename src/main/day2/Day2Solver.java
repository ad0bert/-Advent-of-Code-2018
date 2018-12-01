package main.day2;

import java.io.File;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class Day2Solver extends AbstractSolver {

    public Day2Solver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<Integer> input = AoCFileReader.readIntegerLineVertical(new File(this.inputFile1));

        System.out.println(input);
    }

    @Override
    public void solvePart2() {
        List<Integer> input = AoCFileReader.readIntegerLineVertical(new File(this.inputFile2));

        System.out.println(input);
    }

}

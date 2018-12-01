package main.day3;

import java.io.File;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class Day3Solver extends AbstractSolver {

    public Day3Solver(String day) {
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

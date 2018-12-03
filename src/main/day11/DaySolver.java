package main.day11;

import java.io.File;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;
import utils.Day3CoordinateEntry;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<Day3CoordinateEntry> input = AoCFileReader.readCoordinateList(new File(this.inputFile1));

        System.out.println(input);
    }

    @Override
    public void solvePart2() {
        List<Day3CoordinateEntry> input = AoCFileReader.readCoordinateList(new File(this.inputFile2));

        System.out.println(input);
    }

}

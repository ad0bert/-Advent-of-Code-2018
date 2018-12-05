package main.day05;

import java.io.File;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    private final String abc = "abcdefghijklmnopqrstuvwxyz";

    public DaySolver(String day) {
        super(day);
    }

    private String reactOne(String input, String one, String two) {
        int oldLen = 0;
        do {
            oldLen = input.length();
            input = input.replaceAll(one, "");
            input = input.replaceAll(two, "");
        } while (oldLen != input.length());
        return input;
    }

    private String fullyReact(String input) {
        int oldLen = 0;
        do {
            oldLen = input.length();
            for (char c : this.abc.toCharArray()) {
                input = input.replaceAll(String.valueOf(c) + String.valueOf(c).toUpperCase(), "");
                input = input.replaceAll(String.valueOf(c).toUpperCase() + String.valueOf(c), "");
            }
        } while (oldLen != input.length());
        return input;
    }

    @Override
    public void solvePart1() {
        String input = AoCFileReader.readOneLine(new File(this.inputFile1));
        System.out.println(fullyReact(input).length());
    }

    @Override
    public void solvePart2() {
        String input = AoCFileReader.readOneLine(new File(this.inputFile2));
        int min = Integer.MAX_VALUE;
        for (char c : this.abc.toCharArray()) {
            int size = fullyReact(reactOne(input, String.valueOf(c), String.valueOf(c).toUpperCase())).length();
            if (size < min) {
                min = size;
            }
        }
        System.out.println(min);
    }

}

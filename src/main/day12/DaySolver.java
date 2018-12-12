package main.day12;

import java.io.File;
import java.util.Map;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    private char[] pots = "..........##.##.##..#..#.#.#.#...#...#####.###...#####.##..#####.#..#.##..#..#.#...#...##.##...#.##......####...."
            .toCharArray();

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        Map<String, Character> input = AoCFileReader.readStringMap(new File(this.inputFile1));
        char[] localPots = this.pots;
        for (int gen = 0; gen < 20; ++gen) {

            String res = "..";

            for (int i = 2; i < (localPots.length - 2); ++i) {
                String sub = String.valueOf(localPots[i - 2]) + String.valueOf(localPots[i - 1])
                        + String.valueOf(localPots[i]) + String.valueOf(localPots[i + 1])
                        + String.valueOf(localPots[i + 2]);
                res += input.getOrDefault(sub, '.');
            }

            localPots = (res + ".....").toCharArray();
        }
        System.out.println(cnt(localPots));
    }

    private int cnt(char[] pots) {
        int res = 0;
        for (int i = -10; i < (pots.length - 10); ++i) {
            if (pots[i + 10] == '#') {
                res += i;
            }
        }
        return res;
    }

    @Override
    public void solvePart2() {

        Map<String, Character> input = AoCFileReader.readStringMap(new File(this.inputFile2));

        long calcRes = 0;

        long calcGen = 50000000000l;
        char[] localPots = this.pots;

        long befCalc = cnt(localPots);
        long currCalc = cnt(localPots);

        long change = 0;
        int pos = 0;
        for (int gen = 0; gen < 200000; ++gen) {

            String res = "..";

            for (int i = 2; i < (localPots.length - 2); ++i) {
                String sub = String.valueOf(localPots[i - 2]) + String.valueOf(localPots[i - 1])
                        + String.valueOf(localPots[i]) + String.valueOf(localPots[i + 1])
                        + String.valueOf(localPots[i + 2]);
                res += input.getOrDefault(sub, '.');
            }

            currCalc = cnt(localPots);

            long currChange = currCalc - befCalc;

            if ((currChange == change) && (gen > 100)) {
                pos = gen;
                break;
            }

            befCalc = currCalc;
            change = currChange;

            localPots = (res + ".....").toCharArray();
        }

        calcRes = ((calcGen - pos) * change) + currCalc;

        System.out.println(calcRes);
    }

}

package main.day2;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.AbstractSolver;
import utils.AoCFileReader;

public class Day2Solver extends AbstractSolver {

    public Day2Solver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<List<String>> input = AoCFileReader.readListOfCharList(new File(this.inputFile1));
        int res2 = 0;
        int res3 = 0;
        for (List<String> l : input) {
            Map<String, Integer> resMap = new HashMap<>();
            for (String c : l) {
                if (!resMap.containsKey(c)) {
                    resMap.put(c, 1);
                } else {
                    resMap.put(c, resMap.get(c) + 1);
                }
            }
            boolean found2 = false;
            boolean found3 = false;
            for (Entry<String, Integer> entry : resMap.entrySet()) {
                if ((entry.getValue() == 2) && !found2) {
                    res2++;
                    found2 = true;
                }
                if ((entry.getValue() == 3) && !found3) {
                    res3++;
                    found3 = true;
                }
            }
        }
        System.out.println(res2 * res3);
    }

    @Override
    public void solvePart2() {
        List<List<String>> input = AoCFileReader.readListOfCharList(new File(this.inputFile2));

        for (List<String> l : input) {

            if (findDiff(l, input)) {
                break;
            }
        }
    }

    private boolean findDiff(List<String> comp, List<List<String>> input) {
        for (List<String> l : input) {
            int diffIndex = compare(comp, l);
            if (diffIndex != -1) {
                printWithoutIdx(l, diffIndex);
                return true;
            }
        }
        return false;

    }

    private void printWithoutIdx(List<String> l, int diffIndex) {
        for (int i = 0; i < l.size(); ++i) {
            if (i == diffIndex) {
                continue;
            }
            System.out.print(l.get(i));
        }
    }

    private int compare(List<String> comp, List<String> l) {
        int res = -1;
        int cntDiff = 0;
        for (int i = 0; i < comp.size(); ++i) {
            if (!comp.get(i).equals(l.get(i))) {
                cntDiff++;
                res = i;
            }
        }
        if (cntDiff == 1) {
            return res;
        }
        return -1;
    }

}

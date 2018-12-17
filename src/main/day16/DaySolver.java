package main.day16;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.AbstractSolver;
import main.day16.Opcode.CODES;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    private Map<Integer, List<CODES>> opcodemap = new HashMap<>();

    @Override
    public void solvePart1() {
        List<TestSample> input = AoCFileReader.readTestSamples(new File(this.inputFile1));

        int res = 0;

        for (TestSample ts : input) {
            int cnt = 0;
            for (CODES code : Opcode.CODES.values()) {
                if (Arrays.equals(Opcode.operate(ts.before, ts.operations, code), ts.after)) {
                    cnt++;
                }
            }
            if (cnt >= 3) {
                res++;
            }
        }
        System.out.println(res);
    }

    @Override
    public void solvePart2() {
        List<TestSample> input = AoCFileReader.readTestSamples(new File(this.inputFile1));
        List<int[]> program = AoCFileReader.readProgram(new File(this.inputFile2));

        int[] register = new int[] { 0, 0, 0, 0 };

        for (int i = 0; i < Opcode.CODES.values().length; ++i) {
            this.opcodemap.put(i, new ArrayList<>());
        }

        for (TestSample ts : input) {
            List<CODES> tempList = new ArrayList<>();
            for (CODES code : Opcode.CODES.values()) {
                if (Arrays.equals(Opcode.operate(ts.before, ts.operations, code), ts.after)) {
                    tempList.add(code);
                }
            }
            if (this.opcodemap.get(ts.operations[0]).isEmpty()) {
                this.opcodemap.get(ts.operations[0]).addAll(tempList);
                continue;
            }

            List<CODES> toRemove = new ArrayList<>();
            for (CODES c : tempList) {
                if (!this.opcodemap.get(ts.operations[0]).contains(c)) {
                    toRemove.add(c);
                }
            }

            this.opcodemap.get(ts.operations[0]).remove(toRemove);
        }

        while (hasMultipleEntries(this.opcodemap)) {

            for (Entry<Integer, List<CODES>> entry : this.opcodemap.entrySet()) {
                if (entry.getValue().size() == 1) {
                    for (Entry<Integer, List<CODES>> e : this.opcodemap.entrySet()) {
                        if (e.getValue().size() != 1) {
                            e.getValue().removeAll(entry.getValue());
                        }
                    }
                }
            }
        }

        for (int[] line : program) {
            register = Opcode.operate(register, line, this.opcodemap.get(line[0]).get(0));
        }

        System.out.println(register[0]);
    }

    private boolean hasMultipleEntries(Map<Integer, List<CODES>> opcodemap2) {
        for (Entry<Integer, List<CODES>> entry : this.opcodemap.entrySet()) {
            if (entry.getValue().size() > 1) {
                return true;
            }
        }
        return false;
    }

}

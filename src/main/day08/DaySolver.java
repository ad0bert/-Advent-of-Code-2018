package main.day08;

import java.io.File;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        String[] input = AoCFileReader.readOneLine(new File(this.inputFile1)).split(" ");
        System.out.println(buildTree(input).calcMetaData());
    }

    @Override
    public void solvePart2() {
        String[] input = AoCFileReader.readOneLine(new File(this.inputFile2)).split(" ");
        System.out.println(buildTree(input).calcMetaData2());
    }

    private Day8Node buildTree(String[] input) {
        Day8Node curr = null, old = null;
        int pos = 0;
        while (true) {

            int childCnt = Integer.parseInt(input[pos++]);
            int metaDataCnt = Integer.parseInt(input[pos++]);
            curr = new Day8Node(childCnt, metaDataCnt);

            if (old != null) {
                old.addNode(curr);
            } else {
                old = curr;
            }

            if (childCnt == 0) {
                for (int i = 0; i < metaDataCnt; ++i) {
                    curr.addMetaData(Integer.parseInt(input[pos++]));
                }
            }

            while (true) {
                while (curr.isFull() && curr.isDone() && (curr.parent != null)) {
                    curr = curr.parent;
                }

                if (curr.isFull() && !curr.isDone()) {
                    for (int i = 0; i < curr.metaDataCnt; ++i) {
                        curr.addMetaData(Integer.parseInt(input[pos++]));
                    }
                } else {
                    break;
                }
            }
            if ((curr.parent == null) && curr.isDone() && curr.isFull()) {
                break;
            }

            old = curr;
        }
        return curr;
    }

}

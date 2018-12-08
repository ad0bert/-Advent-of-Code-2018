package main.day08;

import java.util.ArrayList;
import java.util.List;

public class Day8Node {
    public Day8Node parent;
    public List<Day8Node> childNodes = new ArrayList<Day8Node>();
    public List<Integer> metaData = new ArrayList<Integer>();
    public int childNodeCnt = 0;
    public int metaDataCnt = 0;

    public Day8Node(int nodes, int data) {
        this.childNodeCnt = nodes;
        this.metaDataCnt = data;
    }

    public void addNode(Day8Node child) {
        child.parent = this;
        this.childNodes.add(child);
    }

    public boolean isFull() {
        return this.childNodes.size() == this.childNodeCnt;
    }

    public boolean isDone() {
        return this.metaData.size() == this.metaDataCnt;
    }

    public void addMetaData(int data) {
        this.metaData.add(data);
    }

    public int calcMetaData() {
        int res = sumMetaData();
        for (Day8Node child : this.childNodes) {
            res += child.calcMetaData();
        }
        return res;
    }

    public int calcMetaData2() {
        if (this.childNodeCnt == 0) {
            return sumMetaData();
        }
        int res = 0;
        for (int data : this.metaData) {
            if ((data <= this.childNodes.size()) && (data != 0)) {
                res += this.childNodes.get(data - 1).calcMetaData2();
            }
        }
        return res;
    }

    private int sumMetaData() {
        int res = 0;
        for (int data : this.metaData) {
            res += data;
        }
        return res;
    }

}

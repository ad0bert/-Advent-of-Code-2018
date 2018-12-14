package main.day14;

import java.util.List;

public class Day14List {

    public Day14Node curr1, curr2, begin, end;

    public long len = 0;

    public Day14List(int i, int j) {
        this.curr1 = this.begin = new Day14Node();
        this.curr2 = this.end = new Day14Node();
        this.curr1.val = i;
        this.curr2.val = j;
        this.curr1.aft = this.curr2;
        this.curr1.bef = this.curr2;
        this.curr2.aft = this.curr1;
        this.curr2.bef = this.curr1;
        this.len += 2;
    }

    public void add(int number) {
        Day14Node newNode = new Day14Node();
        newNode.val = number;
        newNode.aft = this.begin;
        newNode.bef = this.end;
        this.end.aft = newNode;
        this.end = newNode;
        this.begin.bef = newNode;
        this.len++;
    }

    public void move1() {
        for (int i = this.curr1.val + 1; i > 0; i--) {
            this.curr1 = this.curr1.aft;
        }
    }

    public void move2() {
        for (int i = this.curr2.val + 1; i > 0; i--) {
            this.curr2 = this.curr2.aft;
        }
    }

    public int sum() {
        return this.curr1.val + this.curr2.val;
    }

    public void print10(int begin) {
        Day14Node printNode = this.begin;
        for (int i = 0; i < (begin + 10); ++i) {
            if (i >= begin) {
                System.out.print(printNode.val);
            }
            printNode = printNode.aft;
        }
        System.out.println();
    }

    public boolean matchesList(List<Integer> input) {
        Day14Node currNode = this.end;
        for (int i = input.size() - 1; i >= 0; --i) {
            if ((currNode.val != input.get(i)) || (currNode == this.begin)) {
                return false;
            }
            currNode = currNode.bef;
        }
        return true;
    }
}

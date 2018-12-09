package main.day09;

public class Day9List {

    public Day9Node curr, begin, end;

    public void add(int number) {
        if (this.end == null) {
            this.end = new Day9Node();
            this.begin = this.curr = this.end;
            this.end.val = number;
            this.end.aft = this.begin;
            this.end.bef = this.begin;
        } else {
            Day9Node temp = this.end;
            this.end = new Day9Node();
            this.end.val = number;
            this.end.bef = temp;
            this.end.aft = this.begin;
            temp.aft = this.end;
        }
        this.curr = this.end;
    }

    public void add2(int number) {
        Day9Node toInsert = new Day9Node();
        toInsert.val = number;

        this.curr = this.curr.aft;
        toInsert.aft = this.curr.aft;
        toInsert.bef = this.curr;
        toInsert.aft.bef = toInsert;
        this.curr.aft = toInsert;
        this.curr = toInsert;
    }

    public long replace7() {
        long res = 0;
        Day9Node replace = this.curr.bef.bef.bef.bef.bef.bef.bef;
        Day9Node before = replace.bef;
        Day9Node after = replace.aft;

        before.aft = after;
        after.bef = before;

        res = replace.val;
        replace.bef = null;
        replace.aft = null;

        this.curr = after;
        return res;
    }
}

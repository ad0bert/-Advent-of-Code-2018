package main.day04;

import java.time.LocalDateTime;

@SuppressWarnings("rawtypes")
public class Day4Guard implements Comparable {

    public int[] sleegingMinutes = new int[60]; // starts awake ends awake
    public Integer guardId;

    public Day4Guard(int id) {
        this.guardId = id;
        for (int i = 0; i < this.sleegingMinutes.length; ++i) {
            this.sleegingMinutes[i] = 0;
        }
    }

    public void addTime(LocalDateTime sleepBegin, LocalDateTime sleepEnd) {
        for (int i = sleepBegin.getMinute(); i < sleepEnd.getMinute(); ++i) {
            this.sleegingMinutes[i]++;
        }
    }

    @Override
    public int compareTo(Object o) {
        return this.guardId.compareTo(((Day4Guard) o).guardId);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(this.guardId);
        for (int sleegingMinute : this.sleegingMinutes) {
            res.append(" ").append(sleegingMinute).append(" ");
        }
        return res.toString();
    }

    public int cntSleepingMinutes() {
        int res = 0;
        for (int sleegingMinute : this.sleegingMinutes) {
            res += sleegingMinute;
        }
        return res;
    }

    public int getMaxSleepingMinute() {
        int max = Integer.MIN_VALUE;
        int res = 0;
        for (int i = 0; i < this.sleegingMinutes.length; ++i) {
            if (this.sleegingMinutes[i] > max) {
                max = this.sleegingMinutes[i];
                res = i;
            }
        }

        return res;
    }

    public int getMaxSleepingMinute2() {
        int max = Integer.MIN_VALUE;
        for (int sleegingMinute : this.sleegingMinutes) {
            if (sleegingMinute > max) {
                max = sleegingMinute;
            }
        }

        return max;
    }

}

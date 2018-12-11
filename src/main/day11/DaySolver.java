package main.day11;

import main.AbstractSolver;

public class DaySolver extends AbstractSolver {

    // for reasons we will start at index 1
    private final int DIM = 301;
    private final int SQUARE = 3;

    private final long SERIAL_NO = 4172;

    private final long TEN = 10;
    private final long FIVE = 10 / 2;
    private int[][] batteries = new int[this.DIM][this.DIM];

    public DaySolver(String day) {
        super(day);
        init();
    }

    private void init() {
        for (int i = 1; i < this.batteries.length; ++i) {
            this.batteries[i] = new int[this.DIM];
        }
    }

    private void calcBat(long serialNo) {
        for (int i = 1; i < this.batteries.length; ++i) {
            for (int j = 1; j < this.batteries[i].length; ++j) {
                this.batteries[i][j] = calcPowerLevel(i, j, serialNo);
            }
        }
    }

    private int calcPowerLevel(int i, int j, long serialNo) {
        long rackId = i + this.TEN;
        long powerLevel = rackId * j;
        long res = (powerLevel + serialNo) * rackId;
        res /= 100;
        res %= 10;
        return (int) (res - this.FIVE);
    }

    private int calcSquare(int i, int j, int size) {
        int res = 0;
        for (int k = i; k < (i + size); ++k) {
            for (int l = j; l < (j + size); ++l) {
                res += this.batteries[k][l];
            }
        }
        return res;
    }

    @Override
    public void solvePart1() {

        int resI = 0;
        int resJ = 0;
        int max = Integer.MIN_VALUE;

        this.calcBat(this.SERIAL_NO);
        for (int i = 1; i < (this.batteries.length - this.SQUARE); ++i) {
            for (int j = 1; j < (this.batteries[i].length - this.SQUARE); ++j) {
                int val = calcSquare(i, j, this.SQUARE);
                if (val > max) {
                    max = val;
                    resI = i;
                    resJ = j;
                }
            }
        }

        System.out.println("[" + resI + "," + resJ + "]: " + max);
    }

    @Override
    public void solvePart2() {
        int resI = 0;
        int resJ = 0;
        int resSize = 0;
        int max = Integer.MIN_VALUE;

        this.calcBat(this.SERIAL_NO);
        for (int size = this.SQUARE; size < (this.DIM); ++size) {
            for (int i = 1; i < (this.batteries.length - size); ++i) {
                for (int j = 1; j < (this.batteries[i].length - size); ++j) {
                    int val = calcSquare(i, j, size);
                    if (val > max) {
                        max = val;
                        resI = i;
                        resJ = j;
                        resSize = size;
                    }
                }
            }
        }

        System.out.println("[" + resI + "," + resJ + "," + resSize + "]: " + max);
    }

}

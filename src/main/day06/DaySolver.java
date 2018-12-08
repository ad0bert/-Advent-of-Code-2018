package main.day06;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    class Value {
        char val = '-';
        int dist = Integer.MAX_VALUE;
    }

    class Value1 {
        char val = '-';
        int dist = 0;
    }

    public DaySolver(String day) {
        super(day);
    }

    private int maxX(List<Day6Point> input) {
        int max = Integer.MIN_VALUE;
        for (Day6Point point : input) {
            if (max < point.x) {
                max = point.x;
            }
        }
        return max;
    }

    private int maxY(List<Day6Point> input) {
        int max = Integer.MIN_VALUE;
        for (Day6Point point : input) {
            if (max < point.y) {
                max = point.y;
            }
        }
        return max;
    }

    @Override
    public void solvePart1() {
        List<Day6Point> input = AoCFileReader.readPointList(new File(this.inputFile1));

        int maxX = maxX(input);
        int maxY = maxY(input);
        Value[][] values = new Value[maxX][maxY];

        for (int i = 0; i < maxX; ++i) {
            for (int j = 0; j < maxY; ++j) {
                values[i][j] = new Value();
            }
        }

        for (Day6Point point : input) {
            for (int i = 0; i < maxX; ++i) {
                for (int j = 0; j < maxY; ++j) {
                    int dist = Math.abs(point.x - i) + Math.abs(point.y - j);
                    if (dist < values[i][j].dist) {
                        values[i][j].dist = dist;
                        values[i][j].val = point.c;
                    } else if (dist == values[i][j].dist) {
                        values[i][j].val = '.';
                    }
                }
            }
        }

        for (int i = 0; i < maxX; ++i) {
            removeAll(values[i][0].val, values);
        }

        for (int i = 0; i < maxX; ++i) {
            removeAll(values[i][maxY - 1].val, values);
        }

        for (int i = 0; i < maxY; ++i) {
            removeAll(values[0][i].val, values);
        }

        for (int i = 0; i < maxY; ++i) {
            removeAll(values[maxX - 1][i].val, values);
        }

        Map<Character, Integer> valueCnt = new HashMap<>();

        for (Value[] value : values) {
            for (int j = 0; j < values.length; ++j) {

                if ((value[j].val == '.') || (value[j].val == '!')) {
                    continue;
                }

                if (valueCnt.get(value[j].val) == null) {
                    valueCnt.put(value[j].val, 1);
                } else {
                    valueCnt.put(value[j].val, valueCnt.get(value[j].val) + 1);
                }
            }
        }

        int max = Integer.MIN_VALUE;

        for (Entry<Character, Integer> entry : valueCnt.entrySet()) {
            if (max < entry.getValue()) {
                max = entry.getValue();
            }
        }

        System.out.println(max);

    }

    private void removeAll(char val, Value[][] values) {
        for (int i = 0; i < values.length; ++i) {
            for (int j = 0; j < values[0].length; ++j) {
                if ((values[i][j].val == val) && (val != '.')) {
                    values[i][j].val = '!';
                }
            }
        }

    }

    @Override
    public void solvePart2() {
        List<Day6Point> input = AoCFileReader.readPointList(new File(this.inputFile2));
        int maxX = maxX(input);
        int maxY = maxY(input);
        Value1[][] values = new Value1[maxX][maxY];

        for (int i = 0; i < maxX; ++i) {
            for (int j = 0; j < maxY; ++j) {
                values[i][j] = new Value1();
            }
        }

        for (Day6Point point : input) {
            for (int i = 0; i < maxX; ++i) {
                for (int j = 0; j < maxY; ++j) {
                    values[i][j].dist += Math.abs(point.x - i) + Math.abs(point.y - j);
                    if (values[i][j].dist < 10000) {
                        values[i][j].val = '#';
                    } else {
                        values[i][j].val = '.';
                    }
                }
            }
        }

        int size = 0;

        for (int i = 0; i < maxX; ++i) {
            for (int j = 0; j < maxY; ++j) {
                if (values[i][j].val == '#') {
                    size++;
                }
            }
        }

        System.out.println(size);

    }

}

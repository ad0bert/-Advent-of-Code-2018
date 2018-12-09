package main.day09;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<String> input = AoCFileReader.readMulitpleLines(new File(this.inputFile1));

        long res = 0;

        for (String line : input) {
            String reduced = line.replaceFirst(" players; last marble is worth ", " ");
            reduced = reduced.replaceFirst(" points", "");
            String[] in = reduced.split(" ");
            // System.out.println(line + ": high score is " +
            // runGame(Integer.parseInt(in[0]), Integer.parseInt(in[1])));
            res = runGame(Integer.parseInt(in[0]), Integer.parseInt(in[1]));
        }
        System.out.println(res);
    }

    @Override
    public void solvePart2() {
        List<String> input = AoCFileReader.readMulitpleLines(new File(this.inputFile2));
        long res = 0;
        for (String line : input) {
            String reduced = line.replaceFirst(" players; last marble is worth ", " ");
            reduced = reduced.replaceFirst(" points", "");
            String[] in = reduced.split(" ");
            // System.out.println(
            // line + ": high score is " +
            // runGameOwnList(Integer.parseInt(in[0]),
            // Integer.parseInt(in[1])));
            res = runGameOwnList(Integer.parseInt(in[0]), Integer.parseInt(in[1]));
        }
        System.out.println(res);
    }

    private int runGame(int playerCnt, int maxNumber) {
        List<Integer> game = new LinkedList<>();
        int[] players = new int[playerCnt];
        int currPlayer = 0;
        int currPos = 0;
        game.add(0);
        for (int i = 1; i <= maxNumber; ++i) {
            if ((i % 23) == 0) {
                players[currPlayer] += i;
                currPos -= 7;
                if (currPos < 0) {
                    currPos = game.size() + currPos;
                }
                players[currPlayer] += game.get(currPos);
                game.remove(currPos);
            } else {
                currPos = (currPos + 2) % game.size();
                if (currPos != 0) {
                    game.add(currPos, i);
                } else {
                    game.add(i);
                    currPos = game.size() - 1;
                }
            }
            currPlayer = (currPlayer + 1) % players.length;
        }

        int max = Integer.MIN_VALUE;
        for (int player : players) {
            if (max < player) {
                max = player;
            }
        }
        return max;
    }

    private long runGameOwnList(int playerCnt, int maxNumber) {
        Day9List game = new Day9List();
        long[] players = new long[playerCnt];
        int currPlayer = 0;
        game.add(0);
        for (int i = 1; i <= maxNumber; ++i) {

            if ((i % 23) == 0) {
                players[currPlayer] += (game.replace7() + i);
            } else {
                game.add2(i);
            }
            currPlayer = (currPlayer + 1) % players.length;
        }
        long max = Integer.MIN_VALUE;
        for (long player : players) {
            if (max < player) {
                max = player;
            }
        }
        return max;
    }

}

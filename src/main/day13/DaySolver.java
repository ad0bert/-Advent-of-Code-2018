package main.day13;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    private char[][] mace;

    public DaySolver(String day) {
        super(day);
    }

    @SuppressWarnings("unused")
    private void printMace(List<Day13Cart> carts) {
        for (int i = 0; i < this.mace.length; ++i) {
            for (int j = 0; j < this.mace[i].length; ++j) {

                boolean findCart = false;
                for (Day13Cart cart : carts) {
                    if ((cart.x == i) && (cart.y == j)) {
                        System.out.print(cart.getCart());
                        findCart = true;
                        break;
                    }
                }
                if (!findCart) {
                    System.out.print(this.mace[i][j]);
                }
            }
            System.out.println();
        }
    }

    private void initMace(List<String> input) {
        this.mace = new char[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); ++i) {
            this.mace[i] = input.get(i).toCharArray();
        }
    }

    @Override
    public void solvePart1() {
        List<String> input = AoCFileReader.readMulitpleLines(new File(this.inputFile1));
        initMace(input);

        List<Day13Cart> carts = new ArrayList<>();

        for (int i = 0; i < this.mace.length; ++i) {
            for (int j = 0; j < this.mace[i].length; ++j) {
                char curr = this.mace[i][j];
                if ((curr == '<') || (curr == '>')) {
                    carts.add(new Day13Cart(curr, i, j));
                    this.mace[i][j] = '-';
                } else if ((curr == 'v') || (curr == '^')) {
                    carts.add(new Day13Cart(curr, i, j));
                    this.mace[i][j] = '|';
                }
            }
        }

        boolean isRunning = true;

        while (isRunning) {

            carts.sort(new Comparator<Day13Cart>() {

                @Override
                public int compare(Day13Cart cart1, Day13Cart cart2) {
                    if (cart1.x != cart2.x) {
                        return new Integer(cart1.x).compareTo(cart2.x);
                    }

                    int comp = new Integer(cart1.y).compareTo(cart2.y);
                    if (comp == -1) {
                        return 1;
                    }
                    if (comp == 1) {
                        return -1;
                    }
                    return 0;
                }

            });

            for (Day13Cart cart : carts) {
                cart.move(this.mace[cart.x][cart.y]);

                if (cart.hasCollision(carts)) {

                    System.out.println(cart.y + "," + cart.x);
                    isRunning = false;
                    break;
                }

            }

        }

    }

    @Override
    public void solvePart2() {
        List<String> input = AoCFileReader.readMulitpleLines(new File(this.inputFile2));
        initMace(input);

        List<Day13Cart> carts = new ArrayList<>();

        for (int i = 0; i < this.mace.length; ++i) {
            for (int j = 0; j < this.mace[i].length; ++j) {
                char curr = this.mace[i][j];
                if ((curr == '<') || (curr == '>')) {
                    carts.add(new Day13Cart(curr, i, j));
                    this.mace[i][j] = '-';
                } else if ((curr == 'v') || (curr == '^')) {
                    carts.add(new Day13Cart(curr, i, j));
                    this.mace[i][j] = '|';
                }
            }
        }

        while (carts.size() > 1) {

            carts.sort(new Comparator<Day13Cart>() {

                @Override
                public int compare(Day13Cart cart1, Day13Cart cart2) {
                    if (cart1.x != cart2.x) {
                        return new Integer(cart1.x).compareTo(cart2.x);
                    }

                    return new Integer(cart1.y).compareTo(cart2.y);
                }

            });

            List<Day13Cart> toRemove = new ArrayList<>();

            for (Day13Cart cart : carts) {
                if (toRemove.contains(cart)) {
                    continue;
                }
                cart.move(this.mace[cart.x][cart.y]);
                Day13Cart colided = cart.getCollision(carts);
                if (colided != null) {
                    toRemove.add(cart);
                    toRemove.add(colided);
                }
            }
            carts.removeAll(toRemove);
        }
        System.out.println(carts.get(0).y + "," + carts.get(0).x);
    }

}

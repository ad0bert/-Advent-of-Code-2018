package main.day14;

import java.util.ArrayList;
import java.util.List;

import main.AbstractSolver;

public class DaySolver extends AbstractSolver {

    private static final int LIST_LEN = 633601;

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        Day14List recipes = new Day14List(3, 7);

        while (recipes.len < (LIST_LEN + 10)) {

            int newRecipes = recipes.sum();
            if (newRecipes > 9) {
                recipes.add(newRecipes / 10);
                recipes.add(newRecipes % 10);
            } else {
                recipes.add(newRecipes);
            }
            recipes.move1();
            recipes.move2();
        }

        recipes.print10(LIST_LEN);
    }

    @Override
    public void solvePart2() {
        Day14List recipes = new Day14List(3, 7);

        List<Integer> input = new ArrayList<>();
        input.add(6);
        input.add(3);
        input.add(3);
        input.add(6);
        input.add(0);
        input.add(1);

        while (!recipes.matchesList(input)) {

            int newRecipes = recipes.sum();
            if (newRecipes > 9) {
                recipes.add(newRecipes / 10);
                if (recipes.matchesList(input)) {
                    break;
                }
                recipes.add(newRecipes % 10);
            } else {
                recipes.add(newRecipes);
            }
            recipes.move1();

            recipes.move2();
        }

        System.out.println(recipes.len - input.size());
    }

}

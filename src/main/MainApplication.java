package main;

import java.time.LocalDateTime;

public class MainApplication {

    public static void main(String[] args) {
        AbstractSolver as = DaySolverFactory.getSolver(LocalDateTime.now().getDayOfMonth());
        as.solvePart1();
        as.solvePart2();
    }

}

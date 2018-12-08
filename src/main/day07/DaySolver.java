package main.day07;

import java.io.File;
import java.util.Collections;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void solvePart1() {
        List<Day7Node> input = AoCFileReader.readNodeSet(new File(this.inputFile1));
        Collections.sort(input);
        StringBuilder sb = new StringBuilder();

        while (!input.isEmpty()) {
            for (Character c : sb.toString().toCharArray()) {
                for (Day7Node node : input) {
                    node.preConditions.remove(c);
                }
            }

            for (Day7Node node : input) {
                if (node.preConditions.isEmpty()) {
                    sb.append(node.node);
                    input.remove(node);
                    break;
                }
            }
        }
        System.out.println(sb.toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void solvePart2() {
        List<Day7Node> input = AoCFileReader.readNodeSet(new File(this.inputFile1));

        Day7Worker[] workers = new Day7Worker[5];

        for (int i = 0; i < workers.length; ++i) {
            workers[i] = new Day7Worker();
        }

        Collections.sort(input);

        StringBuilder sb = new StringBuilder();

        int timeSpent = 0;

        while (!input.isEmpty()) {
            for (Character c : sb.toString().toCharArray()) {
                for (Day7Node node : input) {
                    node.preConditions.remove(c);
                }
            }

            boolean hasBreak = false;

            for (Day7Node node : input) {
                if (node.preConditions.isEmpty() && !node.isRemoved) {

                    for (Day7Worker worker : workers) {
                        if (worker.workload == 0) {

                            worker.node = node;
                            node.isRemoved = true;
                            worker.workload = node.time;
                            hasBreak = true;
                            break;
                        }
                    }

                    boolean allWork = true;

                    for (Day7Worker worker : workers) {
                        if (worker.workload == 0) {
                            allWork = false;
                            break;
                        }
                    }

                    if (!allWork) {
                        continue;
                    }

                    if (hasBreak) {
                        break;
                    }
                }
            }

            for (Day7Worker worker : workers) {
                if (worker.workload > 0) {
                    worker.workload--;

                    if (worker.workload == 0) {
                        sb.append(worker.node.node);
                    }

                }
            }

            timeSpent++;

            input.removeIf((x) -> {
                return x.isRemoved;
            });

        }

        for (Day7Worker worker : workers) {
            if (worker.workload > 0) {
                timeSpent += worker.workload;
                sb.append(worker.node.node);
            }

        }

        System.out.println(sb.toString() + " in: " + timeSpent + " seconds.");
    }

}

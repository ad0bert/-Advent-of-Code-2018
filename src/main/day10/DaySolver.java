package main.day10;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<Day10Point> input = AoCFileReader.readPointVelList(new File(this.inputFile1));
        // Modified point drawing from
        // http://zetcode.com/gfx/java2d/basicdrawing/
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                PointsEx ex = new PointsEx(input);
                ex.setVisible(true);
            }
        });
    }

    @Override
    public void solvePart2() {

    }

}

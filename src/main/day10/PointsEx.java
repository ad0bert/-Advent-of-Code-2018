/*
 * Modified point drawing from http://zetcode.com/gfx/java2d/basicdrawing/
 */

package main.day10;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Timer;

public class PointsEx extends JFrame {

    private static final long serialVersionUID = 1L;

    public PointsEx(List<Day10Point> input) {

        initUI(input);
    }

    private void initUI(List<Day10Point> input) {

        final Day10Drawer surface = new Day10Drawer(input);
        add(surface);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.getTimer();
                timer.stop();
            }
        });

        setTitle("Points");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

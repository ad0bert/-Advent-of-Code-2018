/*
 * Modified point drawing from http://zetcode.com/gfx/java2d/basicdrawing/
 */

package main.day10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

class Day10Drawer extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final int DELAY = 150;
    private Timer timer;

    private int i = 0;

    private List<Day10Point> points;

    public Day10Drawer(List<Day10Point> input) {

        this.points = input;

        initTimer();
    }

    private void initTimer() {

        this.timer = new Timer(this.DELAY, this);
        this.timer.start();
    }

    public Timer getTimer() {

        return this.timer;
    }

    private int getM() {
        int x = this.points.get(0).x;
        if (Math.abs(x) < 223) {
            return 0;
        } else if (Math.abs(x) < 300) {
            return 1;
        } else {
            return 100;
        }
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.blue);

        final int m = getM();

        this.points.parallelStream().forEach((point) -> {
            g2d.drawLine(point.x, point.y, point.x, point.y);
            point.move(m);

        });

        this.i += m;

        System.out.println(this.i);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
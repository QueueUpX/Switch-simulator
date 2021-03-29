package apps.student;

import javax.swing.*;
import java.awt.*;

public class GridGraph extends JPanel {
    private int offset = 10;
    private double startValue, endValue, step;

    public GridGraph() {
        super();

        this.setPreferredSize(Student.size);
    }
    public GridGraph(double startValue, double endValue, double step) {
        super();

        this.startValue = startValue;
        this.endValue = endValue;
        this.step = step;

        this.setPreferredSize(Student.size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        makeGrid(g2d);
    }

    private void makeGrid(Graphics2D g) {
        for(int i=0; i<=Student.size.height; i+=offset) {
            g.drawLine(0, i, Student.size.width,i);
        }
        for(int i=0; i<=Student.size.width; i+=offset) {
            g.drawLine(i, 0, i,Student.size.height);
        }
    }

    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }

    public void setEndValue(double endValue) {
        this.endValue = endValue;
    }

    public void setStep(double step) {
        this.step = step;
    }
}

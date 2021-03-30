package apps.student;

import javax.swing.*;
import java.awt.*;

public class GridGraph extends JPanel {
    private int offset = 30;
    private double startValue, endValue, step;
    private JFrame frame;

    public GridGraph() {
        super();

        init();
        setup();
        addComponents();

        this.setPreferredSize(new Dimension(600, 600));
        frame.add(this);
    }
    public GridGraph(double startValue, double endValue, double step) {
        super();

        this.startValue = startValue;
        this.endValue = endValue;
        this.step = step;

        init();
        setup();
        addComponents();

        this.setSize(new Dimension(650, 650));
        frame.add(this);
    }

    private void init() {
        frame = new JFrame("SCR_Apps_STS/TST_1.0");
    }

    private void setup() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(STS_TST.size.width+50, STS_TST.size.height+60));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        frame.setVisible(true);
    }

    private void addComponents() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        makeGrid(g2d);
    }

    private void makeGrid(Graphics2D g) {
        for(int i=0; i<=STS_TST.size.height; i+=offset) {
            g.drawLine(0, i, STS_TST.size.width,i);
        }
        for(int i=0; i<=STS_TST.size.width; i+=offset) {
            g.drawLine(i, 0, i,STS_TST.size.height);
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

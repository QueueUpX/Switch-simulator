package tools;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GridGraph extends JPanel {
    private int offset = 50, extraSpace = 100;
    private final int width = 500+extraSpace, height = 500+extraSpace;
    private JFrame frame;
    private ArrayList<Double> verticalValues, verticalSet, horizontalValues, horizontalSet;
    private ArrayList<Point2D> points;
    private JButton show;

    public GridGraph(ArrayList<Double> verticalValues, ArrayList<Double> horizontalValues) {
        super();

        this.verticalValues = verticalValues;
        this.horizontalValues = horizontalValues;

        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);

        init();
        setup();
        addComponents();
    }

    private void init() {
        frame = new JFrame("SCR_Apps_GridGraph_1.0");
        if(verticalValues == null && horizontalValues == null) {
            verticalValues = new ArrayList<>();
            horizontalValues = new ArrayList<>();
        }
        verticalSet = new ArrayList<>();
        horizontalSet = new ArrayList<>();
        points = new ArrayList<>();
        show = new JButton("<html><h3>Show points</h3></html>");
    }

    private void setup() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(width+extraSpace/2, height));
        frame.setResizable(false);
        frame.setVisible(true);
        show.setBounds(265, 500, 120, 30);
        show.setFocusable(false);
        show.addActionListener(e -> {
            JFrame frame = new JFrame("SCR_Apps_GridGraph_1.0");
            JPanel panel = new JPanel();
            JTextArea textArea = new JTextArea();
            textArea.setFont(new Font("Arial", Font.BOLD, 16));
            textArea.setEditable(false);
            textArea.setRows(20);
            textArea.setColumns(30);
            for(int i=0; i<horizontalValues.size(); i++) {
                textArea.append("Point " + i + "\n");
                textArea.append("X: " + horizontalValues.get(i) + " Y: " + verticalValues.get(i) + "\n\n");
            }
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            panel.add(scrollPane);
            frame.add(panel);
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });
        setVerticalSet();
        setHorizontalSet();
        sort();
        setPoints();
    }

    private void addComponents() {
        this.add(show);
        frame.add(this);
        frame.pack();
    }

    private double getMaxValue(ArrayList<Double> values) {
        double max = values.get(0);
        for(int i=1; i<values.size(); i++) {
            if(values.get(i) > max) {
                max = values.get(i);
            }
        }
        return max;
    }

    private double getMinValue(ArrayList<Double> values) {
        double min = values.get(0);
        for(int i=1; i<values.size(); i++) {
            if(values.get(i) < min) {
                min = values.get(i);
            }
        }
        return min;
    }

    private void setVerticalSet() {
        double max = getMaxValue(verticalValues);
        double min = getMinValue(verticalValues);
        double step = (max - min) / ((double)(height/offset)-3);
        verticalSet.add(Tools.round(max, 2));
        for(int i=offset; i<height-2*extraSpace+extraSpace/10; i+=offset) {
            verticalSet.add(Tools.round(max - (((double)i/offset) * step), 2));
        }
        verticalSet.add(Tools.round(min, 2));
    }

    private void setHorizontalSet() {
        double max = getMaxValue(horizontalValues);
        double min = getMinValue(horizontalValues);
        double step = (max - min) / ((double)(width/offset)-3);
        horizontalSet.add(Tools.round(min, 2));
        for(int i=extraSpace+offset; i<width; i+=offset) {
            horizontalSet.add((Tools.round(min + (((double)(i-extraSpace)/offset) * step), 2)));
        }
        horizontalSet.add(Tools.round(max, 2));
    }

    private void sort() {
        int n = horizontalValues.size();
        for(int i=0; i<n-1; i++) {
            for(int j=0; j<n-i-1; j++) {
                if(horizontalValues.get(j) > horizontalValues.get(j+1)) {
                    double temp = horizontalValues.get(j);
                    horizontalValues.set(j, horizontalValues.get(j+1));
                    horizontalValues.set(j+1, temp);
                    temp = verticalValues.get(j);
                    verticalValues.set(j, verticalValues.get(j+1));
                    verticalValues.set(j+1, temp);
                }
            }
        }
    }

    private void setPoints() {
        double maxHorizontal = horizontalSet.get(horizontalSet.size()-1);
        double maxVertical = verticalSet.get(0);
        double horizontalProc, verticalProc, x, y;
        for(int i=0; i<horizontalValues.size(); i++) {
            horizontalProc = (horizontalValues.get(i)-horizontalValues.get(0)) / (maxHorizontal-horizontalValues.get(0));
            x = horizontalProc * 450.0;
            verticalProc = 1.0 - ((verticalValues.get(i)-verticalSet.get(verticalSet.size()-1)) /
                    (maxVertical-verticalSet.get(verticalSet.size()-1)));
            if(Double.isNaN(((verticalValues.get(i) - verticalSet.get(verticalSet.size() - 1)) /
                    (maxVertical - verticalSet.get(verticalSet.size() - 1))))) {
                verticalProc = 1.0;
            }
            y = verticalProc * 450;
            /*if(y > height-offset-extraSpace) {
                y = height-offset-extraSpace;
            }
            if(y < (double)extraSpace/10) {
                y = 0;
            }*/
            points.add(new Point2D.Double(x+extraSpace, y+(double)extraSpace/10));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        makeGrid(g2d);
        drawVerticalValues(g2d);
        drawHorizontalValues(g2d);
        drawPoints(g2d);
    }

    private void makeGrid(Graphics2D g) {
        g.setColor(Color.lightGray);
        for(int i=extraSpace/10; i<width-extraSpace+extraSpace/10; i+=offset) {
            g.drawLine(extraSpace, i, width-offset,i);
        }
        for(int i=extraSpace; i<=height-offset; i+=offset) {
            g.drawLine(i, extraSpace/10, i,height-offset-extraSpace+extraSpace/10);
        }
    }

    private void drawVerticalValues(Graphics2D g) {
        g.setColor(Color.black);
        String value;
        g.drawString(String.valueOf(verticalSet.get(0)), extraSpace-g.getFontMetrics().stringWidth(String.valueOf(verticalSet.get(0)))-5,
                g.getFontMetrics().getHeight()+g.getFontMetrics().getHeight()/4);
        for(int i=offset; i<height-2*extraSpace+extraSpace/10; i+=offset) {
            value = String.valueOf(verticalSet.get(i/offset));
            g.drawString(value, extraSpace-g.getFontMetrics().stringWidth(value)-5, i+g.getFontMetrics().getHeight());
        }
        g.drawString(String.valueOf(verticalSet.get(verticalSet.size()-1)),
                extraSpace-g.getFontMetrics().stringWidth(String.valueOf(verticalSet.get(verticalSet.size()-1)))-5,
                height-extraSpace-extraSpace/10-2*g.getFontMetrics().getHeight());
    }

    private void drawHorizontalValues(Graphics2D g) {
        g.setColor(Color.black);
        String value;
        g.drawString(String.valueOf(horizontalSet.get(0)), extraSpace-g.getFontMetrics().stringWidth(String.valueOf(horizontalSet.get(0))),
                height-extraSpace+extraSpace/10-offset+g.getFontMetrics().getHeight());
        for(int i=extraSpace+offset; i<width-offset; i+=offset) {
            value = String.valueOf(horizontalSet.get((i-extraSpace)/offset));
            g.drawString(value, i-g.getFontMetrics().stringWidth(value)/2,
                    height-extraSpace+extraSpace/10-offset+g.getFontMetrics().getHeight());
        }
        g.drawString(String.valueOf(horizontalSet.get(horizontalSet.size()-1)),
                width-g.getFontMetrics().stringWidth(String.valueOf(horizontalSet.get(horizontalSet.size()-1)))-offset,
                height-extraSpace+extraSpace/10-offset+g.getFontMetrics().getHeight());
    }

    private void drawPoints(Graphics2D g) {
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.red);
        for(int i=0; i<points.size()-1; i++) {
            g.drawLine((int)points.get(i).getX(), (int)points.get(i).getY(), (int)points.get(i+1).getX(), (int)points.get(i+1).getY());
        }
    }

    public void setVerticalValues(ArrayList<Double> verticalValues) {
        this.verticalValues = verticalValues;
    }

    public void setHorizontalValues(ArrayList<Double> horizontalValues) {
        this.horizontalValues = horizontalValues;
    }
}


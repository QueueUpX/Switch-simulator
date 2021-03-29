package apps.delta;

import java.awt.*;
import java.util.ArrayList;

public class Line {
    public int x1, y1, x2, y2;
    public static final ArrayList<Color> colors = new ArrayList<>() {
        {
            add(Color.blue);
            add(Color.green);
            add(Color.orange);
            add(Color.magenta);
            add(Color.yellow);
            add(Color.pink);
            add(Color.cyan);
            add(Color.darkGray);
            add(Color.black);
            add(Color.red);
        }
    };
    private Color color = Color.black;
    private BasicStroke basicStroke = new BasicStroke(1);
    private ArrayList<Color> colorStack = new ArrayList<>();

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(basicStroke);
        if (colorStack.size() > 1) {
            g.setColor(Color.red);
            g.setStroke(new BasicStroke(5));
        }
        g.drawLine(x1, y1, x2, y2);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        if (color != Color.black) {
            basicStroke = new BasicStroke(3);
        } else {
            basicStroke = new BasicStroke(1);
        }
    }

    void addStack(Color color) {
        if (!colorStack.contains(color)) {
            colorStack.add(color);
        }
    }

    void decreaseStack(Color color) {
        colorStack.remove(color);
    }
}

package apps.bellmanford;

import java.awt.*;

public class Line {
    private final int x1, y1, x2, y2;

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(2));
        g.drawLine(x1, y1, x2, y2);
    }
}

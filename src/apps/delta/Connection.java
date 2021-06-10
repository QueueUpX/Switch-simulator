package apps.delta;

import java.awt.*;
import java.util.ArrayList;

public class Connection {
    private ArrayList<Line> lines;
    private ArrayList<Integer> road;

    Connection() {
        init();
    }

    private void init() {
        lines = new ArrayList<>();
        road = new ArrayList<>();
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void setRoad(int[] road) {
        for (int i = 0; i < road.length; i++) {
            this.road.add(road[i]);
        }
    }

    public void draw(Graphics2D g) {
        for (Line i : lines) {
            i.draw(g);
        }
    }

    ArrayList<Line> getLines() {
        return lines;
    }
}

package apps.benes;

import java.awt.*;

public class Pin {
    private int x1, y1, x2, y2;
    private boolean connected = false;
    private int id;
    private int option = -1;

    public Pin(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(1));
        g.drawLine(x1, y1, x2, y2);
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public int isConnected() {
        return connected ? 1 : 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }
}
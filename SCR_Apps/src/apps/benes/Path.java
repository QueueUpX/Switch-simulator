package apps.benes;

import java.awt.*;

public class Path {
    private Pin in, out;
    private Color color = Color.black;
    private boolean state = false;
    private int id;
    private int option;

    public Path(Pin out, Pin in) {
        this.in = in;
        this.out = out;

        setup();
    }

    private void setup() {

    }

    public void drawOut(Graphics2D g) {
        g.setStroke(new BasicStroke(1));
        if(color != Color.black) {
            g.setStroke(new BasicStroke(4));
        }
        g.setColor(color);
        g.drawLine(out.getX2(), out.getY2(), in.getX2(), in.getY2());
    }

    public void drawIn(Graphics2D g) {
        g.setStroke(new BasicStroke(1));
        if(color != Color.black) {
            g.setStroke(new BasicStroke(4));
        }
        g.setColor(color);
        g.drawLine(out.getX1(), out.getY1(), in.getX1(), in.getY1());
    }

    public Pin getInPin() {
        return in;
    }

    public Pin getOutPin() {
        return out;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setState(boolean state) {
        this.state = state;
        in.setConnected(state);
        out.setConnected(state);
    }

    public int isState() {
        return state ? 1 : 0;
    }

    public void setId(int id, int option) throws PinUsedException {
        if(in.isConnected() == 1 || out.isConnected() == 1) {
            throw new PinUsedException();
        }
        this.id = id;
        this.option = option;
        in.setId(id);
        in.setOption(option);
        out.setId(id);
        out.setOption(option);
    }

    public int getId() {
        return id;
    }
}

class PinUsedException extends Exception {
    PinUsedException() {
        super();
    }
}
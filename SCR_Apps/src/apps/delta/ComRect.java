package apps.delta;

import java.awt.*;

public class ComRect {

    private final int width = 90;
    private final int height = 60;
    private final int pinWidth = width / 5;
    private final int pinHeightOffset = height / 5;
    private int x0, y0;
    private int x1, y1;
    private int x;
    private int y;

    ComRect(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void draw(Graphics2D g) {
        g.drawRect(x, y, width, height);
        drawPins(g);
        drawNumbers(g);
    }

    private void drawPins(Graphics2D g) {
        g.setStroke(new BasicStroke(2));
        // **** Draw left pins
        g.drawLine(x, y + pinHeightOffset, x - pinWidth, y + pinHeightOffset); // up
        g.drawLine(x, y + height - pinHeightOffset, x - pinWidth, y + height - pinHeightOffset); // down
        // **** Draw right pins
        g.drawLine(x + width, y + pinHeightOffset, x + width + pinWidth, y + pinHeightOffset); // up
        g.drawLine(x + width, y + height - pinHeightOffset, x + width + pinWidth, y + height - pinHeightOffset); // down
        g.setStroke(new BasicStroke(1));
    }

    private void drawNumbers(Graphics2D g) {
        g.setFont(new Font("Arial", Font.BOLD, 12));
        x0 = x + width - g.getFontMetrics().stringWidth("0 ");
        y0 = y + pinHeightOffset + g.getFontMetrics().getHeight() / 4;
        g.drawString("0", x0, y0);
        x1 = x + width - g.getFontMetrics().stringWidth("1 ");
        y1 = y + height - pinHeightOffset + g.getFontMetrics().getHeight() / 4;
        g.drawString("1", x1, y1);
    }

    public Pin getPinLeftUp() {
        return new Pin(x, y + pinHeightOffset, x - pinWidth, y + pinHeightOffset);
    }

    public Pin getPinLeftDown() {
        return new Pin(x, y + height - pinHeightOffset, x - pinWidth, y + height - pinHeightOffset);
    }

    public Pin getPinRightUp() {
        return new Pin(x + width, y + pinHeightOffset, x + width + pinWidth, y + pinHeightOffset);
    }

    public Pin getPinRightDown() {
        return new Pin(x + width, y + height - pinHeightOffset, x + width + pinWidth, y + height - pinHeightOffset);
    }

    int getPinWidth() {
        return pinWidth;
    }

    int getPinHeightOffset() {
        return pinHeightOffset;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

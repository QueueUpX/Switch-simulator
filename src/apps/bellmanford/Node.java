package apps.bellmanford;

import java.awt.*;

public class Node {
    private int myId;
    private int x, y;
    private final static int width = 50, height = 50;
    private boolean selected = false;

    public Node(int x, int y, int id) {
        myId = id;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g) {
        if(selected) {
            g.setColor(Color.green);
            g.setStroke(new BasicStroke(2));
        }
        else {
            g.setColor(Color.black);
            g.setStroke(new BasicStroke(1));
        }
        g.drawOval(x, y, width, height);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString(String.valueOf(myId), x+width/2-g.getFontMetrics().stringWidth(String.valueOf(myId))/2,
                y+height/2+g.getFontMetrics().getHeight()/4);
    }

    public boolean checkIfSelected(Point p) {
        int radius = width/2;
        int center_x = x + width/2;
        int center_y = y + height/2;
        return (p.x - center_x) * (p.x - center_x) + (p.y - center_y) * (p.y - center_y) < (radius * radius);
    }

    public int getMyId() {
        return myId;
    }

    public void setMyId(int myId) {
        this.myId = myId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}

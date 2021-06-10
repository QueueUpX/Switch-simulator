package apps.bellmanford;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class DrawFile extends JPanel implements KeyListener, MouseListener {
    private LinkedList<Node> nodes;

    private int node;
    private int delete;
    private int line;

    public DrawFile() {
        super();

        init();
        setup();
        addComponents();

        this.setFocusable(true);
        this.requestFocus();
    }

    private void init() {
        nodes = new LinkedList<>();
        node = -1;
        delete = -1;
        line = -1;
    }

    private void setup() {
    }

    private void addComponents() {
        this.addKeyListener(this);
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawNodes(g2d);
    }

    private void drawNodes(Graphics2D g) {
        for(Node value : nodes) {
            value.draw(g);
        }
        System.out.println(nodes.size() + " nodes drew");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_N) {
            node = node * -1;
            if(delete == 1) {
                delete = -1;
            }
            if(line == 1) {
                line = -1;
            }
            System.out.println("Node mode: " + node);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D) {
            delete = delete * -1;
            if(node == 1) {
                node = -1;
            }
            if(line == 1) {
                line = -1;
            }
            System.out.println("Delete mode: " + delete);
        }
        else if(e.getKeyCode() == KeyEvent.VK_L) {
            line = line * -1;
            if(node == 1) {
                node = -1;
            }
            if(delete == 1) {
                delete = -1;
            }
            System.out.println("Line mode: " + line);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(node == 1) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                nodes.add(new Node(e.getX() - Node.getWidth() / 2, e.getY() - Node.getHeight() / 2, nodes.size() + 1));
                this.repaint();
            }
        }
        else if(delete == 1) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                for (int i = nodes.size() - 1; i >= 0; i--) {
                    if (nodes.get(i).checkIfSelected(new Point(e.getX(), e.getY()))) {
                        for (int j = i + 1; j < nodes.size(); j++) {
                            nodes.get(j).setMyId(nodes.get(j).getMyId() - 1);
                        }
                        nodes.remove(i);
                        break;
                    }
                }
                this.repaint();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

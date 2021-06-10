package apps.delta;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DeltaDesign extends JPanel {
    private final String TITLE = "<html><h1>Delta Switch Design</h1></html>";
    private JLabel title;
    private JButton back;

    public DeltaDesign() {
        super();

        this.setPreferredSize(new Dimension(1020, 600));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        init();
        setup();
        addComponents();
    }

    private void init() {
        title = new JLabel(TITLE);
        back = new JButton("<html><h3>Back</h3></html>");
    }

    private void setup() {
        back.setMaximumSize(new Dimension(100, 30));
        back.setFocusable(false);
        back.addActionListener(e -> {
            this.getParent().add(new MyPanel());
            this.getParent().revalidate();
            this.getParent().repaint();
            this.getParent().remove(this);
        });
    }

    private void addComponents() {
        Box top = Box.createHorizontalBox();
        top.add(Box.createRigidArea(new Dimension(10, 0)));

    }
}

class Input extends JPanel {
    private String[] DATA = {"<html><h2>Number of entries:</h2></html>",
            "<html><h2>Number of outputs:</h2></html>"};
    private ArrayList<JLabel> data;
    private ArrayList<JTextField> inputData;

    Input() {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        init();
        setup();
        addComponents();
    }

    private void init() {
        data = new ArrayList<>(DATA.length);
        inputData = new ArrayList<>(DATA.length);
    }

    private void setup() {
        for(int i=0; i<DATA.length; i++) {
            data.add(new JLabel(DATA[i]));
            inputData.add(new JTextField(10));
            inputData.get(i).setMaximumSize(new Dimension(200, 30));
            inputData.get(i).setFont(new Font("Arial", Font.BOLD, 14));
        }
    }

    private void addComponents() {
        for(int i=0; i<data.size(); i++) {
            Box layer = Box.createHorizontalBox();
            layer.add(data.get(i));
            layer.add(inputData.get(i));
            this.add(layer);
        }
    }
}
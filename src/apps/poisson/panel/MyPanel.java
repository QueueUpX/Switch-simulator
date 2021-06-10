package apps.poisson.panel;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    private JLabel result, S_, Nt_, S, p, Nt, s2, S1, p1, Nt1, s21;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JButton add, diff;
    private JTextField tf1, tf2;
    private JRadioButton b1, b2, b3, b4, b1_1, b2_2, b3_3, b4_4;
    private ButtonGroup g1, g2;
    private JPanel graph;

    public MyPanel() {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        init();
        setup();
        addComponents();
    }

    private void init() {
        result = new JLabel("<html><h2>Results:</h2></html>");
        S_ = new JLabel("<html><h2>S:</h2></html>");
        Nt_ = new JLabel("<html><h2>N(t):</h2></html>");
        S = new JLabel("<html><h2>S</h2></html>");
        p = new JLabel("<html><h2>p</h2></html>");
        Nt = new JLabel("<html><h2>N(t)/s2</h2></html>");
        s2 = new JLabel("<html><h2>s2</h2></html>");
        S1 = new JLabel("<html><h2>S</h2></html>");
        p1 = new JLabel("<html><h2>p</h2></html>");
        Nt1 = new JLabel("<html><h2>N(t)/s2</h2></html>");
        s21 = new JLabel("<html><h2>s2</h2></html>");
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        add = new JButton("<html><h3>Calculate</h3></html>");
        diff = new JButton("<html><h3>Difference</h3></html>");
        tf1 = new JTextField();
        tf2 = new JTextField();
        b1 = new JRadioButton();
        b2 = new JRadioButton();
        b3 = new JRadioButton();
        b4 = new JRadioButton();
        b1_1 = new JRadioButton();
        b2_2 = new JRadioButton();
        b3_3 = new JRadioButton();
        b4_4 = new JRadioButton();
        g1 = new ButtonGroup();
        g2 = new ButtonGroup();
        graph = new JPanel();
    }

    private void setup() {
        textArea.setColumns(20);
        textArea.setRows(8);
        textArea.setEditable(false);
        g1.add(b1);
        g1.add(b2);
        g1.add(b3);
        //g1.add(b4);
        g2.add(b1_1);
        g2.add(b2_2);
        g2.add(b3_3);
        //g2.add(b4_4);
        b1.setSelected(true);
        b2_2.setSelected(true);
        add.setMaximumSize(new Dimension(200, 20));
        diff.setMaximumSize(new Dimension(200, 78));
        graph.setPreferredSize(new Dimension(800, 500));
        graph.setBackground(Color.white);
        tf1.setMaximumSize(new Dimension(200, 60));
        tf1.setPreferredSize(new Dimension(100, 40));
        tf1.setMinimumSize(new Dimension(80, 30));
        tf1.setFont(new Font("Arial", Font.BOLD, 18));
        tf2.setMaximumSize(new Dimension(200, 60));
        tf2.setPreferredSize(new Dimension(100, 40));
        tf2.setMinimumSize(new Dimension(80, 30));
        tf2.setFont(new Font("Arial", Font.BOLD, 18));
    }

    private void addComponents() {
        Box zone = Box.createVerticalBox();
        Box zone1 = Box.createHorizontalBox();
        Box zone1_1 = Box.createVerticalBox();
        Box zone1_2 = Box.createVerticalBox();
        zone1_1.add(result);
        zone1_1.add(scrollPane);
        zone1_2.add(add);
        zone1_2.add(diff);
        zone1.add(zone1_1);
        zone1.add(zone1_2);
        zone.add(Box.createRigidArea(new Dimension(0, 10)));
        zone.add(zone1);
        zone.add(Box.createRigidArea(new Dimension(0, 10)));
        Box zone2 = Box.createHorizontalBox();
        Box zone2_1 = Box.createVerticalBox();
        Box zone2_1_1 = Box.createHorizontalBox();
        Box zone2_1_2 = Box.createVerticalBox();
        Box zone2_1_2_1 = Box.createHorizontalBox();
        Box zone2_1_2_2 = Box.createHorizontalBox();
        Box zone2_1_2_3 = Box.createHorizontalBox();
        Box zone2_1_2_4 = Box.createHorizontalBox();
        Box zone2_1_3 = Box.createHorizontalBox();
        Box zone2_1_4 = Box.createVerticalBox();
        Box zone2_1_4_1 = Box.createHorizontalBox();
        Box zone2_1_4_2 = Box.createHorizontalBox();
        Box zone2_1_4_3 = Box.createHorizontalBox();
        Box zone2_1_4_4 = Box.createHorizontalBox();
        Box spacing1 = Box.createVerticalBox();
        zone2_1_1.add(S_);
        zone2_1_1.add(tf1);
        //spacing1.add(Box.createRigidArea(new Dimension(0, 20)));
        spacing1.add(zone2_1_1);
        //spacing1.add(Box.createRigidArea(new Dimension(0, 20)));
        zone2_1_2_1.add(b1);
        zone2_1_2_1.add(S);
        zone2_1_2_2.add(b2);
        zone2_1_2_2.add(p);
        zone2_1_2_3.add(b3);
        zone2_1_2_3.add(Nt);
        //zone2_1_2_4.add(Box.createRigidArea(new Dimension(0, 20)));
        //zone2_1_2_4.add(b4);
        //zone2_1_2_4.add(s2);
        zone2_1_2.add(zone2_1_2_1);
        zone2_1_2.add(zone2_1_2_2);
        zone2_1_2.add(zone2_1_2_3);
        zone2_1_2.add(zone2_1_2_4);
        zone2_1.add(spacing1);
        zone2_1.add(zone2_1_2);
        zone2_1.add(Box.createRigidArea(new Dimension(0, 20)));
        Box spacing2 = Box.createVerticalBox();
        zone2_1_3.add(Nt_);
        zone2_1_3.add(tf2);
        spacing2.add(zone2_1_3);
        zone2_1_4_1.add(b1_1);
        zone2_1_4_1.add(S1);
        zone2_1_4_2.add(b2_2);
        zone2_1_4_2.add(p1);
        zone2_1_4_3.add(b3_3);
        zone2_1_4_3.add(Nt1);
        //zone2_1_4_4.add(Box.createRigidArea(new Dimension(0, 20)));
        //zone2_1_4_4.add(b4_4);
        //zone2_1_4_4.add(s21);
        zone2_1_4.add(zone2_1_4_1);
        zone2_1_4.add(zone2_1_4_2);
        zone2_1_4.add(zone2_1_4_3);
        zone2_1_4.add(zone2_1_4_4);
        //spacing1.add(Box.createRigidArea(new Dimension(0, 20)));
        zone2_1.add(spacing2);
        //spacing1.add(Box.createRigidArea(new Dimension(0, 20)));
        zone2_1.add(zone2_1_4);
        zone2.add(zone2_1);
        zone2.add(Box.createRigidArea(new Dimension(30, 0)));
        zone2.add(graph);
        zone.add(zone2);
        zone.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(Box.createRigidArea(new Dimension(20, 0)));
        this.add(zone);
        this.add(Box.createRigidArea(new Dimension(20, 0)));
    }
}

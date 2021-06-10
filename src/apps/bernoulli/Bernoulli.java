package apps.bernoulli;

import apps.bernoulli.panel.MyPanel;

import javax.swing.*;

public class Bernoulli extends JFrame {
    private MyPanel panel;

    public Bernoulli() {
        super("SCR_Apps_Bernoulli_1.0");

        init();
        setup();
        addComponents();

        this.setSize(1024, 768);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void init() {
        panel = new MyPanel();
    }

    private void setup() {

    }

    private void addComponents() {
        this.add(panel);
    }
}

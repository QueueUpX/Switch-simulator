package apps.poisson;

import apps.poisson.panel.MyPanel;

import javax.swing.*;

public class Poisson extends JFrame {
    private MyPanel panel;

    public Poisson() {
        super("SCR_Apps_Poisson_1.0");

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

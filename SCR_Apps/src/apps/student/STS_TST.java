package apps.student;

import javax.swing.*;
import java.awt.*;

public class STS_TST extends JFrame {
    public static final Dimension size = new Dimension(800, 640);

    public STS_TST() {
        super("SCR_Apps_STS/TST_1.0");

        init();
        setup();
        addComponents();

        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void init() {
    }

    private void setup() {

    }

    private void addComponents() {
        this.add(new SwitchDesign());
    }
}

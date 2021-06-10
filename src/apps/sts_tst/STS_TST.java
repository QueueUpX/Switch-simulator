package apps.sts_tst;

import javax.swing.*;
import java.awt.*;

public class STS_TST extends JFrame {
    private STS_TST_Panel panel;

    public STS_TST() {
        super("SCR_Apps_STS/TST_1.0");

        init();
        setup();
        addComponents();

        this.setSize(new Dimension(1024, 768));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void init() {
        panel = new STS_TST_Panel();
    }

    private void setup() {

    }

    private void addComponents() {
        this.add(panel);
    }
}


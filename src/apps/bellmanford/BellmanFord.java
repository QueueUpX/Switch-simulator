package apps.bellmanford;

import javax.swing.*;
import java.awt.*;

public class BellmanFord extends JFrame {
    DrawFile drawFile;

    public BellmanFord() {
        super("SCR_Apps_BellmanFord_1.0");

        init();
        setup();
        addComponents();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.transferFocus();
    }

    private void init() {
        drawFile = new DrawFile();
    }

    private void setup() {
        drawFile.setPreferredSize(new Dimension(800, 600));
    }

    private void addComponents() {
        this.add(drawFile);
    }

}

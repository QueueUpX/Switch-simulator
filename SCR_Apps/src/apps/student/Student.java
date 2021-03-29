package apps.student;

import javax.swing.*;
import java.awt.*;

public class Student extends JFrame {
    public static final Dimension size = new Dimension(800, 600);

    public Student() {
        super("SCR_Apps_Student_1.0");

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

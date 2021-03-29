package apps.chooser;

import apps.benes.Benes;
import apps.clos.Clos;
import apps.delta.Delta;
import apps.omega.Omega;
import apps.student.Student;

import javax.swing.*;
import java.awt.*;

public class ChooserWindow extends JFrame {
    private static class ChoosePanel extends JPanel {
        Font buttonFont;
        JLabel title;
        JButton clos;
        JButton delta;
        JButton omega;
        JButton benes;
        JButton student;

        ChoosePanel() {
            super();

            this.setBackground(Color.white);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            init();
            setup();
            addComponents();
        }

        void init() {
            buttonFont = new Font("Arial", Font.BOLD, 20);
            title = new JLabel("<html><h1>Select an application</h1></html>", JLabel.CENTER);
            clos = new JButton("CLOS");
            delta = new JButton("DELTA");
            omega = new JButton("OMEGA");
            benes = new JButton("BENES");
            student = new JButton("STUDENT");
        }

        void setup() {
            title.setAlignmentX(0.5f);

            clos.setFont(buttonFont);
            clos.setAlignmentX(0.5f);
            clos.addActionListener(e -> new Clos());

            delta.setFont(buttonFont);
            delta.setAlignmentX(0.5f);
            delta.addActionListener(e -> new Delta());

            omega.setFont(buttonFont);
            omega.setAlignmentX(0.5f);
            omega.addActionListener(e -> new Omega());

            benes.setFont(buttonFont);
            benes.setAlignmentX(0.5f);
            benes.addActionListener(e -> new Benes());

            student.setFont(buttonFont);
            student.setAlignmentX(0.5f);
            student.addActionListener(e -> new Student());
        }

        void addComponents() {
            this.add(title);
            this.add(Box.createRigidArea(new Dimension(300,50)));
            this.add(clos);
            this.add(Box.createRigidArea(new Dimension(0,20)));
            this.add(delta);
            this.add(Box.createRigidArea(new Dimension(0,20)));
            this.add(omega);
            this.add(Box.createRigidArea(new Dimension(0,20)));
            this.add(benes);
            this.add(Box.createRigidArea(new Dimension(0,20)));
            this.add(student);
            this.add(Box.createRigidArea(new Dimension(0,50)));
        }
    }

    public ChooserWindow() {
        super("SCR_Apps_1.0");

        this.add(new ChoosePanel());
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

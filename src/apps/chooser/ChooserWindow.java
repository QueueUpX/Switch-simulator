package apps.chooser;

import apps.bellmanford.BellmanFord;
import apps.benes.Benes;
import apps.bernoulli.Bernoulli;
import apps.clos.Clos;
import apps.delta.Delta;
import apps.erlang.Erlang;
import apps.omega.Omega;
import apps.poisson.Poisson;
import apps.sts_tst.STS_TST;

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
        JButton sts_tst;
        JButton bernoulli;
        JButton poisson;
        JButton erlang;
        JButton bellmanFord;

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
            sts_tst = new JButton("STS/TST");
            bernoulli = new JButton("Bernoulli");
            poisson = new JButton("Poisson");
            erlang = new JButton("Erlang");
            bellmanFord = new JButton("Bellman Ford");
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

            sts_tst.setFont(buttonFont);
            sts_tst.setAlignmentX(0.5f);
            sts_tst.addActionListener(e -> new STS_TST());

            bernoulli.setFont(buttonFont);
            bernoulli.setAlignmentX(0.5f);
            bernoulli.addActionListener(e -> new Bernoulli());

            poisson.setFont(buttonFont);
            poisson.setAlignmentX(0.5f);
            poisson.addActionListener(e -> new Poisson());

            erlang.setFont(buttonFont);
            erlang.setAlignmentX(0.5f);
            erlang.addActionListener(e -> new Erlang());

            bellmanFord.setFont(buttonFont);
            bellmanFord.setAlignmentX(0.5f);
            bellmanFord.addActionListener(e -> new BellmanFord());
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
            this.add(sts_tst);
            this.add(Box.createRigidArea(new Dimension(0,20)));
            this.add(bernoulli);
            this.add(Box.createRigidArea(new Dimension(0,20)));
            this.add(poisson);
            this.add(Box.createRigidArea(new Dimension(0,20)));
            this.add(erlang);
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

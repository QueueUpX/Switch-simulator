package apps.student;

import apps.student.sts.STS;
import apps.student.tst.TST;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwitchDesign extends JPanel {
    private final String TITLE = "<html><h1>Switch design</h1></html>";
    private final String STS = "<html><h2>STS switch design</h2></html>";
    private final String TST = "<html><h2>TST switch design</h2></html>";
    private TitledBorder titledBorder;
    private JButton sts, tst;

    public SwitchDesign() {
        super();

        init();
        setup();
        addComponents();

        this.setPreferredSize(STS_TST.size);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
    }

    private void init() {
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TITLE,
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        sts = new JButton(STS);
        tst = new JButton(TST);
    }

    private void setup() {
        this.setBorder(titledBorder);
        sts.setBorder(BorderFactory.createEmptyBorder());
        sts.setPreferredSize(new Dimension(265, 50));
        sts.setContentAreaFilled(false);
        sts.setFocusable(false);
        sts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                sts.setForeground(Color.blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                sts.setForeground(Color.black);
            }
        });
        sts.addActionListener(e -> {
            this.getParent().add(new STS());
            this.getParent().revalidate();
            this.getParent().repaint();
            this.getParent().remove(this);
        });
        tst.setPreferredSize(new Dimension(265, 50));
        tst.setBorder(BorderFactory.createEmptyBorder());
        tst.setContentAreaFilled(false);
        tst.setFocusable(false);
        tst.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                tst.setForeground(Color.blue);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                tst.setForeground(Color.black);
            }
        });
        tst.addActionListener(e -> {
            this.getParent().add(new TST());
            this.getParent().revalidate();
            this.getParent().repaint();
            this.getParent().remove(this);
        });
    }

    private void addComponents() {
        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(300, 400));
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 0 ,20));
        container.add(sts);
        container.add(tst);
        this.add(container);
    }
}

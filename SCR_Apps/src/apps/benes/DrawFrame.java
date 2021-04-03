package apps.benes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawFrame extends JFrame {
    private final int width, height;
    private final int clientIn;
    private final int clientOut;
    private JPanel panel, varPanel;
    private JLabel in;
    private JLabel out;
    private JLabel[] spacing;
    private JTextField textIn, textOut;
    private JButton add;

    public DrawFrame(int width, int height, int clientIn, int clientOut) {
        super("SCR_Apps_Benes_1.0");

        this.width = width;
        this.height = height;
        this.clientIn = clientIn;
        this.clientOut = clientOut;

        init();
        setup();
        addComponents();

        this.setSize(800, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    private void init() {
        panel = new JPanel();
        varPanel = new JPanel();
        in = new JLabel("<html><h2>Input user:</h2></html>");
        out = new JLabel("<html><h2>Output user:</h2></html>");
        spacing = new JLabel[2];
        for(int i=0; i<spacing.length; i++) {
            spacing[i] = new JLabel("               ");
        }
        textIn = new JTextField(4);
        textOut = new JTextField(4);
        add = new JButton("<html><h3>Add</h3></html>");
    }

    private void setup() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        varPanel.setMaximumSize(new Dimension(width, height/20));
        varPanel.setBackground(Color.lightGray);
        textIn.setFont(new Font("Arial", Font.BOLD, 15));
        textIn.setHorizontalAlignment(JTextField.CENTER);
        textOut.setFont(new Font("Arial", Font.BOLD, 15));
        textOut.setHorizontalAlignment(JTextField.CENTER);
        add.setPreferredSize(new Dimension(70,35));
    }

    private void addComponents() {
        varPanel.add(in);
        varPanel.add(textIn);
        varPanel.add(spacing[0]);
        varPanel.add(out);
        varPanel.add(textOut);
        varPanel.add(spacing[1]);
        varPanel.add(add);
        Box hBox = Box.createHorizontalBox();
        hBox.setMaximumSize(new Dimension(width, height/20));
        hBox.add(Box.createRigidArea(new Dimension(450, 0)));
        hBox.add(varPanel);
        hBox.add(Box.createRigidArea(new Dimension(450, 0)));
        panel.add(hBox);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(new DrawPanel((int)(width/1.27), height-height/3, clientIn, clientOut, textIn, textOut, add));
        this.add(panel);
    }
}
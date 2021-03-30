package apps.benes;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Benes extends JFrame {
    JPanel panel;
    JLabel label1;
    JTextField textField1;
    JButton build;

    public Benes() {
        super("SCR_Apps_Benes_1.0");

        init();
        setup();
        addComponents();

        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void init() {
        panel = new JPanel();
        label1 = new JLabel("<html><h1>Enter the number of users:</h1></html>");
        textField1 = new JTextField(4);
        build = new JButton("<html><h2>Build Benes</h2></html");
    }

    private void setup() {
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(450,130));
        ((AbstractDocument)textField1.getDocument()).setDocumentFilter(new DocumentFilter(){
            final Pattern regEx = Pattern.compile("[0-9]");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches() || textField1.getText().length() > 1){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
        textField1.setPreferredSize(new Dimension(10,30));
        textField1.setHorizontalAlignment(JTextField.CENTER);
        textField1.setAlignmentY(1f);
        textField1.setFont(new Font("Arial", Font.BOLD, 18));
        build.setPreferredSize(new Dimension(145,30));
        build.addActionListener(e -> {
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();
            if(textField1.getText().equals("")) {

            }
            else if(Integer.parseInt(textField1.getText()) < 4) {
                JOptionPane.showMessageDialog(this,
                        "<html><h2 style='color:red'>The number of users must be &#8805 4</h2></html>");
            }
            else if(Integer.parseInt(textField1.getText()) % 2 != 0){
                JOptionPane.showMessageDialog(this,
                        "<html><h2 style='color:red'>The number of users must be divisible by 2</h2></html>");
            }
            else {
                new DrawFrame(width, height, Integer.parseInt(textField1.getText()) / 2, Integer.parseInt(textField1.getText()) / 2);
            }
        });
    }

    private void addComponents() {
        panel.add(label1);
        panel.add(textField1);
        panel.add(build);
        this.add(panel);
    }
}
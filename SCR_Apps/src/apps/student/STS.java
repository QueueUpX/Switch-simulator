package apps.student;

import tools.Tools;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class STS extends JPanel {
    private final String TITLE = "<html><h1>STS switch design</h1></html>";
    private TitledBorder titledBorder;
    private JButton back, next;
    private Input input;
    private Output output;

    public STS() {
        super();

        init();
        setup();
        addComponents();

        this.setPreferredSize(STS_TST.size);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 330, 10));
    }

    private void init() {
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TITLE,
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        back = new JButton("<html>Back</html>");
        next = new JButton("<html>Next</html>");
        input = new Input();
        output = new Output();
    }

    private void setup() {
        this.setBorder(titledBorder);
        back.addActionListener(e -> {
            if(this.getComponent(0) instanceof Input) {
                if(JOptionPane.showConfirmDialog(this, "<html><h2>Return to switch design menu?</h2></html>") == 0) {
                    this.getParent().add(new SwitchDesign());
                    this.getParent().revalidate();
                    this.getParent().repaint();
                    this.getParent().remove(this);
                    return;
                }
            }
            if(this.getComponent(0) instanceof Output) {
                this.add(input, 0);
                this.revalidate();
                this.repaint();
                this.remove(output);
            }
        });
        next.addActionListener(e -> {
            if(this.getComponent(0) instanceof Input) {
                if(filterData(((Input) this.getComponent(0)).getDataInput())) {
                    switch(((Input) this.getComponent(0)).getType()) {
                        case 0:
                            SquareMatrix(((Input) this.getComponent(0)).getDataInput(), output.getDataOutput());
                            break;
                        case 1:
                        case 2:
                        case 3:
                            break;
                        default:
                            return;
                    }

                    this.add(output, 0);
                    this.revalidate();
                    this.repaint();
                    this.remove(input);
                    return;
                }
            }
            if(this.getComponent(0) instanceof Output) {
                new GridGraph();
            }
        });
    }

    private void addComponents() {
        this.add(input);
        this.add(back);
        this.add(next);
    }

    private Boolean filterData(ArrayList<JTextField> inputs) { //[0-9]*\.?[0-9]* regex for double values
        for (JTextField jTextField : inputs) {
            if (jTextField.getText().equals("") && jTextField.isEnabled()) {
                JOptionPane.showMessageDialog(this,
                        "<html><h2 style='color:red'>All input fields must be filled</h2></html>");
                return false;
            }
        }
        try {
            if (Integer.parseInt(inputs.get(0).getText()) <= 1 || (Integer.parseInt(inputs.get(0).getText()) &
                    (Integer.parseInt(inputs.get(0).getText()) - 1)) != 0) {
                JOptionPane.showMessageDialog(this,
                        "<html><h2 style='color:red'>'Number of users' field is invalid</h2></html>");
                return false;
            }
            if (Integer.parseInt(inputs.get(2).getText()) == 0) {
                JOptionPane.showMessageDialog(this,
                        "<html><h2 style='color:red'>'Number of TDM channels' field is invalid</h2></html>");
                return false;
            }
            if (Double.parseDouble(inputs.get(3).getText()) >= 1 || Double.parseDouble(inputs.get(3).getText()) == 0) {
                JOptionPane.showMessageDialog(this,
                        "<html><h2 style='color:red'>'The probability that a line is active p[0, 1]' field is invalid</h2></html>");
                return false;
            }
            if (Double.parseDouble(inputs.get(4).getText()) >= 1 || Double.parseDouble(inputs.get(4).getText()) == 0) {
                JOptionPane.showMessageDialog(this,
                        "<html><h2 style='color:red'>'Probability of blocking(max) B[0, 1]' field is invalid</h2></html>");
                return false;
            }
        } catch(NumberFormatException ignore) {
            JOptionPane.showMessageDialog(this,
                    "<html><h2 style='color:red'>Please check again the input fields</h2></html>");
            return false;
        }
        return true;
    }

    private void SquareMatrix(ArrayList<JTextField> inputs, ArrayList<JTextField> outputs) {
        int N, complexMax, c, n, k, Nx;
        double p, B, calculatedB, Nb, calculatedComplex;
        N = Integer.parseInt(inputs.get(0).getText());
        complexMax = Integer.parseInt(inputs.get(1).getText());
        try {
            c = Integer.parseInt(inputs.get(2).getText());
            n = N / c;
        } catch (NumberFormatException ignore) {
            n = Integer.parseInt(inputs.get(5).getText());
            c = N / n;
        }
        B = Double.parseDouble(inputs.get(4).getText());
        p = Double.parseDouble(inputs.get(3).getText());
        k = 1;
        while (true) {
            calculatedB = Tools.pow(1 - Tools.pow(1 - (p * n) / k, 2), k);
            System.out.println("Compare: " + Tools.round(B, 1) + " To: " + Tools.round(calculatedB, 1));
            if (Tools.round(calculatedB, 1) <= Tools.round(B, 1) && Tools.round(calculatedB, 1) >= 0) {
                break;
            }
            k++;
        }
        Nx = 2 * k * n;
        Nb = (2 * k * c * (Math.log(n) / Math.log(2))) + (8 * k * c) + (k * c * (Math.log(c) / Math.log(2)));
        calculatedComplex = Nx + Nb/100;
        outputs.get(0).setText(String.valueOf(n));
        outputs.get(1).setText(String.valueOf(c));
        outputs.get(2).setText(String.valueOf(complexMax));
        outputs.get(3).setText(String.valueOf(Tools.round(calculatedComplex, 5)));
        outputs.get(4).setText(String.valueOf(k));
        outputs.get(5).setText(String.valueOf(Tools.round(calculatedB, 5)));
    }
}

class Input extends JPanel {
    private final String TITLE = "<html><h2>Input data</h2></html>";
    private final String[] DATA = {"<html><h2>Number of users:</h2></html>",
            "<html><h2>Complexity of implementation(max):</h2></html>",
            "<html><h2>Number of TDM channels:</h2></html>",
            "<html><h2>The probability that a line is active p[0, 1]:</h2></html>",
            "<html><h2>Probability of blocking(max) B[0, 1]:</h2></html>",
            "<html><h2>Number of entries N:</h2></html>",
            "<html><h2>Square Matrix</h2></html>",
            "<html><h2>Clos</h2></html>",
            "<html><h2>Delta</h2></html>",
            "<html><h2>Benes</h2></html>"};
    private TitledBorder titledBorder;
    private ArrayList<JLabel> data;
    private ArrayList<JTextField> dataInput;
    private ArrayList<JRadioButton> type;

    Input() {
        super();

        init();
        setup();
        addComponents();

        this.setPreferredSize(new Dimension(650, 525));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void init() {
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TITLE,
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        data = new ArrayList<>(6);
        dataInput = new ArrayList<>(6);
        type = new ArrayList<>(4);
    }

    private void setup() {
        this.setBorder(titledBorder);
        for(int i=0; i<6; i++) {
            data.add(new JLabel(DATA[i]));
            dataInput.add(new JTextField(8));
            dataInput.get(i).setMaximumSize(new Dimension(50,30));
            dataInput.get(i).setFont(new Font("Arial", Font.BOLD, 15));
        }
        for(int i=0; i<3; i++) {
            ((AbstractDocument) dataInput.get(i).getDocument()).setDocumentFilter(new DocumentFilter() {
                final Pattern regEx = Pattern.compile("[0-9]");

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    Matcher matcher = regEx.matcher(text);
                    if (!matcher.matches()) {
                        return;
                    }
                    super.replace(fb, offset, length, text, attrs);
                }
            });
        }
        ((AbstractDocument) dataInput.get(3).getDocument()).setDocumentFilter(new DocumentFilter() {
            final Pattern regEx = Pattern.compile("[0-9]*\\.?[0-9]*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
        ((AbstractDocument) dataInput.get(4).getDocument()).setDocumentFilter(new DocumentFilter() {
            final Pattern regEx = Pattern.compile("[0-9]*\\.?[0-9]*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
        ((AbstractDocument) dataInput.get(5).getDocument()).setDocumentFilter(new DocumentFilter() {
            final Pattern regEx = Pattern.compile("[0-9]");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
        dataInput.get(2).addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                dataInput.get(5).setEnabled(dataInput.get(2).getText().equals(""));
            }
        });
        dataInput.get(5).addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                dataInput.get(2).setEnabled(dataInput.get(5).getText().equals(""));
            }
        });
        for(int i=0; i<4; i++) {
            type.add(new JRadioButton(DATA[6+i]));
            type.get(i).setFocusable(false);
            type.get(i).setMaximumSize(new Dimension(180, 35));
        }
    }

    private void addComponents() {
        for(int i=0; i<data.size(); i++) {
            Box hBox = Box.createHorizontalBox();
            hBox.add(Box.createRigidArea(new Dimension(50, 0)));
            hBox.add(data.get(i));
            hBox.add(dataInput.get(i));
            hBox.add(Box.createRigidArea(new Dimension(50, 0)));
            this.add(hBox);
        }
        ButtonGroup option = new ButtonGroup();
        for(int i=0; i<4; i++) {
            option.add(type.get(i));
            Box hBox = Box.createHorizontalBox();
            hBox.add(Box.createRigidArea(new Dimension(50, 0)));
            hBox.add(type.get(i));
            hBox.add(Box.createHorizontalGlue());
            this.add(hBox);
        }
        type.get(0).setSelected(true);
    }

    public ArrayList<JTextField> getDataInput() {
        return dataInput;
    }

    public int getType() {
        for(int i=0; i<type.size(); i++) {
            if(type.get(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }
}

class Output extends JPanel {
    private final String TITLE = "<html><h2>Output data</h2></html>";
    private final String[] DATA = {"<html><h2>Number of entries N:</h2></html>",
            "<html><h2>Number of TDM channels:</h2></html>",
            "<html><h2>Complexity of implementation(max):</h2></html>",
            "<html><h2>Calculated implementation complexity:</h2></html>",
            "<html><h2>Number of central matrices k:</h2></html>",
            "<html><h2>Calculated blocking probability:</h2></html>",
            "<html><h2>Complexity = f(p)</h2></html>",
            "<html><h2>Complexity = f(B)</h2></html>",
            "<html><h2>Probability of blocking = f(p)</h2></html>",
            "<html><h2>Probability of blocking = f(k)</h2></html>"};
    private TitledBorder titledBorder;
    private ArrayList<JLabel> data;
    private ArrayList<JTextField> dataOutput;
    private ArrayList<JRadioButton> type;

    Output() {
        super();

        init();
        setup();
        addComponents();

        this.setPreferredSize(new Dimension(650, 525));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void init() {
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TITLE,
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        data = new ArrayList<>(6);
        dataOutput = new ArrayList<>(6);
        type = new ArrayList<>(4);
    }

    private void setup() {
        this.setBorder(titledBorder);
        for(int i=0; i<6; i++) {
            data.add(new JLabel(DATA[i]));
            dataOutput.add(new JTextField(8));
            dataOutput.get(i).setMaximumSize(new Dimension(50,30));
            dataOutput.get(i).setFont(new Font("Arial", Font.BOLD, 15));
            dataOutput.get(i).setEditable(false);
        }
        for(int i=0; i<4; i++) {
            type.add(new JRadioButton(DATA[6+i]));
            type.get(i).setFocusable(false);
            type.get(i).setMaximumSize(new Dimension(180, 35));
        }
    }

    private void addComponents() {
        for(int i=0; i<data.size(); i++) {
            Box hBox = Box.createHorizontalBox();
            hBox.add(Box.createRigidArea(new Dimension(50, 0)));
            hBox.add(data.get(i));
            hBox.add(dataOutput.get(i));
            hBox.add(Box.createRigidArea(new Dimension(50, 0)));
            this.add(hBox);
        }
        ButtonGroup option = new ButtonGroup();
        for(int i=0; i<4; i++) {
            option.add(type.get(i));
            Box hBox = Box.createHorizontalBox();
            hBox.add(Box.createRigidArea(new Dimension(50, 0)));
            hBox.add(type.get(i));
            hBox.add(Box.createHorizontalGlue());
            this.add(hBox);
        }
        type.get(0).setSelected(true);
    }

    public ArrayList<JTextField> getDataOutput() {
        return dataOutput;
    }
}
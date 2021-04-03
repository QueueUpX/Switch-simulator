package apps.clos;

import tools.Tools;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClosDesign extends JPanel {
    private final String TITLE = "<html><h1>Clos Switch Design</h1></html>";
    private JLabel title;
    private JButton back;
    private Input input;
    private Output output;
    private Graph graph;

    public ClosDesign() {
        super();

        init();
        setup();
        addComponents();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void init() {
        back = new JButton("<html><h3>Back</h3></html>");
        title = new JLabel(TITLE);
        input = new Input();
        output = new Output();
        graph = new Graph();
    }

    private void setup() {
        back.setMaximumSize(new Dimension(100, 30));
        back.setFocusable(false);
        back.addActionListener(e -> {
            this.getParent().add(new MyPanel());
            this.getParent().revalidate();
            this.getParent().repaint();
            this.getParent().remove(this);
        });
    }

    private void addComponents() {
        Box top = Box.createHorizontalBox();
        top.add(Box.createRigidArea(new Dimension(10, 0)));
        top.add(back);
        top.add(Box.createRigidArea(new Dimension(490, 0)));
        top.add(title);
        top.add(Box.createRigidArea(new Dimension(500, 0)));
        this.add(top);
        Box mid = Box.createHorizontalBox();
        mid.add(Box.createRigidArea(new Dimension(10, 300)));
        mid.add(input);
        mid.add(Box.createRigidArea(new Dimension(10, 0)));
        mid.add(output);
        mid.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(mid);
        Box bot = Box.createHorizontalBox();
        bot.add(Box.createRigidArea(new Dimension(10, 0)));
        bot.add(graph);
        bot.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(bot);
    }

    public void setOutputData(ArrayList<String> outputData) {
        for(int i=0; i<outputData.size(); i++) {
            output.getOutputData().get(i).setText(outputData.get(i));
        }
    }
}

class Input extends JPanel {
    private final String TITLE = "<html><h2>Data input</h2></html>";
    private final String[] DATA = {"<html><h2>Number of entries/outputs N:</h2></html>",
            "<html><h2>The probability that a line is active p[0, 1]:</h2></html>",
            "<html><h2>Probability of blocking(max) B[0, 1]:</h2></html>"};
    private TitledBorder titledBorder;
    private ArrayList<JLabel> data;
    private ArrayList<JTextField> inputData;
    private JButton calculate;
    private ArrayList<String> outputData;

    Input() {
        super();

        init();
        setup();
        addComponents();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    private void init() {
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TITLE,
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        data = new ArrayList<>(DATA.length);
        inputData = new ArrayList<>(DATA.length);
        calculate = new JButton("<html><h3>Calculate</h3></html>");
        outputData = new ArrayList<>();
    }

    private void setup() {
        this.setBorder(titledBorder);
        for(int i=0; i<DATA.length; i++) {
            data.add(new JLabel(DATA[i]));
            inputData.add(new JTextField(15));
            inputData.get(i).setMaximumSize(new Dimension(200, 30));
            inputData.get(i).setFont(new Font("Arial", Font.BOLD, 14));
        }
        ((AbstractDocument) inputData.get(0).getDocument()).setDocumentFilter(new DocumentFilter() {
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
        ((AbstractDocument) inputData.get(1).getDocument()).setDocumentFilter(new DocumentFilter() {
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
        ((AbstractDocument) inputData.get(2).getDocument()).setDocumentFilter(new DocumentFilter() {
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
        calculate.setMaximumSize(new Dimension(100, 20));
        calculate.setFocusable(false);
        calculate.addActionListener(e -> {
            if(filterInputData()) {
                calculate();
                ((ClosDesign) this.getParent().getParent()).setOutputData(outputData);
            }
        });
    }

    private void addComponents() {
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        Box data = Box.createVerticalBox();
        Box inputs = Box.createVerticalBox();
        for(int i=0; i<this.data.size(); i++) {
            data.add(this.data.get(i));
            inputs.add(inputData.get(i));
            inputs.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        data.add(calculate);
        data.add(Box.createRigidArea(new Dimension(0, 30)));
        inputs.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(data);
        this.add(inputs);
    }

    private Boolean filterInputData() {
        try {
            if(Integer.parseInt(inputData.get(0).getText()) < 1 || (Integer.parseInt(inputData.get(0).getText()) &
                    (Integer.parseInt(inputData.get(0).getText()) - 1)) != 0) {
                JOptionPane.showMessageDialog(this,
                        "<html><h2 style='color:red'>N must be higher than 0 and be divisible by 2</h2></html>");
                return false;
            }
            for(int i=1; i<inputData.size(); i++) {
                if(Double.parseDouble(inputData.get(i).getText()) > 1) {
                    JOptionPane.showMessageDialog(this,
                            "<html><h2 style='color:red'>The values has to be between [0, 1]</h2></html>");
                    return false;
                }
            }
        } catch(NumberFormatException ignore) {
            JOptionPane.showMessageDialog(this,
                    "<html><h2 style='color:red'>Please check again the input fields</h2></html>");
            return false;
        }
        return true;
    }

    private void calculate() {
        double N = Double.parseDouble(inputData.get(0).getText());
        double p = Double.parseDouble(inputData.get(1).getText());
        double Bmax = Double.parseDouble(inputData.get(2).getText());
        double n, calculatedB, k, Nx;
        double sqrt = Math.sqrt(N / 2.0);
        double sqrt1 = Math.sqrt(N);
        if ((int) sqrt != sqrt) {
            n = sqrt1;
        } else {
            n = sqrt;
        }
        k = 1;
        while (true) {
            calculatedB = Tools.pow(1 - Tools.pow(1 - (p * n) / k, 2), (int)k);
            if (calculatedB <= Bmax && calculatedB >= 0) {
                break;
            }
            k++;
        }
        Nx = (2 * k * n) + (k * Tools.pow((N/n), 2));
        outputData.add(String.valueOf(Tools.round(Nx, 3)));
        outputData.add(String.valueOf(k));
        outputData.add(String.valueOf(Tools.round(calculatedB, 3)));
        outputData.add(String.valueOf(n));
    }
}

class Output extends JPanel {
    private final String TITLE = "<html><h2>Data output</h2></html>";
    private final String[] DATA = {"<html><h2>Calculated implementation complexity:</h2></html>",
            "<html><h2>Number of central matrices k:</h2></html>",
            "<html><h2>Calculated blocking probability:</h2></html>",
            "<html><h2>Number of subgroups n:</h2></html>"};
    private TitledBorder titledBorder;
    private ArrayList<JLabel> data;
    private ArrayList<JTextField> outputData;

    Output() {
        super();

        init();
        setup();
        addComponents();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    private void init() {
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TITLE,
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        data = new ArrayList<>(DATA.length);
        outputData = new ArrayList<>(DATA.length);
    }

    private void setup() {
        this.setBorder(titledBorder);
        for(int i=0; i<DATA.length; i++) {
            data.add(new JLabel(DATA[i]));
            outputData.add(new JTextField(15));
            outputData.get(i).setEditable(false);
            outputData.get(i).setMaximumSize(new Dimension(200, 30));
            outputData.get(i).setFont(new Font("Arial", Font.BOLD, 14));
        }
    }

    private void addComponents() {
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        Box data = Box.createVerticalBox();
        Box outputs = Box.createVerticalBox();
        for(int i=0; i<this.data.size(); i++) {
            data.add(this.data.get(i));
            outputs.add(outputData.get(i));
            outputs.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        this.add(data);
        this.add(outputs);
    }

    public ArrayList<JTextField> getOutputData() {
        return outputData;
    }
}

class Graph extends JPanel {
    private final String TITLE = "<html><h2>Graph</h2></html>";
    private final String[] DATA = {"<html><h2>Complexity = f(p)</h2></html>",
            "<html><h2>Complexity = f(B)</h2></html>",
            "<html><h2>Probability of blocking = f(p)</h2></html>",
            "<html><h2>Probability of blocking = f(k)</h2></html>"};
    private final String[] FIELDS = {"<html><h2>Start p:</h2></html>",
            "<html><h2>End p:</h2></html>",
            "<html><h2>Step p:</h2></html>",
            "<html><h2>New max B[0, 1](not required):</h2></html>",
            "<html><h2>Start B:</h2></html>",
            "<html><h2>End B:</h2></html>",
            "<html><h2>Step B:</h2></html>",
            "<html><h2>New p[0, 1](new required):</h2></html>",
            "<html><h2>Start p:</h2></html>",
            "<html><h2>End p:</h2></html>",
            "<html><h2>Step p:</h2></html>",
            "<html><h2>New k(not required):</h2></html>",
            "<html><h2>Start k:</h2></html>",
            "<html><h2>End k:</h2></html>",
            "<html><h2>Step k:</h2></html>",
            "<html><h2>New p[0, 1](not required):</h2></html>"};
    private ArrayList<JLabel> dataInput;
    private ArrayList<JTextField> inputField;
    private TitledBorder titledBorder;
    private ArrayList<JRadioButton> type;
    private ButtonGroup option;
    private JButton show;

    Graph() {
        super();

        init();
        setup();
        addComponents();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    private void init() {
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TITLE,
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        type = new ArrayList<>(4);
        option = new ButtonGroup();
        show = new JButton("<html><h3>Show</h3></html>");
        dataInput = new ArrayList<>(FIELDS.length);
        inputField = new ArrayList<>(FIELDS.length);
    }

    private void setup() {
        this.setBorder(titledBorder);
        for(int i=0; i<DATA.length; i++) {
            type.add(new JRadioButton(DATA[i]));
            option.add(type.get(i));
            type.get(i).setFocusable(false);
        }
        type.get(0).addActionListener(e -> {
            ((JComponent)((JComponent)this.getComponent(2)).getComponent(0)).removeAll();
            ((JComponent)((JComponent)this.getComponent(2)).getComponent(1)).removeAll();
            for(int j=0; j<4; j++) {
                ((JComponent)((JComponent)this.getComponent(2)).getComponent(0)).add(dataInput.get(j));
                ((JComponent)((JComponent)this.getComponent(2)).getComponent(1)).add(inputField.get(j));
                ((JComponent)((JComponent)this.getComponent(2)).getComponent(1)).add(Box.createRigidArea(new Dimension(0, 10)));
            }
            this.getComponent(2).revalidate();
            this.getComponent(2).repaint();
        });
        type.get(1).addActionListener(e -> {
            ((JComponent)((JComponent)this.getComponent(2)).getComponent(0)).removeAll();
            ((JComponent)((JComponent)this.getComponent(2)).getComponent(1)).removeAll();
            for(int j=4; j<8; j++) {
                ((JComponent)((JComponent)this.getComponent(2)).getComponent(0)).add(dataInput.get(j));
                ((JComponent)((JComponent)this.getComponent(2)).getComponent(1)).add(inputField.get(j));
                ((JComponent)((JComponent)this.getComponent(2)).getComponent(1)).add(Box.createRigidArea(new Dimension(0, 10)));
            }
            this.getComponent(2).revalidate();
            this.getComponent(2).repaint();
        });
        type.get(2).addActionListener(e -> {
            ((JComponent)((JComponent)this.getComponent(2)).getComponent(0)).removeAll();
            ((JComponent)((JComponent)this.getComponent(2)).getComponent(1)).removeAll();
            for(int j=8; j<12; j++) {
                ((JComponent)((JComponent)this.getComponent(2)).getComponent(0)).add(dataInput.get(j));
                ((JComponent)((JComponent)this.getComponent(2)).getComponent(1)).add(inputField.get(j));
                ((JComponent)((JComponent)this.getComponent(2)).getComponent(1)).add(Box.createRigidArea(new Dimension(0, 10)));
            }
            this.getComponent(2).revalidate();
            this.getComponent(2).repaint();
        });
        type.get(3).addActionListener(e -> {
            ((JComponent)((JComponent)this.getComponent(2)).getComponent(0)).removeAll();
            ((JComponent)((JComponent)this.getComponent(2)).getComponent(1)).removeAll();
            for(int j=12; j<16; j++) {
                ((JComponent)((JComponent)this.getComponent(2)).getComponent(0)).add(dataInput.get(j));
                ((JComponent)((JComponent)this.getComponent(2)).getComponent(1)).add(inputField.get(j));
                ((JComponent)((JComponent)this.getComponent(2)).getComponent(1)).add(Box.createRigidArea(new Dimension(0, 10)));
            }
            this.getComponent(2).revalidate();
            this.getComponent(2).repaint();
        });
        type.get(0).setSelected(true);
        for(int i=0; i<FIELDS.length; i++) {
            dataInput.add(new JLabel(FIELDS[i]));
            inputField.add(new JTextField(10));
            inputField.get(i).setMaximumSize(new Dimension(200, 30));
            inputField.get(i).setFont(new Font("Arial", Font.BOLD, 14));
            if(i != 14 && i != 13 && i != 12 && i != 11) {
                decimalTextField(inputField.get(i));
            }
            else {
                integerTextField(inputField.get(i));
            }
        }
        show.setMaximumSize(new Dimension(100, 30));
        show.setFocusable(false);
        show.addActionListener(e -> {
            if(filterInputData()) {

            }
        });
    }

    private void addComponents() {
        Box left = Box.createVerticalBox();
        left.add(Box.createRigidArea(new Dimension(10, 0)));
        left.add(type.get(0));
        left.add(type.get(1));
        this.add(left);
        Box mid = Box.createVerticalBox();
        mid.add(type.get(2));
        mid.add(type.get(3));
        this.add(mid);
        Box right = Box.createHorizontalBox();
        right.setName("initGraphVars");
        right.setPreferredSize(new Dimension(400, 176));
        Box text = Box.createVerticalBox();
        text.setName("JLabels");
        Box field = Box.createVerticalBox();
        field.setName("JTextFields");
        for(int i=0; i<4; i++) {
            text.add(dataInput.get(i));
            field.add(inputField.get(i));
            field.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        right.add(text);
        right.add(field);
        this.add(right);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(show);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
    }

    private void integerTextField(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
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

    private void decimalTextField(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
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
    }

    private Boolean filterInputData() {
        int type = 0;
        for(int i=0; i<this.type.size(); i++) {
            if(this.type.get(i).isSelected()) {
                type = i;
                break;
            }
        }
        try {
            for(int i=type*4; i<type+4; i++) {
                if(i == 14 || i == 13 || i == 12 || i == 11) {
                    if(i == 11) {
                        if(!inputField.get(i).getText().isEmpty()) {
                            if(Integer.parseInt(inputField.get(i).getText()) == 0) {
                                JOptionPane.showMessageDialog(this,
                                        "<html><h2 style='color:red'>This value cannot be 0</h2></html>");
                                return false;
                            }
                        }
                    }
                    else {
                        if (Integer.parseInt(inputField.get(i).getText()) == 0) {
                            JOptionPane.showMessageDialog(this,
                                    "<html><h2 style='color:red'>This value cannot be 0</h2></html>");
                            return false;
                        }
                    }
                }
                else {
                    if(i == 3 || i == 7 || i == 15) {
                        if(!inputField.get(i).getText().isEmpty()) {
                            if(Double.parseDouble(inputField.get(i).getText()) > 1) {
                                JOptionPane.showMessageDialog(this,
                                        "<html><h2 style='color:red'>This value should be between [0, 1]</h2></html>");
                                return false;
                            }
                        }
                    }
                    else {
                        if(i == 2 || i == 6 || i == 10) {
                            if (Double.parseDouble(inputField.get(i).getText()) > 1 || Double.parseDouble(inputField.get(i).getText()) == 0) {
                                JOptionPane.showMessageDialog(this,
                                        "<html><h2 style='color:red'>This value should be between (0, 1]</h2></html>");
                                return false;
                            }
                        }
                        else {
                            if (Double.parseDouble(inputField.get(i).getText()) > 1) {
                                JOptionPane.showMessageDialog(this,
                                        "<html><h2 style='color:red'>This value should be between [0, 1]</h2></html>");
                                return false;
                            }
                        }
                    }
                }
                if(i % 4 == 0) {
                    if(Double.parseDouble(inputField.get(i).getText()) >= Double.parseDouble(inputField.get(i+1).getText())) {
                        JOptionPane.showMessageDialog(this,
                                "<html><h2 style='color:red'>Start &#60 End</h2></html>");
                        return false;
                    }
                }
            }
        } catch(NumberFormatException ignore) {
            JOptionPane.showMessageDialog(this,
                    "<html><h2 style='color:red'>Please check again the input fields</h2></html>");
            return false;
        }
        return true;
    }
}
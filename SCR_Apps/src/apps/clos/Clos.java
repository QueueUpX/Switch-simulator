package apps.clos;

import tools.Tools;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clos extends JFrame {

    public Clos() {
        super("SCR_Apps_Clos_1.0");

        this.add(new MyPanel());
        this.setSize(1350, 650);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel {
    private JButton clear;
    private JLabel title;
    private JLabel N, k, p;
    private Font font;
    private JTextField textField;
    private DefaultCellEditor singleclick;
    private JTable input;
    private JTable output;
    private JCheckBox errors;

    MyPanel() {
        super();

        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        init();
        setup();
        addComponents();
    }

    void init() {
        clear = new JButton("<html><h3>Clear</h3></htlm>");
        title = new JLabel("<html><h1><u>3-Stage space switch analysis, with blockade</u></h1></html>", JLabel.CENTER);
        N = new JLabel("<html><h2>N, number of entries/outputs of the switch<br/>N = integer " +
                "value, power of 2</h2></html>");
        k = new JLabel("<html><h2>k, number of central matrices<br/>k = integer value, 1 &#60 k &#60 2*n-1</h2></html>");
        p = new JLabel("<html><h2>p, fraction of the total time an input is active<br/>" +
                "p = integer value, 0 &#60 p &#60 100</h2></html>");
        font = new Font("Arial", Font.PLAIN, 15);
        textField = new JTextField();
        singleclick = new DefaultCellEditor(textField);
        errors = new JCheckBox("<html><h2>Calculation errors handler</h2></html>");
        output = new JTable(2, 9);
        input = new JTable(3, 9){
            @Override
            public boolean isCellEditable(int row, int column) {
                return (row != 0 || column != 0) &&
                        (row != 1 || column != 0) &&
                        (row != 2 || column != 0);
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                super.setValueAt(aValue, row, column);

                input.getColumnModel().getColumn(column).setCellRenderer(new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value,
                                                                   boolean isSelected, boolean hasFocus, int row, int column) {
                        //Cells are by default rendered as a JLabel.
                        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected,
                                hasFocus, row, column);
                        cell.setFont(font);
                        try {
                            if (column > 0) {
                                cell.setForeground(Color.red);
                            }
                            if (row == 0 && column > 0) {
                                if (Integer.parseInt(cell.getText()) > 1 && (Integer.parseInt(cell.getText()) &
                                        (Integer.parseInt(cell.getText()) - 1)) == 0) {
                                    cell.setForeground(Color.green);
                                }
                            }
                            if (row == 1 && column > 0) {
                                double sqrt = -1;
                                double sqrt1 = -1;
                                if (Integer.parseInt(input.getValueAt(0, column).toString()) > 1 &&
                                        (Integer.parseInt(input.getValueAt(0, column).toString()) &
                                        (Integer.parseInt(input.getValueAt(0, column).toString()) - 1)) == 0) {
                                    sqrt = Math.sqrt(Double.parseDouble(input.getValueAt(0, column).toString()) / 2.0);
                                    sqrt1 = Math.sqrt(Double.parseDouble(input.getValueAt(0, column).toString()));
                                }
                                if ((int) sqrt != sqrt) {
                                    if (Integer.parseInt(cell.getText()) > 1 && Integer.parseInt(cell.getText()) < ((2*sqrt1)-1)) {
                                        cell.setForeground(Color.green);
                                    }
                                }
                                else {
                                    if (Integer.parseInt(cell.getText()) > 1 && Integer.parseInt(cell.getText()) < ((2*sqrt)-1)) {
                                        cell.setForeground(Color.green);
                                    }
                                }
                            }
                            if (row == 2 && column > 0) {
                                if (Integer.parseInt(cell.getText()) > 0 && Integer.parseInt(cell.getText()) < 100) {
                                    cell.setForeground(Color.green);
                                }
                            }
                        } catch (Exception ignore) {}
                        return cell;
                    }
                });

                try {
                    if (row == 0 && column > 0) {
                        if (aValue.toString() != null && !aValue.toString().isEmpty()) {
                            if (Integer.parseInt(aValue.toString()) > 1 && (Integer.parseInt(aValue.toString()) &
                                    (Integer.parseInt(aValue.toString()) - 1)) == 0) {
                                double sqrt = Math.sqrt(Double.parseDouble(aValue.toString()) / 2.0);
                                double sqrt1 = Math.sqrt(Double.parseDouble(aValue.toString()));
                                if ((int) sqrt != sqrt) {
                                    output.setValueAt((int) sqrt1, row, column);
                                } else {
                                    output.setValueAt((int) sqrt, row, column);
                                }
                            }
                            else {
                                output.setValueAt(null, 0, column);
                            }
                        }
                        else {
                            output.setValueAt(null, 0, column);
                        }
                    }
                    double p = Double.parseDouble(input.getValueAt(2, column).toString())/100.0;
                    double k = Double.parseDouble(input.getValueAt(1, column).toString());
                    double n = Double.parseDouble(output.getValueAt(0, column).toString());
                    double B = Tools.round(Tools.pow(1.0-Tools.pow(1.0-(p*(n/k)), 2), (int)k), 3);
                    if(errors.isSelected()) {
                        if(B > 100) {
                            B = 100;
                        }
                        if (p == 0.0) {
                            B = 0;
                        }
                        if (k >= (2 * n) - 1) {
                            B = 0;
                        }
                    }
                    output.setValueAt(B, 1, column);
                    if(errors.isSelected()) {
                        if(B < 0) {
                            output.setValueAt("<html><h4>Negative value</h4></html>", 1, column);
                        }
                    }
                } catch (Exception ignore) {output.setValueAt(null, 1, column);}
            }
        };
    }

    void setup() {
        title.setAlignmentX(0.5f);
        textField.setFont(font);
        textField.setFocusable(false);
        ((AbstractDocument)textField.getDocument()).setDocumentFilter(new DocumentFilter(){
            final Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
        // **** Make JTextField only allow digits

        singleclick.setClickCountToStart(1);
        // **** Cell begins focusable after one click

        input.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        input.setRowHeight(30);
        input.setDefaultEditor(input.getColumnClass(1), singleclick);
        input.setCellSelectionEnabled(false);
        input.setValueAt("<html><h2>N</h2></html>", 0, 0);
        input.setValueAt("<html><h2>k</h2></html>", 1, 0);
        input.setValueAt("<html><h2>p [%]</h2></html>", 2, 0);
        input.isCellEditable(0, 0);
        //input.requestFocus();

        output.setRowHeight(30);
        output.setValueAt("<html><h2>n</h2></html>", 0, 0);
        output.setValueAt("<html><h2>B[%]</h2></html>", 1, 0);
        output.setEnabled(false);

        clear.setMaximumSize(new Dimension(80,30));
        clear.addActionListener(e -> {
            for (int i=0; i<3; i++) {
                for (int j=1; j<9; j++) {
                    input.setValueAt(null, i, j);
                }
            }
            for (int i=0; i<2; i++) {
                for (int j=1; j<9; j++) {
                    output.setValueAt(null, i, j);
                }
            }
        });
        clear.setFocusable(false);

        errors.setBackground(Color.white);
        errors.setFocusable(false);
        errors.setMaximumSize(new Dimension(200, 50));
        errors.addActionListener(e -> {
            for(int i=1; i<=8; i++) {
                input.setValueAt(input.getValueAt(0, i), 0, i);
            }
        });
    }

    void addComponents() {
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        Box N = Box.createHorizontalBox();
        N.add(Box.createRigidArea(new Dimension(150, 0)));
        N.add(this.N);
        this.add(N);
        Box k = Box.createHorizontalBox();
        k.add(Box.createRigidArea(new Dimension(150, 0)));
        k.add(this.k);
        this.add(k);
        Box p = Box.createHorizontalBox();
        p.add(Box.createRigidArea(new Dimension(150, 0)));
        p.add(this.p);
        this.add(p);
        Box clear = Box.createHorizontalBox();
        clear.add(Box.createRigidArea(new Dimension(150, 0)));
        clear.add(errors);
        clear.add(Box.createHorizontalGlue());
        clear.add(Box.createRigidArea(new Dimension(805,80)));
        clear.add(this.clear);
        clear.add(Box.createRigidArea(new Dimension(50, 0)));
        this.add(clear);
        Box inputBox = Box.createHorizontalBox();
        inputBox.add(Box.createRigidArea(new Dimension(10, 0)));
        inputBox.add(input);
        inputBox.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(inputBox);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        Box outputBox = Box.createHorizontalBox();
        outputBox.add(Box.createRigidArea(new Dimension(10, 0)));
        outputBox.add(output);
        outputBox.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(outputBox);
    }
}
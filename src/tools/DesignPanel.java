package tools;

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

public abstract class DesignPanel extends JPanel {
    private static final String h1Begin = "<html><h1>", h1End = "</h1></html>";
    private static final String h2Begin = "<html><h2>", h2End = "</h2></html>";
    private static final String h3Begin = "<html><h3>", h3End = "</h3></html>";
    protected static final int h1 = 1, h2 = 2, h3 = 3;
    private TitledBorder titledBorder;
    private String TITLE;
    private ArrayList<String> DATA;
    private ArrayList<String> DATAin;
    private ArrayList<String> DATAout;
    private ArrayList<JLabel> dataText;
    private ArrayList<JLabel> dataTextIn;
    private ArrayList<JLabel> dataTextOut;
    private ArrayList<JTextField> dataInput;
    private ArrayList<JTextField> dataOutput;
    private ArrayList<JRadioButton> options;
    private ArrayList<JButton> buttons;
    private ArrayList<ButtonGroup> buttonGroup;
    private ArrayList<Integer> filterMapIn;
    private ArrayList<Integer> filterMapOut;

    protected DesignPanel() {
        super();

        init();
        config();
        setup();
        addComponents();
    }

    protected void init() {
        DATA = new ArrayList<>();
        DATAin = new ArrayList<>();
        DATAout = new ArrayList<>();
        dataText = new ArrayList<>();
        dataTextIn = new ArrayList<>();
        dataTextOut = new ArrayList<>();
        dataInput = new ArrayList<>();
        dataOutput = new ArrayList<>();
        options = new ArrayList<>();
        buttons = new ArrayList<>();
        buttonGroup = new ArrayList<>();
        filterMapIn = new ArrayList<>();
        filterMapOut = new ArrayList<>();
    }

    protected void setup() {
        try {
            titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TITLE,
                    TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
            this.setBorder(titledBorder);
        } catch (Exception ignore) {}
    }

    protected void setTITLE(String title, int hSize) {
        TITLE = setTextSize(title, hSize);
    }

    protected void addDATA(String text, int hSize) {
        DATA.add(setTextSize(text, hSize));
        addDataText(DATA.get(DATA.size()-1));
    }

    protected void addDATAin(String text, int hSize) {
        DATAin.add(setTextSize(text, hSize));
        addDataTextIn(DATAin.get(DATAin.size()-1));
        addDataInput();
    }

    protected void addDATAout(String text, int hSize) {
        DATAout.add(setTextSize(text, hSize));
        addDataTextOut(DATAout.get(DATAout.size()-1));
        addDataOutput();
    }

    protected void addOption(String name, int hSize) {
        options.add(new JRadioButton(setTextSize(name, hSize)));
        options.get(options.size()-1).setName(name);
    }

    protected void addButton(String name, int hSize) {
        buttons.add(new JButton(setTextSize(name, hSize)));
        buttons.get(buttons.size()-1).setName(name);
    }

    protected ButtonGroup createButtonGroup() {
        buttonGroup.add(new ButtonGroup());
        return buttonGroup.get(buttonGroup.size()-1);
    }

    protected void addButtonToGroup(ButtonGroup buttonGroup, JRadioButton ...jRadioButtons) {
        for(JRadioButton iterator : jRadioButtons) {
            this.buttonGroup.get(this.buttonGroup.indexOf(buttonGroup)).add(iterator);
        }
    }

    protected void integerTextField(JTextField textField) {
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
        try {
            filterMapIn.set(dataInput.indexOf(textField), 0);
        } catch(Exception ignore) {
            filterMapOut.set(dataOutput.indexOf(textField), 0);
        }
    }

    protected void decimalTextField(JTextField textField) {
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
        try {
            filterMapIn.set(dataInput.indexOf(textField), 1);
        } catch(Exception ignore) {
            filterMapOut.set(dataOutput.indexOf(textField), 1);
        }
    }

    protected static String setTextSize(String title, int hSize) {
        return switch (hSize) {
            case h1 -> h1Begin + title + h1End;
            case h2 -> h2Begin + title + h2End;
            case h3 -> h3Begin + title + h3End;
            default -> "";
        };
    }

    protected Boolean basicFilteringInputs() {
        for(int i=0; i<filterMapIn.size(); i++) {
            if(filterMapIn.get(i) == 0) {
                try {
                    if(Integer.parseInt(dataInput.get(i).getText()) < 1) {
                        JOptionPane.showMessageDialog(this,
                                setTextSize("Invalid value &#60 1", h2));
                        return false;
                    }
                } catch(NumberFormatException ignore) {
                    JOptionPane.showMessageDialog(this,
                            setTextSize("Check again the fields", h2));
                    return false;
                }
            }
            else if(filterMapIn.get(i) == 1){
                try {
                    if(Double.parseDouble(dataInput.get(i).getText()) > 1) {
                        JOptionPane.showMessageDialog(this,
                                setTextSize("Invalid value &#62 1", h2));
                        return false;
                    }
                } catch(NumberFormatException ignore) {
                    JOptionPane.showMessageDialog(this,
                            setTextSize("Check again the fields", h2));
                    return false;
                }
            }
        }
        return true;
    }

    protected String getTITLE() {
        return TITLE;
    }

    protected ArrayList<String> getDATAin() {
        return DATAin;
    }

    protected ArrayList<String> getDATAout() {
        return DATAout;
    }

    protected ArrayList<JLabel> getDataTextIn() {
        return dataTextIn;
    }

    protected ArrayList<JLabel> getDataTextOut() {
        return dataTextOut;
    }

    public ArrayList<JTextField> getDataInput() {
        return dataInput;
    }

    public ArrayList<JTextField> getDataOutput() {
        return dataOutput;
    }

    protected ArrayList<JRadioButton> getOptions() {
        return options;
    }

    protected ArrayList<JButton> getButtons() {
        return buttons;
    }

    protected ArrayList<ButtonGroup> getButtonGroup() {
        return buttonGroup;
    }

    protected ArrayList<Integer> getFilterMapIn() {
        return filterMapIn;
    }

    protected ArrayList<Integer> getFilterMapOut() {
        return filterMapOut;
    }

    private void addDataText(String text) {
        dataText.add(new JLabel(text));
        dataText.get(dataText.size()-1).setName(text);
    }

    private void addDataTextIn(String text) {
        dataTextIn.add(new JLabel(text));
        dataTextIn.get(dataTextIn.size()-1).setName(text);
    }

    private void addDataTextOut(String text) {
        dataTextOut.add(new JLabel(text));
        dataTextOut.get(dataTextOut.size()-1).setName(text);
    }

    private void addDataInput() {
        dataInput.add(new JTextField());
        dataInput.get(dataInput.size()-1).setName(dataTextIn.get(dataTextIn.size()-1).getName());
        addFilterMapIn();
    }

    private void addDataOutput() {
        dataOutput.add(new JTextField());
        dataOutput.get(dataOutput.size()-1).setName(dataTextOut.get(dataTextOut.size()-1).getName());
        addFilterMapOut();
    }

    private void addFilterMapIn() {
        filterMapIn.add(-1);
    }

    private void addFilterMapOut() {
        filterMapOut.add(-1);
    }

    protected abstract void config();
    protected abstract void addComponents();
}
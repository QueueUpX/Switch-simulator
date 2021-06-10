package apps.student.tst;

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
    private final String h1Begin = "<html><h1>", h1End = "</h1></html>";
    private final String h2Begin = "<html><h2>", h2End = "</h2></html>";
    private final String h3Begin = "<html><h3>", h3End = "</h3></html>";
    protected static final int h1 = 1, h2 = 2, h3 = 3;
    private String TITLE;
    private TitledBorder titledBorder;
    private ArrayList<String> DATAin;
    private ArrayList<String> DATAout;
    private ArrayList<JLabel> dataTextIn;
    private ArrayList<JLabel> dataTextOut;
    private ArrayList<JTextField> dataInput;
    private ArrayList<JTextField> dataOutput;
    private ArrayList<JRadioButton> options;

    protected DesignPanel() {
        super();

        init();
        setup();
        config();
        addComponents();
    }

    protected DesignPanel(int boxAxis) {
        super();

        this.setLayout(new BoxLayout(this, boxAxis));

        init();
        setup();
        config();
        addComponents();
    }

    protected void init() {
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TITLE,
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        DATAin = new ArrayList<>();
        DATAout = new ArrayList<>();
        dataTextIn = new ArrayList<>();
        dataTextOut = new ArrayList<>();
        dataInput = new ArrayList<>();
        dataOutput = new ArrayList<>();
        options = new ArrayList<>();
    }

    protected void setup() {
        this.setBorder(titledBorder);
    }

    protected void setTITLE(String title, int hSize) {
        TITLE = setTextSize(title, hSize);
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
    }

    private void addDataTextIn(String text) {
        dataTextIn.add(new JLabel(text));
    }

    private void addDataTextOut(String text) {
        dataTextOut.add(new JLabel(text));
    }

    private void addDataInput() {
        dataInput.add(new JTextField());
    }

    private void addDataOutput() {
        dataOutput.add(new JTextField());
    }

    private String setTextSize(String title, int hSize) {
        return switch (hSize) {
            case h1 -> h1Begin + title + h1End;
            case h2 -> h2Begin + title + h2End;
            case h3 -> h3Begin + title + h3End;
            default -> "";
        };
    }

    abstract void config();
    abstract void addComponents();
}

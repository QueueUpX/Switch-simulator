package apps.sts_tst.tst;

import tools.DesignPanel;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Graph extends DesignPanel {
    public Graph() {
        super();
    }

    @Override
    protected void config() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(1000, 1000));
        setTITLE("Graph settings", h2);
        addDATAin("Start p:", h2);
        decimalTextField(getDataInput().get(0));
        addDATAin("End p:", h2);
        decimalTextField(getDataInput().get(1));
        addDATAin("Step p:", h2);
        decimalTextField(getDataInput().get(2));
        addDATAin("New max B[0,1](not required):", h2);
        decimalTextField(getDataInput().get(3));
        addDATAin("Start B:", h2);
        decimalTextField(getDataInput().get(4));
        addDATAin("End B:", h2);
        decimalTextField(getDataInput().get(5));
        addDATAin("Step B:", h2);
        decimalTextField(getDataInput().get(6));
        addDATAin("New p[0, 1](not required):", h2);
        decimalTextField(getDataInput().get(7));
        addDATAin("Start p:", h2);
        decimalTextField(getDataInput().get(8));
        addDATAin("End p:", h2);
        decimalTextField(getDataInput().get(9));
        addDATAin("Step p:", h2);
        decimalTextField(getDataInput().get(10));
        addDATAin("New l(not required):", h2);
        integerTextField(getDataInput().get(11));
        addDATAin("Start l:", h2);
        integerTextField(getDataInput().get(12));
        addDATAin("End l:", h2);
        integerTextField(getDataInput().get(13));
        addDATAin("Step l:", h2);
        integerTextField(getDataInput().get(14));
        addDATAin("New p[0, 1](not required):", h2);
        decimalTextField(getDataInput().get(15));
        for(JTextField iterator : getDataInput()) {
            iterator.setColumns(15);
            iterator.setMaximumSize(new Dimension(200, 30));
            iterator.setFont(new Font("Arial", Font.BOLD, 16));
        }
    }

    @Override
    protected void addComponents() {
        for(int i=4*getSelectedOption(); i<4*getSelectedOption()+4; i++) {
            Box hBox = Box.createHorizontalBox();
            hBox.add(Box.createRigidArea(new Dimension(10, 0)));
            hBox.add(getDataTextIn().get(i));
            hBox.add(getDataInput().get(i));
            hBox.add(Box.createRigidArea(new Dimension(10, 0)));
            this.add(hBox);
        }
    }

    public void reallocateComponents() {
        this.removeAll();
        this.revalidate();
        this.repaint();
        addComponents();
    }

    private int getSelectedOption() {
        return TST.getOutput().getSelectedOption();
    }

    public Boolean filterInputs() {
        for(int i=getSelectedOption()*4; i<getSelectedOption()*4+3; i++) {
            if(getFilterMapIn().get(i) == 0) {
                try {
                    if(Integer.parseInt(getDataInput().get(i).getText()) < 1) {
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
            else if(getFilterMapIn().get(i) == 1){
                try {
                    if(Double.parseDouble(getDataInput().get(i).getText()) > 1) {
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
        if(Double.parseDouble(getDataInput().get(getSelectedOption()*4).getText()) >=
                Double.parseDouble(getDataInput().get(getSelectedOption()*4+1).getText())) {
            JOptionPane.showMessageDialog(this,
                    setTextSize("Start >= End", h2));
            return false;
        }
        return true;
    }

    public ArrayList<Double> getHorizontalValues() {
        BigDecimal start = new BigDecimal(getDataInput().get(getSelectedOption()*4).getText());
        BigDecimal end = new BigDecimal(getDataInput().get(getSelectedOption()*4+1).getText());
        BigDecimal step = new BigDecimal(getDataInput().get(getSelectedOption()*4+2).getText());
        ArrayList<Double> horizontalValues = new ArrayList<>();
        while(start.compareTo(end) < 0) {
            horizontalValues.add(start.doubleValue());
            start = start.add(step);
        }
        horizontalValues.add(end.doubleValue());
        return horizontalValues;
    }

    public ArrayList<Double> getVerticalValues() {
        ArrayList<Double> verticalValues = new ArrayList<>();
        switch (getSelectedOption()) {
            case 0 -> {
                try {
                    double aux = Double.parseDouble(getDataInput().get(3).getText());
                    for (int i = 0; i < getHorizontalValues().size(); i++) {
                        verticalValues.add(apps.sts_tst.tst.SquareMatrix.calculateComplexFp(getHorizontalValues().get(i), aux));
                    }
                } catch (NumberFormatException ignore) {
                    for (int i = 0; i < getHorizontalValues().size(); i++) {
                        verticalValues.add(apps.sts_tst.tst.SquareMatrix.calculateComplexFp(getHorizontalValues().get(i)));
                    }
                }
                JOptionPane.showMessageDialog(this,
                        setTextSize("Warning l &#60 " + apps.sts_tst.tst.SquareMatrix.getKInterval()[0] + " values are included", h2));
            }
            case 1 -> {
                try {
                    double aux = Double.parseDouble(getDataInput().get(7).getText());
                    for (int i = 0; i < getHorizontalValues().size(); i++) {
                        verticalValues.add(apps.sts_tst.tst.SquareMatrix.calculateComplexFB(getHorizontalValues().get(i), aux));
                    }
                } catch (NumberFormatException ignore) {
                    for (int i = 0; i < getHorizontalValues().size(); i++) {
                        verticalValues.add(apps.sts_tst.tst.SquareMatrix.calculateComplexFB(getHorizontalValues().get(i)));
                    }
                }
                JOptionPane.showMessageDialog(this,
                        setTextSize("Warning l &#60 " + apps.sts_tst.tst.SquareMatrix.getKInterval()[0] + " values are included", h2));
            }
            case 2 -> {
                try {
                    double aux = Double.parseDouble(getDataInput().get(11).getText());
                    for (int i = 0; i < getHorizontalValues().size(); i++) {
                        verticalValues.add(apps.sts_tst.tst.SquareMatrix.calculateBFp(getHorizontalValues().get(i), (int) aux));
                    }
                } catch (NumberFormatException ignore) {
                    for (int i = 0; i < getHorizontalValues().size(); i++) {
                        verticalValues.add(apps.sts_tst.tst.SquareMatrix.calculateBFp(getHorizontalValues().get(i)));
                    }
                }
                if (apps.sts_tst.tst.SquareMatrix.Output.k < apps.sts_tst.tst.SquareMatrix.getKInterval()[0] && getDataInput().get(11).getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            setTextSize("Warning l &#60 " + apps.sts_tst.tst.SquareMatrix.getKInterval()[0] + " values are included", h2));
                } else if (!getDataInput().get(11).getText().isEmpty() &&
                        Integer.parseInt(getDataInput().get(11).getText()) < apps.sts_tst.tst.SquareMatrix.getKInterval()[0]) {
                    JOptionPane.showMessageDialog(this,
                            setTextSize("Warning l &#60 " + apps.sts_tst.tst.SquareMatrix.getKInterval()[0] + " values are included", h2));
                }
            }
            case 3 -> {
                try {
                    double aux = Double.parseDouble(getDataInput().get(15).getText());
                    for (int i = 0; i < getHorizontalValues().size(); i++) {
                        verticalValues.add(apps.sts_tst.tst.SquareMatrix.calculateBFk(getHorizontalValues().get(i).intValue(), aux));
                    }
                } catch (NumberFormatException ignore) {
                    for (int i = 0; i < getHorizontalValues().size(); i++) {
                        verticalValues.add(apps.sts_tst.tst.SquareMatrix.calculateBFk(getHorizontalValues().get(i).intValue()));
                    }
                }
                if (Integer.parseInt(getDataInput().get(15).getText()) < apps.sts_tst.tst.SquareMatrix.getKInterval()[0]) {
                    JOptionPane.showMessageDialog(this,
                            setTextSize("Warning l &#60 " + SquareMatrix.getKInterval()[0] + " values are included", h2));
                }
            }
        }
        return verticalValues;
    }
}
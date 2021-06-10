package apps.sts_tst.sts;

import tools.DesignPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Input extends DesignPanel {
    public Input() {
        super();
    }

    @Override
    protected void config() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(1000, 1000));
        setTITLE("Input data", h2);
        addDATAin("Number of users:", h2);
        integerTextField(getDataInput().get(0));
        addDATAin("Complexity of implementation(max):", h2);
        decimalTextField(getDataInput().get(1));
        getFilterMapIn().set(1, -1);
        addDATAin("Number of TDM channels:", h2);
        integerTextField(getDataInput().get(2));
        addDATAin("The probability that a line is active p[0, 1]:", h2);
        decimalTextField(getDataInput().get(3));
        addDATAin("Probability of blocking(max) B[0, 1]:", h2);
        decimalTextField(getDataInput().get(4));
        addOption("Square Matrix", h2);
        addButtonToGroup(createButtonGroup(), getOptions().get(0));
        getOptions().get(0).setSelected(true);
        for(JTextField iterator : getDataInput()) {
            iterator.setColumns(15);
            iterator.setMaximumSize(new Dimension(200, 30));
            iterator.setFont(new Font("Arial", Font.BOLD, 16));
        }
        for(JRadioButton iterator : getOptions()) {
            iterator.setFocusable(false);
        }
    }

    @Override
    protected void addComponents() {
        addTextInputBox();
        addOptionBox();
    }

    private void addTextInputBox() {
        for(int i=0; i<getDATAin().size(); i++) {
            Box hBox = Box.createHorizontalBox();
            hBox.add(Box.createRigidArea(new Dimension(10, 0)));
            hBox.add(getDataTextIn().get(i));
            hBox.add(getDataInput().get(i));
            hBox.add(Box.createRigidArea(new Dimension(10, 0)));
            this.add(hBox);
        }
    }

    private void addOptionBox() {
        for(int i=0; i<getOptions().size(); i++) {
            Box hBox = Box.createHorizontalBox();
            hBox.add(Box.createRigidArea(new Dimension(10, 0)));
            hBox.add(getOptions().get(i));
            hBox.add(Box.createHorizontalGlue());
            this.add(hBox);
        }
    }

    public Boolean filterInputs() {
        if(!basicFilteringInputs()) {
            return false;
        }
        return true;
    }

    public ArrayList<JTextField> getDataInput() {
        return super.getDataInput();
    }

    public int getSelectedOption() {
        for(int i=0; i<getOptions().size(); i++) {
            if(getOptions().get(i).isSelected()) {
                return i;
            }
        }
        return 0;
    }
}

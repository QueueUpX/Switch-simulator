package apps.sts_tst.tst;

import tools.DesignPanel;

import javax.swing.*;
import java.awt.*;

public class Output extends DesignPanel {
    public Output() {
        super();
    }

    @Override
    protected void config() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(1000, 1000));
        setTITLE("Output data", h2);
        addDATAout("Number of entries N:", h2);
        addDATAout("Complexity of implementation(max):", h2);
        addDATAout("Calculated implementation complexity:", h2);
        addDATAout("Number of space stage time l:", h2);
        addDATAout("Blocking probability(max):", h2);
        addDATAout("Calculated blocking probability:", h2);
        addOption("Complexity = f(p)", h2);
        addOption("Complexity = f(B)", h2);
        addOption("Probability of blocking = f(p)", h2);
        addOption("Probability of blocking = f(k)", h2);
        getOptions().get(0).setSelected(true);
        addButtonToGroup(createButtonGroup(), getOptions().get(0), getOptions().get(1), getOptions().get(2), getOptions().get(3));
        for(JTextField iterator : getDataOutput()) {
            iterator.setColumns(15);
            iterator.setMaximumSize(new Dimension(200, 30));
            iterator.setEditable(false);
            iterator.setFont(new Font("Arial", Font.BOLD, 16));
        }
        for(JRadioButton iterator : getOptions()) {
            iterator.setFocusable(false);
        }
    }

    @Override
    protected void addComponents() {
        for(int i=0; i<getDATAout().size(); i++) {
            Box hBox = Box.createHorizontalBox();
            hBox.add(Box.createRigidArea(new Dimension(10, 0)));
            hBox.add(getDataTextOut().get(i));
            hBox.add(getDataOutput().get(i));
            hBox.add(Box.createRigidArea(new Dimension(10, 0)));
            this.add(hBox);
        }
        for(int i=0; i<getOptions().size(); i++) {
            Box hBox = Box.createHorizontalBox();
            hBox.add(Box.createRigidArea(new Dimension(10, 0)));
            hBox.add(getOptions().get(i));
            hBox.add(Box.createHorizontalGlue());
            this.add(hBox);
        }
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

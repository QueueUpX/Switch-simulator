package apps.student.tst;

import javax.swing.*;

public class TST extends DesignPanel {
    private JButton next, back;

    public TST() {
        super(BoxLayout.Y_AXIS);
    }

    @Override
    void config() {
        setTITLE("TST Switch Design", h1);
    }

    @Override
    void addComponents() {

    }

    @Override
    protected void init() {
        super.init();

        next = new JButton();
        back = new JButton();
    }
}

package apps.sts_tst;

import apps.sts_tst.sts.STS;
import apps.sts_tst.tst.TST;
import tools.DesignPanel;

public class STS_TST_Panel extends DesignPanel {

    public STS_TST_Panel() {
        super();
    }

    @Override
    protected void config() {
        setTITLE("STS/TST Switch Design", h2);
        addButton("STS", h2);
        addButton("TST", h2);
        getButtons().get(0).setFocusable(false);
        getButtons().get(1).setFocusable(false);
    }

    @Override
    protected void addComponents() {
        this.add(getButtons().get(0));
        this.add(getButtons().get(1));
    }

    @Override
    protected void setup() {
        super.setup();
        getButtons().get(0).addActionListener(e -> {
            this.getParent().add(new STS());
            this.getParent().revalidate();
            this.getParent().repaint();
            this.getParent().remove(this);
        });
        getButtons().get(1).addActionListener(e -> {
            this.getParent().add(new TST());
            this.getParent().revalidate();
            this.getParent().repaint();
            this.getParent().remove(this);
        });
    }
}

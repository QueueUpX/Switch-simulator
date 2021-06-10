package apps.sts_tst.tst;

import apps.sts_tst.STS_TST_Panel;
import tools.DesignPanel;
import tools.GridGraph;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class TST extends DesignPanel {
    private static Input input;
    private static Output output;
    private static Graph graph;
    private HashMap<JComponent, Integer> state;

    public TST() {
        super();
    }

    @Override
    protected void config() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setTITLE("TST Switch Design", h2);
        state.put(input, 0);
        state.put(output, 1);
        state.put(graph, 2);
        addButton("Back", h3);
        addButton("Next", h3);
        for(JButton iterator : getButtons()) {
            iterator.setFocusable(false);
            iterator.setMaximumSize(new Dimension(100, 30));
        }
    }

    @Override
    protected void addComponents() {
        this.add(input);
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createRigidArea(new Dimension(50, 0)));
        hBox.add(getButtons().get(0));
        hBox.add(Box.createHorizontalGlue());
        hBox.add(Box.createHorizontalGlue());
        hBox.add(getButtons().get(1));
        hBox.add(Box.createRigidArea(new Dimension(50, 0)));
        this.add(hBox);
    }

    @Override
    protected void init() {
        super.init();
        input = new Input();
        output = new Output();
        graph = new Graph();
        state = new HashMap<>();
    }

    @Override
    protected void setup() {
        super.setup();
        getButtons().get(0).addActionListener(e -> {
            switch(state.get(this.getComponent(0))) {
                case 0:
                    if(JOptionPane.showConfirmDialog(this, setTextSize("Return to switch design menu?", h2)) == 0) {
                        this.getParent().add(new STS_TST_Panel(), 0);
                        this.getParent().revalidate();
                        this.getParent().repaint();
                        this.getParent().remove(this);
                    }
                    break;
                case 1:
                    this.add(input, 0);
                    this.revalidate();
                    this.repaint();
                    this.remove(output);
                    break;
                case 2:
                    getButtons().get(1).setText(setTextSize("next", h3));
                    this.add(output, 0);
                    this.revalidate();
                    this.repaint();
                    this.remove(graph);
                    break;
                default:
                    break;
            }
        });
        getButtons().get(1).addActionListener(e -> {
            switch(state.get(this.getComponent(0))) {
                case 0:
                    if(input.filterInputs()) {
                        switch (input.getSelectedOption()) {
                            case 0:
                                apps.sts_tst.tst.SquareMatrix.setInput(input);
                                apps.sts_tst.tst.SquareMatrix.setOutput(output);
                                break;
                            default:
                                break;
                        }
                        this.add(output, 0);
                        this.revalidate();
                        this.repaint();
                        this.remove(input);
                    }
                    break;
                case 1:
                    getButtons().get(1).setText(setTextSize("show", h3));
                    graph.reallocateComponents();
                    this.add(graph, 0);
                    this.revalidate();
                    this.repaint();
                    this.remove(output);
                    break;
                case 2:
                    if(graph.filterInputs()) {
                        new GridGraph(graph.getVerticalValues(), graph.getHorizontalValues());
                    }
                    break;
                default:
                    break;
            }
        });
    }

    public static Input getInput() {
        return input;
    }

    public static Output getOutput() {
        return output;
    }

    public static Graph getGraph() {
        return graph;
    }
}

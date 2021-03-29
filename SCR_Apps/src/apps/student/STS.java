package apps.student;

import tools.Tools;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class STS extends JPanel {
    private final String TITLE = "<html><h1>Proiectare comutator STS</h1></html>";
    private TitledBorder titledBorder;
    private JButton back, next;
    private Input input;
    private Output output;
    private GridGraph gridGraph;

    public STS() {
        super();

        init();
        setup();
        addComponents();

        this.setPreferredSize(Student.size);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 330, 10));
    }

    private void init() {
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TITLE,
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        back = new JButton("<html>Back</html>");
        next = new JButton("<html>Next</html>");
        input = new Input();
        output = new Output();
        gridGraph = new GridGraph();
    }

    private void setup() {
        this.setBorder(titledBorder);
        back.addActionListener(e -> {
            if(this.getComponent(0) instanceof Input) {
                if(JOptionPane.showConfirmDialog(this, "<html><h2>Reveniti la meniul principal?</h2></html>") == 0) {
                    this.getParent().add(new SwitchDesign());
                    this.getParent().revalidate();
                    this.getParent().repaint();
                    this.getParent().remove(this);
                    return;
                }
            }
            if(this.getComponent(0) instanceof Output) {
                this.add(input, 0);
                this.revalidate();
                this.repaint();
                this.remove(output);
            }
        });
        next.addActionListener(e -> {
            if(this.getComponent(0) instanceof Input) {
                if(filterData(((Input) this.getComponent(0)).getDataInput())) {
                    computeData(((Input) this.getComponent(0)).getDataInput(), output.getDataOutput());
                    this.add(output, 0);
                    this.revalidate();
                    this.repaint();
                    this.remove(input);
                    return;
                }
            }
            if(this.getComponent(0) instanceof Output) {
                JFrame frame = new JFrame();
                frame.add(gridGraph);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(new Dimension(1000, 700));
                frame.setLayout(new FlowLayout());
                frame.setVisible(true);
                //this.add(gridGraph, 0);
                this.revalidate();
                this.repaint();
                this.remove(output);
            }
        });
    }

    private void addComponents() {
        this.add(input);
        this.add(back);
        this.add(next);
    }

    private Boolean filterData(ArrayList<JTextField> inputs) { //[0-9]*\.?[0-9]* regex for double values
        for (JTextField jTextField : inputs) {
            if (jTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(this,
                        "<html><h2 style='color:red'>Toate datele de intrare trebuie completate</h2></html>");
                return false;
            }
        }
        return true;
    }

    private void computeData(ArrayList<JTextField> inputs, ArrayList<JTextField> outputs) {
        int nrAbonati = Integer.parseInt(inputs.get(0).getText());
        System.out.println(nrAbonati);
        int complexMax = Integer.parseInt(inputs.get(1).getText());
        System.out.println(complexMax);
        int nrCanale = Integer.parseInt(inputs.get(2).getText());
        System.out.println(nrCanale);
        double p = Double.parseDouble(inputs.get(3).getText());
        System.out.println(p);
        double B = Double.parseDouble(inputs.get(4).getText());
        System.out.println(B);
        int N = nrAbonati/nrCanale;
        System.out.println(N);
        int k = 1;
        double result;
        while(true) {
            result = Tools.pow(1-Tools.pow(1-(p*N)/k, 2), k);
            System.out.println("Compare: " + Tools.round(B,1) + " To: " + Tools.round(result, 1));
            /*if(Tools.round(B,1) == Tools.round(result, 1)) {
                break;
            }*/
            if(Tools.round(result, 1) <= Tools.round(B,1)) {
                break;
            }
            k++;
        }
        System.out.println(k);
        System.out.println(result);
    }
}

class Input extends JPanel {
    private final String TITLE = "<html><h2>Date de intrare</h2></html>";
    private final String[] DATA = {"<html><h2>Numarul de abonati:</h2></html>",
            "<html><h2>Complexitatea implementarii(maxima):</h2></html>",
            "<html><h2>Numarul de canale TDM:</h2></html>",
            "<html><h2>Probabilitatea ca o linie sa fie activa p[0, 1]:</h2></html>",
            "<html><h2>Probabilitatea de blocare(maxima) B[0, 1]:</h2></html>",
            "<html><h2>Matrice Patrata</h2></html>",
            "<html><h2>Clos</h2></html>",
            "<html><h2>Delta</h2></html>",
            "<html><h2>Benes</h2></html>"};
    private TitledBorder titledBorder;
    private ArrayList<JLabel> data;
    private ArrayList<JTextField> dataInput;
    private ArrayList<JRadioButton> type;

    Input() {
        super();

        init();
        setup();
        addComponents();

        this.setPreferredSize(new Dimension(650, 480));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void init() {
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TITLE,
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        data = new ArrayList<>(5);
        dataInput = new ArrayList<>(5);
        type = new ArrayList<>(4);
    }

    private void setup() {
        this.setBorder(titledBorder);
        for(int i=0; i<5; i++) {
            data.add(new JLabel(DATA[i]));
            dataInput.add(new JTextField(8));
            dataInput.get(i).setMaximumSize(new Dimension(50,30));
            dataInput.get(i).setFont(new Font("Arial", Font.BOLD, 15));
        }
        for(int i=0; i<4; i++) {
            type.add(new JRadioButton(DATA[5+i]));
            type.get(i).setFocusable(false);
            type.get(i).setMaximumSize(new Dimension(180, 35));
        }
    }

    private void addComponents() {
        for(int i=0; i<data.size(); i++) {
            Box hBox = Box.createHorizontalBox();
            hBox.add(Box.createRigidArea(new Dimension(50, 0)));
            hBox.add(data.get(i));
            hBox.add(dataInput.get(i));
            hBox.add(Box.createRigidArea(new Dimension(50, 0)));
            this.add(hBox);
        }
        ButtonGroup option = new ButtonGroup();
        for(int i=0; i<4; i++) {
            option.add(type.get(i));
            Box hBox = Box.createHorizontalBox();
            hBox.add(Box.createRigidArea(new Dimension(50, 0)));
            hBox.add(type.get(i));
            hBox.add(Box.createHorizontalGlue());
            this.add(hBox);
        }
        type.get(0).setSelected(true);
    }

    public ArrayList<JTextField> getDataInput() {
        return dataInput;
    }
}

class Output extends JPanel {
    private final String TITLE = "<html><h2>Date de Iesire</h2></html>";
    private final String[] DATA = {"<html><h2>Numar de intrari N:</h2></html>",
            "<html><h2>Complexitatea implementarii calculata:</h2></html>",
            "<html><h2>Numar matrici centrale k:</h2></html>",
            "<html><h2>Probabilitatea de blocare calculata:</h2></html>",
            "<html><h2></h2></html>"};
    private TitledBorder titledBorder;
    private ArrayList<JLabel> data;
    private ArrayList<JTextField> dataOutput;

    Output() {
        super();

        init();
        setup();
        addComponents();

        this.setPreferredSize(new Dimension(650, 480));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void init() {
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), TITLE,
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        data = new ArrayList<>(4);
        dataOutput = new ArrayList<>(4);
    }

    private void setup() {
        this.setBorder(titledBorder);
        for(int i=0; i<4; i++) {
            data.add(new JLabel(DATA[i]));
            dataOutput.add(new JTextField(8));
            dataOutput.get(i).setMaximumSize(new Dimension(50,30));
            dataOutput.get(i).setFont(new Font("Arial", Font.BOLD, 15));
            dataOutput.get(i).setEditable(false);
        }
    }

    private void addComponents() {
        for(int i=0; i<data.size(); i++) {
            Box hBox = Box.createHorizontalBox();
            hBox.add(Box.createRigidArea(new Dimension(50, 0)));
            hBox.add(data.get(i));
            hBox.add(dataOutput.get(i));
            hBox.add(Box.createRigidArea(new Dimension(50, 0)));
            this.add(hBox);
        }
    }

    public ArrayList<JTextField> getDataOutput() {
        return dataOutput;
    }
}
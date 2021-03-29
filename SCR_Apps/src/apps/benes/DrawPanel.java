package apps.benes;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrawPanel extends JPanel {
    private final int width;
    private final int height;
    private final int clientIn;
    private final int clientOut;
    private int input, output;
    private Boolean retry = null;
    private final JTextField textIn;
    private final JTextField textOut;
    private final JButton add;
    private ArrayList<Com> inCom, centralCom, outCom;
    private ArrayList<Path> stageOnePaths, stageTwoPaths;
    private ArrayList<Integer> connectionStack;
    private ArrayList<ArrayList<Path>> road;
    private Path one = null, two = null, three = null, four = null, five = null;
    //private Path temp;
    private Path tempThree;

    public DrawPanel(int width, int height, int clientIn, int clientOut, JTextField textIn, JTextField textOut, JButton add) {
        super();

        this.width = width;
        this.height = height;
        this.clientIn = clientIn;
        this.clientOut = clientOut;
        this.textIn = textIn;
        this.textOut = textOut;
        this.add = add;

        this.setMaximumSize(new Dimension(width, height+2));

        init();
        setup();
    }

    private void init() {
        inCom = new ArrayList<>(clientIn);
        centralCom = new ArrayList<>(2);
        outCom = new ArrayList<>(clientOut);
        stageOnePaths = new ArrayList<>(clientIn+clientOut);
        stageTwoPaths = new ArrayList<>(clientIn+clientOut);
        connectionStack = new ArrayList<>(clientIn+clientOut);
        road = new ArrayList<>(clientIn+clientOut);
    }

    private void setup() {
        ((AbstractDocument)textIn.getDocument()).setDocumentFilter(new DocumentFilter(){
            final Pattern regEx = Pattern.compile("[0-9]");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches() || textIn.getText().length() > 1){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
        ((AbstractDocument)textOut.getDocument()).setDocumentFilter(new DocumentFilter(){
            final Pattern regEx = Pattern.compile("[0-9]");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches() || textOut.getText().length() > 1){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
        add.addActionListener(e -> {
            if (!textIn.getText().equals("") && !textOut.getText().equals("")) {
                if (Integer.parseInt(textIn.getText()) < (clientIn + clientOut) && Integer.parseInt(textOut.getText()) < (clientIn+clientOut)) {
                    if (connectionStack.get(Integer.parseInt(textIn.getText())) != null) {
                        JOptionPane.showMessageDialog(this,
                                "<html><h2 style='color:red'>Abonat intrare deja utilizat</h2></html>");
                    } else if (connectionStack.contains(Integer.parseInt(textOut.getText()))) {
                        JOptionPane.showMessageDialog(this,
                                "<html><h2 style='color:red'>Abonat iesire deja utilizat</h2></html>");
                    } else {
                        input = Integer.parseInt(textIn.getText());
                        output = Integer.parseInt(textOut.getText());
                        addConnection();
                        addRoad(input, output, 0);
                        this.repaint();
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "<html><h2 style='color:red'>Abonatul introdus este inexistent</h2></html>");
                }
            }
        });
        createSpaceForConnections();
        createSpaceForRoad();
        createInCom();
        createCentralCom();
        createOutCom();
        makeStageOnePaths();
        makeStageTwoPaths();
    }

    private void addConnection() {
        connectionStack.set(input, output);
    }

    private void createSpaceForConnections() {
        for(int i=0; i<clientIn+clientOut; i++) {
            connectionStack.add(null);
        }
    }

    private void createSpaceForRoad() {
        for (int i=0; i<clientIn+clientOut; i++) {
            road.add(new ArrayList<>());
        }
    }

    private void createInCom() {
        int width = this.width/(this.clientIn*2);
        int height = (this.height)/this.clientIn;
        if(width > this.width/(4*2)) {
            width = this.width/(4*2);
        }
        for(int i=0; i<clientIn; i++) {
            inCom.add(new Com(40, (height*i), width, height, 2, 2, clientIn, clientOut));
        }
    }

    private void createCentralCom() {
        int width = this.width/4;
        int height = this.height/2;
        for(int i=0; i<2; i++) {
            centralCom.add(new Com(this.width/2-width/2, (height*i)+height/100, width, height-2*(height/100),
                    clientIn, clientOut, clientIn, clientOut));
            if(clientIn == 2) {
                centralCom.get(centralCom.size()-1).getInPins().get(0).setY1(centralCom.get(centralCom.size()-1).getInPins().get(0).getY1()+50);
                centralCom.get(centralCom.size()-1).getInPins().get(0).setY2(centralCom.get(centralCom.size()-1).getInPins().get(0).getY2()+50);
                centralCom.get(centralCom.size()-1).getInPins().get(1).setY1(centralCom.get(centralCom.size()-1).getInPins().get(1).getY1()-50);
                centralCom.get(centralCom.size()-1).getInPins().get(1).setY2(centralCom.get(centralCom.size()-1).getInPins().get(1).getY2()-50);

                centralCom.get(centralCom.size()-1).getOutPins().get(0).setY1(centralCom.get(centralCom.size()-1).getOutPins().get(0).getY1()+50);
                centralCom.get(centralCom.size()-1).getOutPins().get(0).setY2(centralCom.get(centralCom.size()-1).getOutPins().get(0).getY2()+50);
                centralCom.get(centralCom.size()-1).getOutPins().get(1).setY1(centralCom.get(centralCom.size()-1).getOutPins().get(1).getY1()-50);
                centralCom.get(centralCom.size()-1).getOutPins().get(1).setY2(centralCom.get(centralCom.size()-1).getOutPins().get(1).getY2()-50);
            }
        }
    }

    private void createOutCom() {
        int width = this.width/(this.clientOut*2);
        int height = (this.height)/(this.clientOut);
        if(width > this.width/(4*2)) {
            width = this.width/(4*2);
        }
        for(int i=0; i<clientOut; i++) {
            outCom.add(new Com(this.width-width-40, (height*i), width, height, 2, 2, clientIn, clientOut));
        }
    }

    private void makeStageOnePaths() {
        for(int i=0; i<clientIn; i++) {
            stageOnePaths.add(new Path(inCom.get(i).getOutPins().get(0), centralCom.get(0).getInPins().get(i)));
            stageOnePaths.add(new Path(inCom.get(i).getOutPins().get(1), centralCom.get(1).getInPins().get(i)));
        }
    }

    private void makeStageTwoPaths() {
        for(int i=0; i<clientOut; i++) {
            stageTwoPaths.add(new Path(centralCom.get(0).getOutPins().get(i), outCom.get(i).getInPins().get(0)));
            stageTwoPaths.add(new Path(centralCom.get(1).getOutPins().get(i), outCom.get(i).getInPins().get(1)));
        }
    }

    private void removeRoad(int input) {
        for(int i=0; i<road.get(input).size(); i++) {
            road.get(input).get(i).setState(false);
            road.get(input).get(i).setColor(Color.black);
        }
        road.set(input, new ArrayList<>());
    }

    /*private void addRoad(int input, int output, int option) {
        Color color = inCom.get(input/2).getColor();
        if(option > 1) {
            System.out.println("Entry: " + input + " output: " + output);
            int lastId;
            int lastOption;
            lastId = temp.getInPin().getId();
            lastOption = temp.getInPin().getOption();
            if(tempThree != null) {
                System.out.println("TempThree not null");
                lastId = tempThree.getInPin().getId();
                lastOption = tempThree.getInPin().getOption();
            }
            System.out.println("lastId: " + lastId + " output: " + connectionStack.get(lastId) + " lastOption: " + lastOption);
            removeRoad(lastId);
            retry = true;
            addRoad(lastId, connectionStack.get(lastId), 1-(lastOption&1));
            retry = null;
            addRoad(input, output, 0);
            System.out.println("OUT");
            return;
        }

        try {
            if(retry != null)
                tempThree = null;
            one = new Path(inCom.get(input/2).getInPins().get(input&1), inCom.get(input/2).getOutPins().get(option));
            temp = one;
            one.setId(input, option);
            two = stageOnePaths.get(input-(input&1)+option);
            temp = two;
            two.setId(input, option);
            three = new Path(stageOnePaths.get(input-(input&1)+option).getInPin(), centralCom.get(option).getOutPins().get(output/2));
            tempThree = three;
            three.setId(input, option);
            four = stageTwoPaths.get((output/2)*2+option);
            temp = four;
            four.setId(input, option);
            five = new Path(outCom.get(output/2).getInPins().get(option), outCom.get(output/2).getOutPins().get(output&1));
            temp = five;
            five.setId(input, option);

            System.out.println("I DO");
            one.setColor(color);

            one.setState(true);
            two.setColor(color);

            two.setState(true);
            three.setColor(color);

            three.setState(true);
            four.setColor(color);

            four.setState(true);
            five.setColor(color);

            five.setState(true);
            road.set(input, new ArrayList<>() {{
                add(one);
                add(two);
                add(three);
                add(four);
                add(five);
            }});
            tempThree = null;
        } catch (PinUsedException e) {
            if(retry == null) {
                System.out.println("Conflict retry null");
                addRoad(input, output, ++option);
            }
            if(retry != null) {
                System.out.println("Conflict retry not null");
                //retry = null;
                addRoad(input, output, 2);
            }
        }
    }*/

    private void addRoad(int input, int output, int option) {
        Color color = inCom.get(input/2).getColor();
        if(option > 1) {
            //System.out.println("Entry: " + input + " output: " + output);
            int lastId;
            int lastOption;
            lastId = inCom.get(input/2).getInPins().get(1-(input&1)).getId();
            lastOption = inCom.get(input/2).getInPins().get(1-(input&1)).getOption();
            if(tempThree != null && retry != null) {
                //System.out.println("TempThree not null");
                lastId = tempThree.getInPin().getId();
                lastOption = tempThree.getInPin().getOption();
            }
            //System.out.println("lastId: " + lastId + " output: " + connectionStack.get(lastId) + " lastOption: " + lastOption);
            removeRoad(lastId);
            retry = true;
            addRoad(lastId, connectionStack.get(lastId), 1-(lastOption&1));
            retry = null;
            addRoad(input, output, 0);
            //System.out.println("OUT");
            return;
        }

        try {
            if(retry != null)
                tempThree = null;
            one = new Path(inCom.get(input/2).getInPins().get(input&1), inCom.get(input/2).getOutPins().get(option));
            //temp = one;
            one.setId(input, option);
            two = stageOnePaths.get(input-(input&1)+option);
            two.setId(input, option);
            three = new Path(stageOnePaths.get(input-(input&1)+option).getInPin(), centralCom.get(option).getOutPins().get(output/2));
            tempThree = three;
            three.setId(input, option);
            four = stageTwoPaths.get((output/2)*2+option);
            four.setId(input, option);
            five = new Path(outCom.get(output/2).getInPins().get(option), outCom.get(output/2).getOutPins().get(output&1));
            five.setId(input, option);

            //System.out.println("I DO");
            one.setColor(color);

            one.setState(true);
            two.setColor(color);

            two.setState(true);
            three.setColor(color);

            three.setState(true);
            four.setColor(color);

            four.setState(true);
            five.setColor(color);

            five.setState(true);
            road.set(input, new ArrayList<>() {{
                add(one);
                add(two);
                add(three);
                add(four);
                add(five);
            }});
            tempThree = null;
        } catch (PinUsedException e) {
            if(retry == null) {
                //System.out.println("Conflict retry null");
                addRoad(input, output, ++option);
            }
            if(retry != null) {
                //System.out.println("Conflict retry not null");
                addRoad(input, output, 2);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawInCom(g2d);
        drawCentralCom(g2d);
        drawOutCom(g2d);
        drawStageOnePaths(g2d);
        drawStageTwoPaths(g2d);
        drawRoad(g2d);
    }

    private void drawInCom(Graphics2D g) {
        for(int i=0; i<inCom.size(); i++) {
            inCom.get(i).draw(g);
            inCom.get(i).drawInputValuesToInPins(g, i);
        }
    }

    private void drawCentralCom(Graphics2D g) {
        for(Com iterator : centralCom) {
            iterator.draw(g);
        }
    }

    private void drawOutCom(Graphics2D g) {
        for(int i=0; i<outCom.size(); i++) {
            outCom.get(i).draw(g);
            outCom.get(i).drawInputValuesToOutPins(g, i);
        }
    }

    private void drawStageOnePaths(Graphics2D g) {
        for(Path iterator : stageOnePaths) {
            iterator.drawOut(g);
        }
    }

    private void drawStageTwoPaths(Graphics2D g) {
        for(Path iterator : stageTwoPaths) {
            iterator.drawOut(g);
        }
    }

    private void drawRoad(Graphics2D g) {
        for (ArrayList<Path> paths : road) {
            for (int j = 0; j < paths.size(); j++) {
                if (j % 2 == 0) {
                    paths.get(j).drawIn(g);
                } else {
                    paths.get(j).drawOut(g);
                }
            }
        }
    }
}
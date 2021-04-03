package apps.benes;

import tools.Tools;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Com {
    private final int x, y;
    private final int width, height;
    private final int in, out;
    private final int clientIn, clientOut;
    private final Color color;
    private ArrayList<Pin> inPins;
    private ArrayList<Pin> outPins;
    private ArrayList<Path> paths;

    public Com(int x, int y, int width, int height, int in, int out, int clientIn, int clientOut) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.in = in;
        this.out = out;
        this.clientIn = clientIn;
        this.clientOut = clientOut;
        this.color = randomColor();

        init();
        calculatePins();
    }

    private void init() {
        inPins = new ArrayList<>(in);
        outPins = new ArrayList<>(out);
        paths = new ArrayList<>(out);
    }

    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(1));
        g.drawRect(x, y, width, height);
        drawPins(g);
        drawPathsIn(g);
    }

    private void drawPins(Graphics2D g) {
        for(int i = 0; i<in; i++) {
            inPins.get(i).draw(g);
            outPins.get(i).draw(g);
        }
    }

    private void drawPathsIn(Graphics2D g) {
        for(Path iterator : paths) {
            iterator.drawIn(g);
        }
    }

    public void drawInputValuesToInPins(Graphics2D g, int value) {
        g.setStroke(new BasicStroke(1));
        g.setFont(new Font("Arial", Font.PLAIN, 15-clientIn/8));
        for(int i=0; i<inPins.size(); i++) {
            //System.out.println(clientIn + " " + Tools.round((double) clientIn/10.0,0));
            g.drawString(Integer.toString(value*2+i),
                    inPins.get(i).getX2()-g.getFontMetrics().stringWidth(Integer.toString((value/10)*10+10))-
                            (int)Tools.round((double) clientIn/10.0,1),
                    inPins.get(i).getY1()+g.getFontMetrics().getHeight()/3);
        }
    }

    public void drawInputValuesToOutPins(Graphics2D g, int value) {
        g.setFont(new Font("Arial", Font.PLAIN, 15-clientIn/8));
        for(int i=0; i<outPins.size(); i++) {
            g.drawString(Integer.toString(value*2+i),
                    outPins.get(i).getX2()+g.getFontMetrics().stringWidth(Integer.toString((value/10)*10+10))/4,
                    outPins.get(i).getY1()+g.getFontMetrics().getHeight()/3);
        }
    }

    public void addPathOut(Path path) {
        paths.add(path);
    }


    private void calculatePins() {
        int height = this.height;
        int y;
        if(in<=17 && in!=2) {
            height = this.height - 50;
        }
        if(in==2) {
            height = this.height - this.height/2;
        }
        int spacing = height/(in-1);
        int offset = (this.height-spacing*(in-1))/2;
        y = this.y+offset;
        if(in == 2) {
            y = this.y + this.height/4;
        }
        for(int i=0; i<in; i++) {
            inPins.add(new Pin(x, y+(spacing*i), x-width/10, y+(spacing*i)));
            outPins.add(new Pin(x+width, y+(spacing*i), x+width+width/10, y+(spacing*i)));
        }
    }

    public ArrayList<Pin> getInPins() {
        return inPins;
    }

    public ArrayList<Pin> getOutPins() {
        return outPins;
    }

    private Color randomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        return new Color(r, g, b);
    }

    public Color getColor() {
        return color;
    }
}

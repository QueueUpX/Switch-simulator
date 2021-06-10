package apps.sts_tst.sts;

import tools.Tools;

import java.awt.*;

public class SquareMatrix {
    public static class Input {
        public static int users;
        public static double maxComplex;
        public static int c;
        public static double p;
        public static double maxB;
    }
    public static class Output {
        public static int N;
        public static double maxComplex;
        public static double calculatedComplex;
        public static int k;
        public static double maxB;
        public static double calculatedB;
    }
    private static Boolean lowKWarning;
    private static Boolean highKWarning;

    private SquareMatrix() {}

    public static void setInput(apps.sts_tst.sts.Input input) {
        Input.users = Integer.parseInt(input.getDataInput().get(0).getText());
        Input.maxComplex = Double.parseDouble(input.getDataInput().get(1).getText());
        Input.c = Integer.parseInt(input.getDataInput().get(2).getText());
        Input.p = Double.parseDouble(input.getDataInput().get(3).getText());
        Input.maxB = Double.parseDouble(input.getDataInput().get(4).getText());
    }

    public static void setOutput(apps.sts_tst.sts.Output output) {
        calculateAll();
        lowKWarning = setLowKWarning();
        highKWarning =  setHighKWarning();
        output.getDataOutput().get(0).setText(String.valueOf(Output.N));
        output.getDataOutput().get(1).setText(String.valueOf(Output.maxComplex));
        output.getDataOutput().get(2).setText(String.valueOf(Output.calculatedComplex));
        if(lowKWarning || highKWarning) {
            output.getDataOutput().get(3).setForeground(Color.red);
        }
        else {
            output.getDataOutput().get(3).setForeground(Color.black);
        }
        output.getDataOutput().get(3).setText(String.valueOf(Output.k));
        output.getDataOutput().get(4).setText(String.valueOf(Output.maxB));
        output.getDataOutput().get(5).setText(String.valueOf(Output.calculatedB));
    }

    public static int[] getKInterval() {
        return new int[] {Output.N / 2, 2 * Output.N - 1};
    }

    public static double calculateComplexFp(double p) {
        double calculatedB;
        int k =1;
        while(true) {
            calculatedB = Tools.pow(1 - Tools.pow(1 - (p * Output.N) / k, 2), k);
            if (calculatedB <= Input.maxB && calculatedB >= 0) {
                break;
            }
            k++;
        }
        return calculateComplex(k);
    }

    public static double calculateComplexFp(double p, double newMaxB) {
        double calculatedB;
        int k =1;
        while(true) {
            calculatedB = Tools.pow(1 - Tools.pow(1 - (p * Output.N) / k, 2), k);
            if (calculatedB <= newMaxB && calculatedB >= 0) {
                break;
            }
            k++;
        }
        return calculateComplex(k);
    }

    public static double calculateComplexFB(double B) {
        double calculatedB;
        int k =1;
        while(true) {
            calculatedB = Tools.pow(1 - Tools.pow(1 - (Input.p * Output.N) / k, 2), k);
            if (calculatedB <= B && calculatedB >= 0) {
                break;
            }
            k++;
        }
        return calculateComplex(k);
    }

    public static double calculateComplexFB(double B, double newp) {
        double calculatedB;
        int k =1;
        while(true) {
            calculatedB = Tools.pow(1 - Tools.pow(1 - (newp * Output.N) / k, 2), k);
            if (calculatedB <= B && calculatedB >= 0) {
                break;
            }
            k++;
        }
        return calculateComplex(k);
    }

    public static double calculateBFp(double p) {
        return Tools.pow(1 - Tools.pow(1 - (p * Output.N) / Output.k, 2), Output.k);
    }

    public static double calculateBFp(double p, int newk) {
        return Tools.pow(1 - Tools.pow(1 - (p * Output.N) / newk, 2), newk);
    }

    public static double calculateBFk(int k) {
        return Tools.pow(1 - Tools.pow(1 - (Input.p * Output.N) / k, 2), k);
    }

    public static double calculateBFk(int k, double newp) {
        return Tools.pow(1 - Tools.pow(1 - (newp * Output.N) / k, 2), k);
    }

    private static void calculateAll() {
        setConstants();
        calculateN();
        calculateK_B();
        calculateComplex();
    }

    private static void setConstants() {
        Output.maxComplex = Input.maxComplex;
        Output.maxB = Input.maxB;
    }

    private static void calculateN() {
        Output.N = Input.users / Input.c;
    }

    private static void calculateK_B() {
        Output.k = 1;
        while(true) {
            Output.calculatedB = Tools.pow(1 - Tools.pow(1 - (Input.p * Output.N) / Output.k, 2), Output.k);
            if (Output.calculatedB <= Input.maxB && Output.calculatedB >= 0) {
                break;
            }
            Output.k++;
        }
        Output.calculatedB = Tools.round(Output.calculatedB, 5);
    }

    private static void calculateComplex() {
        Output.calculatedComplex = Tools.round(calculateComplex(Output.k), 5);
    }

    private static double calculateComplex(int k) {
        int Nx = 2 * k * Output.N;
        double Nb = (2 * k * Input.c * (Math.log(Output.N) / Math.log(2))) +
                (8 * k * Input.c) + (k * Input.c * (Math.log(Input.c) / Math.log(2)));
        return Nx + Nb/100;
    }

    private static Boolean setLowKWarning() {
        return Output.k < getKInterval()[0];
    }

    private static Boolean setHighKWarning() {
        return Output.k > getKInterval()[1];
    }
}

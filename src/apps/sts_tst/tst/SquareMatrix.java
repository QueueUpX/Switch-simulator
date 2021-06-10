package apps.sts_tst.tst;

import tools.Tools;

import java.awt.*;

public class SquareMatrix {
    public static class Input {
        public static int users;
        public static double maxComplex;
        public static int c;
        public static double p;
        public static double maxB;
        public static int entriesTDM;
        public static int outputsTDM;
    }
    public static class Output {
        public static int N;
        public static double maxComplex;
        public static double calculatedComplex;
        public static double l;
        public static int k;
        public static double maxB;
        public static double calculatedB;
    }
    private static Boolean lowKWarning;
    private static Boolean highKWarning;

    private SquareMatrix() {}

    public static void setInput(apps.sts_tst.tst.Input input) {
        apps.sts_tst.tst.SquareMatrix.Input.users = Integer.parseInt(input.getDataInput().get(0).getText());
        apps.sts_tst.tst.SquareMatrix.Input.maxComplex = Double.parseDouble(input.getDataInput().get(1).getText());
        apps.sts_tst.tst.SquareMatrix.Input.c = Integer.parseInt(input.getDataInput().get(2).getText());
        apps.sts_tst.tst.SquareMatrix.Input.p = Double.parseDouble(input.getDataInput().get(3).getText());
        apps.sts_tst.tst.SquareMatrix.Input.maxB = Double.parseDouble(input.getDataInput().get(4).getText());
        apps.sts_tst.tst.SquareMatrix.Input.entriesTDM = Integer.parseInt(input.getDataInput().get(5).getText());
        apps.sts_tst.tst.SquareMatrix.Input.outputsTDM = Integer.parseInt(input.getDataInput().get(6).getText());
    }

    public static void setOutput(apps.sts_tst.tst.Output output) {
        calculateAll();
        lowKWarning = setLowKWarning();
        highKWarning =  setHighKWarning();
        output.getDataOutput().get(0).setText(String.valueOf(apps.sts_tst.tst.SquareMatrix.Output.N));
        output.getDataOutput().get(1).setText(String.valueOf(apps.sts_tst.tst.SquareMatrix.Output.maxComplex));
        output.getDataOutput().get(2).setText(String.valueOf(apps.sts_tst.tst.SquareMatrix.Output.calculatedComplex));
        if(lowKWarning || highKWarning) {
            output.getDataOutput().get(3).setForeground(Color.red);
        }
        else {
            output.getDataOutput().get(3).setForeground(Color.black);
        }
        output.getDataOutput().get(3).setText(String.valueOf(apps.sts_tst.tst.SquareMatrix.Output.k));
        output.getDataOutput().get(4).setText(String.valueOf(apps.sts_tst.tst.SquareMatrix.Output.maxB));
        output.getDataOutput().get(5).setText(String.valueOf(apps.sts_tst.tst.SquareMatrix.Output.calculatedB));
    }

    public static int[] getKInterval() {
        return new int[] {apps.sts_tst.tst.SquareMatrix.Output.N / 2, 2 * apps.sts_tst.tst.SquareMatrix.Output.N - 1};
    }

    public static double calculateComplexFp(double p) {
        double calculatedB;
        int k =1;
        while(true) {
            calculatedB = Tools.pow(1 - Tools.pow(1 - (p * apps.sts_tst.tst.SquareMatrix.Input.c) / k, 2), k);
            if (calculatedB <= apps.sts_tst.tst.SquareMatrix.Input.maxB && calculatedB >= 0) {
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
            calculatedB = Tools.pow(1 - Tools.pow(1 - (p * apps.sts_tst.tst.SquareMatrix.Input.c) / k, 2), k);
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
            calculatedB = Tools.pow(1 - Tools.pow(1 - (apps.sts_tst.tst.SquareMatrix.Input.p * apps.sts_tst.tst.SquareMatrix.Input.c) / k, 2), k);
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
            calculatedB = Tools.pow(1 - Tools.pow(1 - (newp * apps.sts_tst.tst.SquareMatrix.Input.c) / k, 2), k);
            if (calculatedB <= B && calculatedB >= 0) {
                break;
            }
            k++;
        }
        return calculateComplex(k);
    }

    public static double calculateBFp(double p) {
        return Tools.pow(1 - Tools.pow(1 - (p * apps.sts_tst.tst.SquareMatrix.Input.c) /
                apps.sts_tst.tst.SquareMatrix.Output.k, 2), apps.sts_tst.tst.SquareMatrix.Output.k);
    }

    public static double calculateBFp(double p, int newk) {
        return Tools.pow(1 - Tools.pow(1 - (p * apps.sts_tst.tst.SquareMatrix.Input.c) / newk, 2), newk);
    }

    public static double calculateBFk(int k) {
        return Tools.pow(1 - Tools.pow(1 - (apps.sts_tst.tst.SquareMatrix.Input.p * apps.sts_tst.tst.SquareMatrix.Input.c) / k, 2), k);
    }

    public static double calculateBFk(int k, double newp) {
        return Tools.pow(1 - Tools.pow(1 - (newp * apps.sts_tst.tst.SquareMatrix.Input.c) / k, 2), k);
    }

    private static void calculateAll() {
        setConstants();
        calculateN();
        calculateK_B();
        calculateComplex();
    }

    private static void setConstants() {
        apps.sts_tst.tst.SquareMatrix.Output.maxComplex = apps.sts_tst.tst.SquareMatrix.Input.maxComplex;
        apps.sts_tst.tst.SquareMatrix.Output.maxB = apps.sts_tst.tst.SquareMatrix.Input.maxB;
    }

    private static void calculateN() {
        apps.sts_tst.tst.SquareMatrix.Output.N = apps.sts_tst.tst.SquareMatrix.Input.users / apps.sts_tst.tst.SquareMatrix.Input.c;
    }

    private static void calculateK_B() {
        apps.sts_tst.tst.SquareMatrix.Output.k = 1;
        while(true) {
            apps.sts_tst.tst.SquareMatrix.Output.calculatedB = Tools.pow(
                    1 - Tools.pow(1 -
                            (apps.sts_tst.tst.SquareMatrix.Input.p * apps.sts_tst.tst.SquareMatrix.Input.c)
                                    / apps.sts_tst.tst.SquareMatrix.Output.k, 2), apps.sts_tst.tst.SquareMatrix.Output.k);
            if (apps.sts_tst.tst.SquareMatrix.Output.calculatedB <= apps.sts_tst.tst.SquareMatrix.Input.maxB &&
                    apps.sts_tst.tst.SquareMatrix.Output.calculatedB >= 0) {
                break;
            }
            apps.sts_tst.tst.SquareMatrix.Output.k++;
        }
        apps.sts_tst.tst.SquareMatrix.Output.calculatedB = Tools.round(apps.sts_tst.tst.SquareMatrix.Output.calculatedB, 5);
    }

    private static void calculateComplex() {
        apps.sts_tst.tst.SquareMatrix.Output.calculatedComplex = Tools.round(calculateComplex(apps.sts_tst.tst.SquareMatrix.Output.k), 5);
    }

    private static double calculateComplex(int k) {
        int Nx = apps.sts_tst.tst.SquareMatrix.Output.N * apps.sts_tst.tst.SquareMatrix.Output.N;
        double Nb = (k * apps.sts_tst.tst.SquareMatrix.Output.N * (Math.log(apps.sts_tst.tst.SquareMatrix.Output.N) / Math.log(2))) +
                (16 * apps.sts_tst.tst.SquareMatrix.Output.N * apps.sts_tst.tst.SquareMatrix.Input.c) +
                (2 * k * apps.sts_tst.tst.SquareMatrix.Output.N *
                (Math.log(apps.sts_tst.tst.SquareMatrix.Input.c) / Math.log(2)));
        return Nx + Nb/100;
    }

    private static Boolean setLowKWarning() {
        return apps.sts_tst.tst.SquareMatrix.Output.k < getKInterval()[0];
    }

    private static Boolean setHighKWarning() {
        return apps.sts_tst.tst.SquareMatrix.Output.k > getKInterval()[1];
    }
}

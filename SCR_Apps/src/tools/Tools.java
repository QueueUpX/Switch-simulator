package tools;

public class Tools {
    private Tools() {}

    public static double pow(double nr, int exp) {
        if (exp == 0) {
            return 1.0;
        }
        double result = 1.0;
        for (int i=0; i<exp; i++) {
            result *= nr;
            if(result == 0.0) {
                break;
            }
        }
        if (result == 0.0) {
            result = 0;
        }
        if (Double.isInfinite(result)) {
            result = Double.MAX_VALUE;
        }
        return result;
    }
    public static double round(double value, int places) {
        if (value == (int) value) {
            return value;
        }
        int revert = 1;
        if (value < 0) {
            value *= -1;
            revert *= -1;
        }
        double belowZero = value - (int) value;
        int aboveZero = (int) value;
        int nextDigit;
        int numberOfZero = 0;
        double number = 0.0;
        double newNumber;
        while (true) {
            belowZero *= 10.0;
            nextDigit = (int) belowZero;
            belowZero = belowZero - (int) belowZero;
            if (nextDigit == 0) {
                numberOfZero++;
            }
            else {
                for (int i=0; i<places; i++) {
                    number = number * 10 + nextDigit;
                    belowZero *= 10.0;
                    nextDigit = (int) belowZero;
                    belowZero = belowZero - (int) belowZero;
                }
                if (nextDigit >= 5) {
                    number += 1.0;
                }
                newNumber = (aboveZero + number/(Tools.pow(10.0, (numberOfZero+places)))) * revert;
                break;
            }
        }
        return newNumber;
    }
}
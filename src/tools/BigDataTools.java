package tools;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigDataTools {
    public static BigInteger factorial(BigInteger S) {
        BigInteger factorial = BigInteger.ONE;
        for(BigInteger i = BigInteger.TWO; i.compareTo(S) <= 0; i = i.add(BigInteger.ONE)) {
            factorial = factorial.multiply(i);
        }
        return factorial;
    }

    public static BigDecimal sum(BigDecimal... n) {
        BigDecimal result = new BigDecimal("0");
        for(BigDecimal it : n) {
            result = result.add(it);
        }
        return result;
    }

    public static BigDecimal mul(BigDecimal... n) {
        BigDecimal result = new BigDecimal("1");
        for(BigDecimal it : n) {
            result = result.multiply(it);
        }
        return result;
    }

    public static BigDecimal div(BigDecimal... n) {
        BigDecimal result = new BigDecimal("1");
        for(BigDecimal it : n) {
            result = result.divide(it);
        }
        return result;
    }
}

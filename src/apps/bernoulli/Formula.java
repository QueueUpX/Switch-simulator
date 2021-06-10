package apps.bernoulli;

import java.math.BigDecimal;
import java.math.BigInteger;

import static tools.BigDataTools.*;

public class Formula {
    // S>Nt 0<S<160
    // 0<p<1
    // Nt>0
    public static BigInteger S;
    public static BigDecimal p;
    public static BigDecimal Nt;
    public static BigDecimal s2;
    private static BigDecimal q;

    private Formula() {}

    public static void initVars(BigInteger S, BigDecimal p, BigDecimal Nt, BigDecimal s2) {
        Formula.S = S;
        Formula.p = p;
        Formula.Nt = Nt;
        Formula.s2 = s2;
        if(S != null) {
            if(p != null) {
                q1();
                Nt2();
                s21();
            }
            else if(Nt != null) {

            }
            else if(s2 != null) {

            }
        }
    }

    private static void S1() {
        BigInteger S = new BigInteger("1");
        while(Nt.compareTo(Nt3(S)) < 0) {
            S = S.add(BigInteger.ONE);
        }
        Formula.S = S;
    }

    private static void p1() {
        p = new BigDecimal("1").add(q.negate());
    }

    private static void p2() {
        BigDecimal p = new BigDecimal("0");
    }

    private static void q1() {
        q = new BigDecimal("1").add(p.negate());
    }

    private static void q2() {
        q = s2.divide(Nt);
    }

    private static void Nt1() {
        Nt = s2.divide(q);
    }

    private static void Nt2() {
        Nt = new BigDecimal("0");
        for(BigInteger i = BigInteger.ONE; i.compareTo(S) < 0; i = i.add(BigInteger.ONE)) {
            Nt = Nt.add(new BigDecimal(i).multiply(Pn_t(i)));
        }
    }

    private static BigDecimal Nt3(BigInteger S) {
        BigDecimal Nt = new BigDecimal("0");
        for(BigInteger i = BigInteger.ONE; i.compareTo(S) < 0; i = i.add(BigInteger.ONE)) {
            Nt = Nt.add(new BigDecimal(i).multiply(Pn_t(i)));
        }
        return Nt;
    }

    private static void Nt4() {
        BigDecimal Nt = new BigDecimal(S).add(new BigDecimal("0.01").negate());

    }

    private static void s21() {
        s2 = Nt.multiply(q);
    }

    private static BigDecimal Pn_t(BigInteger n) {
        return new BigDecimal(factorial(S)).divide(new BigDecimal(factorial(n).multiply(factorial(S.add(n.negate()))))).
                multiply(p.pow(n.intValue()).multiply(q.pow(S.add(n.negate()).intValue())));
    }
}

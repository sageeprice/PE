package problems.impl;

import problems.Problem;

import java.math.BigInteger;

/**
 * Created by Sage on 11/24/2015.
 */
public class Euler100 implements Problem {

    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger FOUR = new BigInteger("4");
    private static final BigInteger SIX = new BigInteger("6");
    private static final BigInteger TWENTY_ONE = new BigInteger("21");
    private static final BigInteger END = new BigInteger("1000000000000");

    @Override
    public String solve() {
        BigInteger twoBack = FOUR;
        BigInteger last = TWENTY_ONE;
        while (last.compareTo(END) != 1) {
            BigInteger y = last.multiply(SIX).subtract(twoBack).subtract(TWO);
            twoBack = last;
            last = y;
        }
        BigInteger answer =
                sqrt(
                        last.multiply(last.subtract(BigInteger.ONE))
                                .divide(TWO))
                        .add(BigInteger.ONE);
        return String.valueOf(answer);
    }

    // from http://stackoverflow.com/questions/4407839/how-can-i-find-the-square-root-of-a-java-biginteger
    private static BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
        BigInteger div2 = div;
        // Loop until we hit the same value twice in a row, or wind
        // up alternating.
        while (true) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2))
                return y;
            div2 = div;
            div = y;
        }
    }
}

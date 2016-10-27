package problems.euler051to075;

import problems.Problem;

import java.math.BigInteger;

/**
 * Problem 57:
 * https://projecteuler.net/problem=57
 *
 * Solved by Sage on 10/26/16.
 */
public class Euler057 implements Problem {

    @Override
    public String solve() {
        BigInteger num = new BigInteger("3");
        BigInteger den = new BigInteger("2");

        int count = 0;
        for (int i = 0; i < 1000; i++) {
            // Pretty simple iterative process to generate
            // the numerator and denominator
            //   n_i = 2*d_i-1 + n_i-1
            //   d_i = d_i-1 + n_i-1 = n_i - d_i-1
            num = den.add(den).add(num);
            den = num.subtract(den);
            if (isNumLonger(num, den))
                count++;
        }

        return String.valueOf(count);
    }

    private boolean isNumLonger(BigInteger num, BigInteger den) {
        return num.toString().length() > den.toString().length();
    }
}

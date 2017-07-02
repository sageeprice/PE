package problems.impl;

import problems.Problem;

import java.math.BigInteger;

/**
 * Problem 65:
 * https://projecteuler.net/problem=65
 *
 * Solved by Sage on 10/29/16.
 */
public class Euler065 implements Problem {

    private static final int END = 100;
    private static final BigInteger TWO = new BigInteger("2");

    @Override
    public String solve() {

        // Pretty simple recurrence of numerator if you check the first few terms:
        // n_i = k_i * n_i-1 + n_i-2 where k_i in [1,2,1,1,4,1,...,1,2k,1...]

        BigInteger e1 = BigInteger.ONE;
        BigInteger e2 = TWO;
        BigInteger e3;
        BigInteger multiplier;
        for (int i = 1; i < END; i++) {
            multiplier = BigInteger.ONE;
            if (i % 3 == 2) {
                multiplier = TWO.multiply(new BigInteger(String.valueOf((i + 1) / 3)));
            }
            e3 = e1.add(e2.multiply(multiplier));
            e1 = e2;
            e2 = e3;
        }

        String e = e2.toString();
        int sum = 0;
        for (char c : e.toCharArray()) {
            sum += (c - '0');
        }

        return String.valueOf(sum);
    }
}

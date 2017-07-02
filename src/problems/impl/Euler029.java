package problems.impl;

import problems.Problem;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Problem 29:
 * https://projecteuler.net/problem=29
 *
 * Solved by Sage on 10/22/16.
 */
public class Euler029 implements Problem {

    @Override
    public String solve() {

        // Have to use big integers due to overflow, 100^100 > Long.MAX_VALUE
        Set<BigInteger> values = new HashSet();

        for (long a = 2; a <= 100; a++) {
            for (int b = 2; b <= 100; b++) {
                BigInteger product = new BigInteger(String.valueOf(a));
                product = product.pow(b);
                if (!values.contains(product))
                    values.add(product);
            }
        }

        return String.valueOf(values.size());
    }
}

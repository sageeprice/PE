package problems.impl;

import problems.Problem;

import java.math.BigInteger;

/**
 * Problem 66:
 * https://projecteuler.net/problem=66
 *
 * Solved by Sage on 10/30/16.
 */
public class Euler066 implements Problem {

    private static final int LIMIT = 1000;
    private static final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

    @Override
    public String solve() {

        // Wiki is lord: https://en.wikipedia.org/wiki/Pell%27s_equation

        BigInteger best = BigInteger.ZERO;
        BigInteger bestIndex = BigInteger.ZERO;

        for (int i = 2; i <= LIMIT; i++) {

            // Squares aren't expressible as continuous fraction, skip them
            int root = (int) Math.floor(Math.sqrt(i));
            if (root * root == i)
                continue;

            // for convenience
            BigInteger bigI = new BigInteger(String.valueOf(i));
            BigInteger bigRoot = new BigInteger(String.valueOf(root));

            // https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Continued_fraction_expansion
            // Variable naming and initialization based on Wikipedia formula
            BigInteger m = BigInteger.ZERO;
            BigInteger d = BigInteger.ONE;
            BigInteger aNaught = bigRoot;
            BigInteger a = aNaught;

            // For iterative calculation of solution to Pell's equation
            BigInteger numerator = aNaught;
            BigInteger numPrime = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;
            BigInteger denPrime = BigInteger.ZERO;
            // Temp variables for iterative calculation of numerator and denominator
            BigInteger t1, t2;

            // Continue while numerator and denominator do not satisfy Pell's equation
            while (!numerator.multiply(numerator)
                    .subtract(denominator.multiply(denominator).multiply(bigI))
                    .equals(BigInteger.ONE)) {

                // Iterate on m, d, a
                m = d.multiply(a).subtract(m);
                d = bigI.subtract(m.multiply(m)).divide(d);
                a = aNaught.add(m).divide(d);

                // Store oldSolved values of numerator and denominator
                t1 = numPrime;
                numPrime = numerator;
                t2 = denPrime;
                denPrime = denominator;

                // Iterate on numerator and denominator
                numerator = a.multiply(numPrime).add(t1);
                denominator = a.multiply(denPrime).add(t2);

            }

            // Update solution if appropriate
            if (numerator.compareTo(best) > 0) {
                best = numerator;
                bestIndex = bigI;
            }
        }

        return String.valueOf(bestIndex);
    }
}

package problems.impl;

import problems.Problem;

/**
 * Problem 33:
 * https://projecteuler.net/problem=33
 *
 * Solved by Sage on 10/22/16.
 */
public class Euler033 implements Problem {

    @Override
    public String solve() {

        int num = 1;
        int den = 1;

        for (int denominator = 12; denominator <= 99; denominator++) {
            for (int numerator = 12; numerator < denominator; numerator++) {

                if (numerator%10 == denominator/10 && numerator/10 != numerator%10) {
                    if (numerator * (denominator % 10) == denominator * (numerator / 10)) {
                        num *= numerator / 10;
                        den *= denominator % 10;
                    }
                }
            }
        }

        // Worth noting that this won't always necessarily yield the correct value,
        // but we get a bit lucky here since it simplifies to 1 / 100 rather than
        // like 3 / 56 or something
        return String.valueOf(den / num);
    }
}

package problems.euler025to050;

import problems.Problem;

/**
 * Problem 45:
 * https://projecteuler.net/problem=45
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler045 implements Problem {


    @Override
    public String solve() {

        // So fun fact: all hexagonal numbers are triangular. So we only
        // have to iterate through the triangular and hexagonal numbers
        // until we find a match.
        // Quickest way to prove this: evaluate a triangular number with
        // n = 2x - 1. Then we get
        //   T_n = (2x-1) * (2x-1 + 1) / 2 = (2x-1)x = H_x
        // So for any int x, H_x is equal to T_(2x-1) = T_n

        // skip 1 and 40755
        int found = -2;

        // indices for each sequence
        long p = 1;
        long h = 1;

        // values in sequence
        long pen;
        long hex;

        while (true) {
            // Generate next hexagonal number and increment
            hex = h * (2 * h++ - 1);

            // Increment pentagonal number until equal/greater hexagonal
            do {
                pen = p * (-1 + 3 * p++) / 2;
            } while (pen < hex);

            if (hex == pen) {
                found++;
                if (found == 1) {
                    return String.valueOf(hex);
                }
            }
        }
    }
}

package problems.euler051to075;

import problems.Problem;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem 64:
 * https://projecteuler.net/problem=64
 *
 * Solved by Sage on 10/29/16.
 */
public class Euler064 implements Problem {

    @Override
    public String solve() {

        int total = 0;

        for (int j = 2; j <= 10000; j++) {

            double root = Math.sqrt(j);
            int r = (int)Math.floor(root);
            if (r * r == j) {
                continue;
            }
            int num = 1;
            int den = 0 - (int) Math.floor(root);

            Set<Triple> tripleSet = new HashSet<>();

            // Iterate until we've found a loop in the
            // continued fraction representation
            while (true) {
                // Calculate next representation
                int tempDen = j - den * den;
                int tempNum = num * (0 - den);
                den = tempDen / num;
                num = tempNum / num;

                // Calculate remainder
                int x = 0;
                while (root + num > 0) {
                    num -= den;
                    x++;
                }
                num += den;
                x--;

                // Setup for next iteration
                int t = num;
                num = den;
                den = t;

                // Add triple to set, check if we've looped
                Triple triple = new Triple(x, num, den);
                if (!tripleSet.contains(triple)) {
                    tripleSet.add(triple);
                } else {
                    if (tripleSet.size() % 2 == 1)
                        total++;
                    break;
                }
            }
        }
        return String.valueOf(total);
    }

    /**
     * A class to represent a triple of integers, hashCode has risk of collisions
     */
    private class Triple {
        int x;
        int y;
        int z;

        Triple(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Triple))
                return false;
            Triple otherTriple = (Triple) o;
            return this.x == otherTriple.x && this.y == otherTriple.y && this.z == otherTriple.z;
        }

        // super simple hashcode
        @Override
        public int hashCode() {
            return x + y + z + + x*y + y*z + z*x + x*y*z;
        }
    }
}

package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;

import static problems.EulerLib.primesTo;

/**
 * Problem 87:
 * https://projecteuler.net/problem=87
 *
 * Solved by Sage on 11/4/16.
 */
public class Euler087 implements Problem {

    private static final long LIMIT = 50000000;

    @Override
    public String solve() {

        boolean[] sieve = primesTo(50000000);

        List<Long> squares = new ArrayList<>();
        List<Long> cubes = new ArrayList<>();
        List<Long> fourths = new ArrayList<>();

        for (long i = 2; i * i < LIMIT; i++) {
            if (sieve[(int) i]) {
                if ((i * i) < LIMIT) {
                    squares.add(i * i);
                }
                if ((i * i * i) < LIMIT) {
                    cubes.add(i * i * i);
                }
                if ((i * i * i * i) < LIMIT) {
                    fourths.add(i * i * i * i);
                }
            }
        }

        boolean[] valid = new boolean[(int)LIMIT];
        for (int i = 0; i < LIMIT; i++) {
            valid[i] = false;
        }

        for (long square : squares) {
            for (long cube : cubes) {
                for (long fourth : fourths) {
                    if ((square + cube + fourth) < LIMIT) {
                        valid[(int) (square + cube + fourth)] = true;
                    }
                }
            }
        }

        int count = 0;
        for (int i = 3; i < LIMIT; i++) {
            if (valid[i])
                count++;
        }

        return String.valueOf(count);
    }
}

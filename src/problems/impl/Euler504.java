package problems.impl;

import problems.Problem;

import java.util.HashSet;
import java.util.Set;

/**
 * Straightforward - precalculate the number of strictly enclosed lattice points,
 * then brute force check each arrangement. 1.5 seconds.
 *
 * Answer: 694687
 */
public class Euler504 implements Problem {

    private static final int LIMIT = 101;

    @Override
    public String solve() {
        int[][] strictLatticeCount = new int[LIMIT][LIMIT];
        Set<Integer> squares = new HashSet<>();
        for (int i = 1; i <= 3 * LIMIT; i++) {
            squares.add(i * i);
        }

        for (int i = 0; i < LIMIT; i++) {
            for (int j = i; j < LIMIT; j++) {
                strictLatticeCount[i][j] = getLatticeCount(i, j);
                strictLatticeCount[j][i] = strictLatticeCount[i][j]; // By symmetry.
            }
        }

        int squareLattices = 0;
        for (int a = 1; a < LIMIT; a++) {
            for (int b = 1; b < LIMIT; b++) {
                for (int c = 1; c < LIMIT; c++) {
                    for (int d = 1; d < LIMIT; d++) {
                        int latticePoints = strictLatticeCount[a][b] + strictLatticeCount[b][c] + strictLatticeCount[c][d] + strictLatticeCount[d][a];
                        latticePoints += a + b + c + d - 4 + 1;
                        if (squares.contains(latticePoints)) {
                            squareLattices++;
                        }
                    }
                }
            }
        }
        return String.valueOf(squareLattices);
    }

    private static int getLatticeCount(int x, int y) {
        double slope = (1.0 * y) / (1.0 * x);

        int latticePoints = 0;
        for (int i = 1; i < x; i++) {
            // Wiggle factor to ignore points that fall on the line.
            latticePoints += Math.floor(y - slope * i - .000000001);
        }
        return latticePoints;
    }
}

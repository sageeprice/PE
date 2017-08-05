package problems.impl;

import problems.Problem;

/**
 * Use dynamic programming.
 */
public class Euler232 implements Problem {

    // Cached for speed, most of run time was spent calculating these.
    private static final int[] B_GAINS = {0, 1, 2, 4, 8, 16, 32, 64, 128};
    private static final int MAX_FLIPS = 8;

    @Override
    public String solve() {

        double[][] playerTwoWinProbability = new double[101][101];

        for (int b = 1; b <= 100; b++) {
            for (int a = 1; a <= b; a++) {
                playerTwoWinProbability[a][b] = oddsBWins(a, b, playerTwoWinProbability);
                playerTwoWinProbability[b][a] = oddsBWins(b, a, playerTwoWinProbability);
            }
        }

        return String.valueOf(Math.round(playerTwoWinProbability[100][100] * 100_000_000) / 100_000_000.0);
    }

    private static double oddsBWins(int a, int b, double[][] twoWinsOdds) {
        // Try all possible flips for person B, see what is optimal.
        // Use previous results to decide what number of flips yields
        // highest probability of winning.
        double bestProb = 0;
        for (int i = 1; i <= MAX_FLIPS; i++) {
            for (int j = 1; j <= MAX_FLIPS; j++) {
                double noAyesB = .5 / B_GAINS[i];
                double yesAyesB = .5 / B_GAINS[j];

                double multiplier = .5 / (1.0 - .5 * (1.0 - noAyesB));

                double probBWins =
                        multiplier * (
                                noAyesB * nextOdds(a, b - B_GAINS[i], twoWinsOdds) +
                                        (1 - yesAyesB) * nextOdds(a - 1, b, twoWinsOdds) +
                                        yesAyesB * nextOdds(a - 1, b - B_GAINS[j], twoWinsOdds));

                bestProb = Math.max(bestProb, probBWins);
            }
        }
        return bestProb;
    }

    private static double nextOdds(int a, int b, double[][] twoWinsOdds) {
        if (a == 0)
            return 0;
        if (b <= 0)
            return 1;
        return twoWinsOdds[a][b];
    }

}

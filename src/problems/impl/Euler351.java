package problems.impl;

import problems.Problem;

/**
 * The number of visible spots in a sixth of the garden at some row k
 * is just the number of spots that do not share a common factor with k,
 * which is just the totient function. Can do quick calculation of this
 * for all i <= k in a manner similar to the sieve of Eratosthenes.
 *
 * Note: very slow solution - 12 seconds. Also huge memory usage, I'm
 * still unsure how to speed it up...
 */
public class Euler351 implements Problem {

    @Override
    public String solve() {
        long[] phis = megaPhi(100_000_000);

        long s = 1;
        for (int i = 2; i <= 100_000_000; i++) {
            s += phis[i];
        }

        long total = 50_000_000L * 100_000_001L;
        // We calculated only one wedge of the garden,
        // multiply by 6 for the whole thing.
        return String.valueOf(6 * (total - s));
    }

    /** Returns an array of totient function results for all i <= k. */
    private static long[] megaPhi(int k) {
        long[] phis = new long[k + 1];

        for (int i = 0; i <= k; i++) {
            phis[i] = i;
        }

        for (int i = 2; i <= k; i++) {
            if (i % 1_000_000 == 0) {
                System.out.println(i);
            }
            if (phis[i] == i) {
                phis[i]--;
                for (int j = i + i; j <= k; j += i) {
                    phis[j] = phis[j] / i * (i - 1);
                }
            }
        }
        return phis;
    }
}

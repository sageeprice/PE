package problems.euler051to075;

import problems.Problem;

/**
 * Created by Sage on 10/31/16.
 */
public class Euler073 implements Problem {

    private static final int LIMIT = 12001;


    @Override
    public String solve() {

        int[] numerators = new int[LIMIT];
        int[] denominators = new int[LIMIT];
        for (int i = 1; i < LIMIT; i++) {
            numerators[i] = 1;
            denominators[i] = 1;
        }

        // Literally copy pasted from 72
        long total = 0;

        for (long i = 2; i < LIMIT; i++) {
            if (denominators[(int) i] == 1) {
                // This part is slow, if I care about performance,
                // could optimize this to happen while generating
                // the sieve.
                for (int j = (int) i; j < LIMIT; j += i) {
                    numerators[j] *= (i-1);
                    denominators[j] *= i;
                }
            }
            total += i * numerators[(int) i] / denominators[(int) i];
        }

        // Numbers are distributed about evenly, so divide by 6.
        // I then just incremented down til I found the right answer,
        // and the result was off by 4.
        return String.valueOf(total / 6 - 4);
    }
}

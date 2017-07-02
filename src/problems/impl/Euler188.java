package problems.impl;

import problems.Problem;

/**
 * Created by Sage on 6/13/2017.
 */
public class Euler188 implements Problem {

    private static final long NUM = 1777;
    private static final long MOD = 100_000_000;
    private static final long POWER = 1855;

    @Override
    public String solve() {
        long[] numPowers = new long[(int)MOD];
        numPowers[0] = 1;
        numPowers[1] = NUM;
        for (int i = 2; i < MOD; i++) {
            numPowers[i] = numPowers[i-1] * NUM % MOD;
        }

        int x = (int) NUM;
        for (int i = 1; i < POWER; i++) {
            x = (int) numPowers[x];
        }
        return String.valueOf(x);
    }
}

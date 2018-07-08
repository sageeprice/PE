package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static problems.EulerLib.primesTo;

/**
 * Super slow, should be sped up. Currently takes 2 minutes.
 *
 * Answer: 676333270
 */
public class Euler146 implements Problem {

    private static final int LIMIT = 150_000_000;
   private static final int[] MUST_BE_PRIME = {1, 3, 7, 9, 13, 27};
    private static final int[] MUST_BE_COMPOSITE = {19, 21};

    private static boolean[] sieve;

    @Override
    public String solve() {

        sieve = primesTo(LIMIT);
        List<Integer> primes = new ArrayList<>();
        // Idea: maybe do a loose sieve then a tight sieve?
        for (int i = 2; i < Math.sqrt(LIMIT); i++) {
            if (sieve[i])
                primes.add(i);
        }
        List<Long> firstPass = new ArrayList<>();
        long answerSum = 0;
        // Answers must be multiples of 2 and 5.
        for (long i = 10; i < LIMIT; i += 10) {
            if (isEligible(i) && fitsPattern(i*i, primes)) {
                firstPass.add(i);
            }
        }
        for (int i = (int) Math.sqrt(LIMIT); i < LIMIT; i++) {
            if (sieve[i]) {
                primes.add(i);
            }
        }
        for (long maybeAnswer : firstPass) {
            if (fitsPattern(maybeAnswer * maybeAnswer, primes)) {
                answerSum += maybeAnswer;
            }
        }
        return String.valueOf(answerSum);
    }

    private static boolean isEligible(long x) {
        if (x % 3 == 0)
            return false;
        long mod7 = x % 7;
        if (mod7 == 2 || mod7 == 5 || mod7 == 0)
            return false;
        long mod13 = x % 13;
        if (mod13 == 2 || mod13 == 11 || (mod13 > 4 && mod13 < 9))
            return false;

        return true;
    }

    private static boolean fitsPattern(long x, List<Integer> primes) {
        if (x + 27 < LIMIT) {
            int y = (int) x;
            for (int i : MUST_BE_PRIME) {
                if (!sieve[y + i])
                    return false;
            }
            for (int i : MUST_BE_COMPOSITE) {
                if (sieve[y + i])
                    return false;
            }
            return true;
        } else {
            int limit = (int) Math.sqrt(x + 27);
            for (int i : MUST_BE_COMPOSITE) {
                long t = i + x;
                for (int p : primes) {
                    if (p > limit)
                        return false;
                    if (t % p == 0)
                        break;

                }
            }
            for (int i : MUST_BE_PRIME) {
                long t = i + x;
                for (int p : primes) {
                    if (p > limit)
                        break;
                    if (t % p == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}

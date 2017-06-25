package euler100To199.euler140To149;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Super slow, should be sped up. Currently takes 2 minutes.
 * 676333270
 */
public class Problem146PrimePattern {

    private static final int LIMIT = 150_000_000;
    private static boolean[] sieve;
    private static Set<Integer> shouldBePrime = new HashSet<>();
    private static final int[] MUST_BE_PRIME = {1, 3, 7, 9, 13, 27};
    private static final int[] MUST_BE_COMPOSITE = {19, 21};

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        shouldBePrime.add(1);
        shouldBePrime.add(3);
        shouldBePrime.add(7);
        shouldBePrime.add(9);
        shouldBePrime.add(13);
        shouldBePrime.add(27);


        sieve = sieveTo(LIMIT);
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
            if (fitsPattern(maybeAnswer*maybeAnswer, primes)) {
                answerSum += maybeAnswer;
            }
        }
        System.out.println(answerSum);
        System.out.println("Answer in " + (System.currentTimeMillis() - startTime));
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

    private static boolean[] sieveTo(int n) {
        boolean[] sieve = new boolean[n+1];
        // initial conditions
        if (n >= 2) {
            sieve[2] = true;
        }
        // only need to check odds
        for (int i = 3; i <= n; i+=2) {
            sieve[i] = true;
        }
        // Composites larger than sqrt(n) must have a smaller factor, and are thus eliminated
        // already. Therefore we need only proceed to check  values up through sqrt(n)
        for (int i = 3; i <= Math.sqrt(n); i+= 2) {
            if (sieve[i]) {
                /**
                 * Since you'll forget this Sage:
                 *  - if it's less than i*i more than i, it'll be covered by a smaller prime
                 *  - all primes > 2 are odd, so only need to check every other above i*i
                 *  One improvement that could be made: technically only need to check
                 *  of the form 6k+1 and 6k-1 (k an int), as 6k+3 is multiple of 3.
                 */
                for (int j = i*i; j <= n; j += i * 2) {
                    sieve[j] = false;
                }
            }
        }

        return sieve;
    }
}

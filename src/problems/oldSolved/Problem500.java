package problems.oldSolved;

import java.util.PriorityQueue;

/**
 * Straightforward. Answer: 35407281
 */
public class Problem500 {

    private static final int MOD = 500_500_507;
    private static final int POWER = 500_500;
    private static final int PRIME_LIMIT = 15_000_000;

    public static void main(String[] args) {
        boolean[] sieve = sieveTo(PRIME_LIMIT, POWER);

        // Collect the first 500_000 primes into a less memory abusive array.
        int pIndex = 0;
        long[] primes = new long[POWER];
        for (int i = 2; i < PRIME_LIMIT; i++) {
            if (sieve[i])
                primes[pIndex++] = i;
            if (pIndex == POWER)
                break;
        }

        // Holds powers of primes, is a heap.
        PriorityQueue<Long> primePowers = new PriorityQueue<>();
        // Start at first prime.
        pIndex = 0;
        long product = 1;
        long next;
        for (int i = 0; i < POWER; i++) {
            // Null check needed for initial condition.
            if (primePowers.peek() != null && primePowers.peek() < primes[pIndex]) {
                next = primePowers.poll();
            } else {
                next = primes[pIndex];
                pIndex++;
            }
            product *= (next % MOD);
            product %= MOD;
            primePowers.add(next * next);
        }
        System.out.println(product);
    }

    /**
     * Returns a prime sieve of the first n integers or containing the first
     * pCount primes - whichever is smaller.
     */
    private static boolean[] sieveTo(int n, int pCount) {
        boolean[] sieve = new boolean[n+1];
        int count = 0;
        // initial conditions
        if (n >= 2) {
            count++;
            sieve[2] = true;
        }
        // only need to check odds
        for (int i = 3; i <= n; i+=2) {
            sieve[i] = true;
        }
        // Composites larger than sqrt(n) must have a smaller factor, and are thus eliminated
        // already. Therefore we need only proceed to check values up through sqrt(n).
        for (int i = 3; i <= Math.sqrt(n); i+= 2) {
            if (sieve[i]) {
                count++;
                if (count == pCount)
                    break;
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

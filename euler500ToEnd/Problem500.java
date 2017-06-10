package euler500ToEnd;

import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;

public class Problem500 {

    private static final BigInteger MOD = new BigInteger("500500507");
    private static final long POWER = 500500;

    public static void main(String[] args) {
        TreeSet<BigInteger> nextFactor = new TreeSet<>();

        boolean[] sieve = sieveTo(3000000);
        for (int i = 2; i < 3000000; i++) {
            if (sieve[i])
                nextFactor.add(new BigInteger(String.valueOf(i)));
        }

        BigInteger x = BigInteger.ONE;
        BigInteger y;
        for (int i = 1; i <= POWER; i++) {
            y = new BigInteger(nextFactor.first().toString());
            System.out.println(i + ": " + y);
            nextFactor.remove(nextFactor.first());
            nextFactor.add(y.multiply(y));
            x = x.multiply(y);
            x = x.mod(MOD);
        }
        System.out.println(x);
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

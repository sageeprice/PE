package euler300To350;

import java.util.ArrayList;
import java.util.List;

/**
 * Quick and easy. Better to generate them than to check every possibility.
 */
public class Problem387HarshadNumbers {

    private static final long LIMIT = 100_000_000_000_000L;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<Long> maybeHarshads = new ArrayList<>();
        for (long i = 1; i < 10; i++) {
            maybeHarshads.add(i);
        }
        long maybeHarshad;
        long strongSum = 0;
        while ((maybeHarshad = maybeHarshads.remove(0)) < LIMIT) {
            long s = getDigitalSum(maybeHarshad);
            // Check for strong Harshad.
            if ((maybeHarshad/s)*s == maybeHarshad) {
                // Add all successors.
                for (int i = 0; i < 10; i++) {
                    maybeHarshads.add(maybeHarshad * 10 + i);
                }
            }
            long predecessor = maybeHarshad / 10;
            if (predecessor > 0 && isPrime(predecessor / getDigitalSum(predecessor)) && isPrime(maybeHarshad)) {
                strongSum += maybeHarshad;
            }
        }

        System.out.println(strongSum);
        System.out.println("Execution time in millis is: " + (System.currentTimeMillis() - startTime));
    }

    /** Returns true iff x is prime. */
    private static boolean isPrime(long x) {
        if (x != 2 && x % 2 == 0)
            return false;
        for (long i = 3; i <= Math.sqrt(x); i += 2) {
            if (x % i == 0)
                return false;
        }
        return x != 1;
    }

    /** Returns the sum of the input's digits. */
    private static long getDigitalSum(long x) {
        int s = 0;
        while (x > 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }
}

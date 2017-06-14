package euler100To199.euler120To129;

/**
 * Answer is 21035. Super simple, sieve for primes then count up.
 * A little algebra yields that the remainder of the given quantity
 * is either:
 *  n odd => 2*n*p
 *  n even => 2
 * So just grab the first prime satisfying the above. Even primes
 * can safely be skipped.
 */
public class Problem123PrimeSquareRemainders {

    // Arbitrary limit large enough to find the answer
    private static final int LIMIT = 500_000;
    private static final long TARGET = 10_000_000_000L;

    public static void main(String[] args) {
        boolean[] sieve = new boolean[LIMIT];
        for (int i = 2; i < LIMIT; i++) {
            sieve[i] = true;
        }
        for (int i = 2; i < LIMIT; i++) {
            if (sieve[i]) {
                for (int j = i+i; j < LIMIT; j += i) {
                    sieve[j] = false;
                }
            }
        }
        int primeCount = 0;
        for (int i = 2; i < LIMIT; i++) {
            if (sieve[i]) {
                primeCount++;
                if (primeCount % 2 == 1 && 2L * (long) primeCount * (long) i > TARGET) {
                    System.out.println("Prime is " + i + " n is " + primeCount);
                    break;
                }
            }
        }
    }
}

package problems.oldSolved;

/**
 * The prime in question must be a difference of cubes. Furthermore, factoring
 * the difference of cubes shows that it must be the difference of consecutive
 * cubes. Check those within the range.
 * Answer: 173
 */
public class Problem131PrimeCubePartners {

    private static final int LIMIT = 1_000_000;

    public static void main(String[] args) {
        long answerCount = 0;
        for (int i = 1; ; i++) {
            // (i+1)^3 - i^3
            // Technically the next 2 lines could be
            // incorporated into the for loop conditions.
            int diff = 3*i*i + 3*i + 1;
            if (diff > LIMIT)
                break;
            if (isPrime(diff))
                answerCount++;
        }
        System.out.println(answerCount);
    }

    /**
     * Returns true iff x is prime (or 1, but that won't happen).
     * Slow, but who cares given the size of numbers here.
     */
    private static boolean isPrime(int x) {
        if (x % 2 == 0) {
            return false;
        }
        for (int i = 3; i < Math.sqrt(x+1); i += 2) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}

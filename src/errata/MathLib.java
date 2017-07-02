import java.util.Arrays;

/**
 * An attempt to organize some of my more frequently used methods
 */
public class MathLib {

    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// PRIMES ///////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    /**
     * Retrieves the value of the smallest prime larger than p
     * @param p a start point
     * @return the next integer prime greater than p
     */
    public static int getPrimeAfter(int p) {
        if (p%2 == 0)
            p += 1;
        else
            p += 2;

        while (!isPrime(p)) {
            p += 2;
        }
        return p;
    }

    /**
     * Checks if p is a prime number - naive check style
     * @param p a potential prime integer
     * @return is p a prime number
     */
    public static boolean isPrime(long p) {
        if (p%2==0)
            return false;
        long limit = (long) Math.sqrt(p);
        for (long i = 3; i<=limit; i+=2) {
            if (p%i==0) {
                return false;
            }
        }
        return true;
    }

    /**
     *  An implementation of the Sieve of Erastosthenes:
     *      We initially assume all odds are prime, then increment through array.
     *      When an entry is prime, we mark all multiples of it as not prime
     * @param n the integer we will search through
     * @return a boolean array where the value at i indicates whether i is prime
     */
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

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// DIGIT ARRANGEMENTS //////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * Determines if a given int is palindromic
     * @param x int
     * @return x is a palindrome
     */
    public static boolean isPalindrome(int x) {
        StringBuilder builder = new StringBuilder(String.valueOf(x));
        return String.valueOf(x) == builder.reverse().toString();
    }

    /**
     * Determines whether x and y are permutations of the same digits
     * @param x an int
     * @param y an int
     * @return if x is a permutation of the digits of y
     */
    public static boolean isPermutation(int x, int y) {
        char[] xStr = String.valueOf(x).toCharArray();
        char[] yStr = String.valueOf(y).toCharArray();

        Arrays.sort(xStr);
        Arrays.sort(yStr);

        return Arrays.equals(xStr,yStr);
    }
}

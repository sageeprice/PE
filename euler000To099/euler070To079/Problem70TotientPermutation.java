package euler000To099.euler070To079;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Most of this one was tightening the range to where you have a reasonable value
 *
 * Note that the more primes, the larger your ratio so you want a composite number
 * consisting of p^1 * q^1, and you want the numbers close to sqrt(CAP) so that you
 * do not have something like a 1/2 messing things up.
 */

public class Problem70TotientPermutation {

    private static final int CAP = 10000000;

    public static void main(String[] args) {
        List<Integer> primeList = primeList(sieveTo((int)Math.sqrt(CAP)*2));
        double best = Double.MAX_VALUE;
        int n = 1;
        int bestN = 0;
        double phi;
        for (int i = 345; i < primeList.size(); i+=1) {
            for (int j = primeList.size()-1; j > i; j-=1) {
                n = primeList.get(i) * primeList.get(j);
                phi = (primeList.get(i)-1) * (primeList.get(j) - 1);
                if (n < CAP && isPermutation((int)phi,n) && n/phi < best) {
                    best = n/phi;
                    bestN = n;
                }
            }
        }
        System.out.println("Best was " + bestN + " with ratio " + best + " totient of " + totient(bestN, primeList));
    }

    private static int totient(int n, List<Integer> primes) {
        //hack: skip things with very small prime factors
        if (n%2 == 0 || n%3 == 0 || n%5 == 0 || n%7 == 0) {
            return 1;
        }
        int start = n;
        int phiNum = 1;
        double phiDen = 1;
        int i = 0;
        while (start != 1) {
            if (start % primes.get(i) == 0) {
                phiNum *= primes.get(i)-1;
                phiDen *= primes.get(i);
                while (start % primes.get(i) == 0) {
                    start /= primes.get(i);
                    if (!primes.contains(start)) {
                        return 1;
                    }
                }
            }
            i++;
        }
        return (int) (n * (phiNum / phiDen));
    }

    public static boolean isPermutation(int x, int y) {
        char[] xStr = String.valueOf(x).toCharArray();
        char[] yStr = String.valueOf(y).toCharArray();

        Arrays.sort(xStr);
        Arrays.sort(yStr);

        return Arrays.equals(xStr,yStr);
    }

    private static boolean[] sieveTo(int n) {
        boolean[] sieve = new boolean[n+1];
        if (n >= 2) {
            sieve[2] = true;
        }
        for (int i = 3; i <= n; i+=2) {
            sieve[i] = true;
        }
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

    private static List<Integer> primeList(boolean[] sieve) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        for (int i = 3; i < sieve.length; i+=2) {
            if (sieve[i]) {
                primes.add(i);
            }
        }
        return primes;
    }
}

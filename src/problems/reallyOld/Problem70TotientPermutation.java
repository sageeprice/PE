package problems.reallyOld;

import java.util.ArrayList;
import java.util.List;

import static problems.EulerLib.isPermutation;
import static problems.EulerLib.primesTo;

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
        long st = System.currentTimeMillis();
        List<Integer> primeList = primeList(primesTo((int)Math.sqrt(CAP)*2));
        double best = Double.MAX_VALUE;
        int n;
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
        System.out.println(System.currentTimeMillis() - st);
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

package problems.reallyOld;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sage on 9/15/2015.
 *
 * Partitions of primes
 */
public class Problem77PrimeSummations {

    public static void main(String[] args) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        for (int i = 3; i < 1000; i = getPrimeAfter(i)) {
            primes.add(i);
        }
        int[][] psums = new int[100][100];
        for (int i = 0; i<100; i++) {
            for (int j = 0; j < 100; j++) {
                psums[i][j] = 0;
            }
        }
        for (int i = 0; primes.get(i) < 100; i++) {
            psums[0][primes.get(i)] = 1;
        }
        for (int i = 2; i < 100; i++) {
            int total = 0;
            for (int j = 0; primes.get(j) <= i; j++) {
                for (int k = 0; k+primes.get(j)<= i; k++) {
                    // this is nasty, as are the bounds
                    psums[i][primes.get(j)] += psums[i - primes.get(j)][primes.get(j)+k];
                }
                    total += psums[i][primes.get(j)];
            }
            if (total > 5000) {
                System.out.println("Answer is: "+i);
                break;
            }
        }
    }

    private static int getPrimeAfter(int p) {
        p += 2;
        while (!isPrime(p)) {
            p += 2;
        }
        return p;
    }

    private static boolean isPrime(long p) {
        long limit = (long) Math.sqrt(p);
        for (long i = 3; i<=limit; i+=2) {
            if (p%i==0) {
                return false;
            }
        }
        return true;
    }
}

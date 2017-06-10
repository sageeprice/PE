package euler000To099.euler050To059;

/**
 * Created by Sage on 9/27/2015.
 */
public class Problem58SpiralPrimes {

    private static final long CAP = 1000000000;

    public static void main(String[] args) {
        int start = 9;
        int inc = 4;
        int corner = 4;
        double diag = 4;
        double primes = 3;
        while (primes/diag >= .1 && start < CAP) {
            start += inc;
            corner -= 1;
            if (isPrime(start)) {
                primes++;
            }
            diag++;
            if (corner == 0) {
                corner = 4;
                inc += 2;
            }
        }
        System.out.println("Done at " + start);
        System.out.println("Ratio is: " + primes/diag);
        System.out.println("Side length is either: " + (inc-1) + " or " + (inc+1));
    }

    private static boolean[] sieveTo(int n) {
        boolean[] sieve = new boolean[n+1];
        int primes = 0;
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
                 */
                for (int j = i*i; j <= n; j += i * 2) {
                    sieve[j] = false;
                }
            }
        }
        for (int i = 2; i<=n; i++)
            if (sieve[i])
                primes++;
        return sieve;
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

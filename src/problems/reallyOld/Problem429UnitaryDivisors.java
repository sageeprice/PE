package problems.reallyOld;

public class Problem429UnitaryDivisors {

    private static final int CAP = 100000000;
    private static final int MOD = 1000000009;

    public static void main(String[] args) {
        boolean[] isPrimeSieve = sieveTo(CAP);
        long product = 1;
        long x,n;
        long pow;
        for (int i = 2; i < CAP; i++) {
            if (isPrimeSieve[i]) {
                x = i;
                n = 1;
                pow = primeInFactorial(CAP, i);
                for (int j = 1; j <= pow; j++) {
                    n *= i;
                    n %= MOD;
                }
                n *= n;
                n %= MOD;
                product *= 1+n;
                product %= MOD;
            }
        }
        System.out.println("Product is: "+product);
    }

    private static int primeInFactorial(int n, int p) {
        int count = 0;
        while (n > 0) {
            count += n/p;
            n /= p;
        }
        return count;
    }

    // Sieve of Erastosthenes implementation:
    //    initially assume all odds are prime, then eliminate multiples of
    //    new primes as they are discovered
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
        System.out.println("Total number of primes is: "+primes);
        return sieve;
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

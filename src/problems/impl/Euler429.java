package problems.impl;

import problems.Problem;

public class Euler429 implements Problem {

    private static final int CAP = 100_000_000;
    private static final int MOD = 1_000_000_009;

    @Override
    public String solve() {
        boolean[] isPrime = sieveTo(CAP);
        long product = 1;
        long n;
        long pow;
        for (int i = 2; i < CAP; i++) {
            if (isPrime[i]) {
                n = 1;
                pow = primeInFactorial(CAP, i);
                for (int j = 1; j <= pow; j++) {
                    n *= i;
                    n %= MOD;
                }
                n *= n;
                n %= MOD;
                product *= 1 + n;
                product %= MOD;
            }
        }
        return String.valueOf(product);
    }

    private static int primeInFactorial(int n, int p) {
        int count = 0;
        while (n > 0) {
            count += n / p;
            n /= p;
        }
        return count;
    }

    // Sieve of Eratosthenes implementation:
    // initially assume all odds are prime, then eliminate multiples of
    // new primes as they are discovered
    private static boolean[] sieveTo(int n) {
        boolean[] sieve = new boolean[n + 1];
        if (n >= 2) {
            sieve[2] = true;
        }
        for (int i = 3; i <= n; i+=2) {
            sieve[i] = true;
        }
        for (int i = 3; i <= Math.sqrt(n); i+= 2) {
            if (sieve[i]) {
                for (int j = i * i; j <= n; j += i * 2) {
                    sieve[j] = false;
                }
            }
        }
        return sieve;
    }

}

package problems.impl;

import problems.Problem;

/**
 * Created by Sage on 9/15/2015.
 *
 * numer is just some number times the totient
 */
public class Euler243 implements Problem {

    private static final long NUMER = 15499;
    private static final long DENOM = 94744;

    @Override
    public String solve() {
        // skip first two primes to make life easier
        long phi = 2;
        long den = 6;
        long n,d = 0;
        boolean found = false;
        // Iterate on primes, phi is (p0-1)*(p1-1)*...*(pk-1)/n,
        // so at each step multiply phi by prime-1,
        // and multiply denominator by prime itself
        for (long prime = 5; true; prime = getPrimeAfter(prime)) {
            phi *= prime - 1;
            den *= prime;
            // Need to check if any multiples will break ratio before next prime,
            // so check each multiple up to the value of most recent prime
            if (phi * DENOM >= (den - 1) * NUMER) {
                for (int i = 1; i < prime; i++) {
                    n = phi * i;
                    d = den * i;
                    if (n * DENOM < (d - 1) * NUMER) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
        }
        return String.valueOf(d);
    }

    private static long getPrimeAfter(long p) {
        p += 2;
        while (!isPrime(p)) {
            p += 2;
        }
        return p;
    }

    private static boolean isPrime(long p) {
        long limit = (long) Math.sqrt(p);
        for (long i = 3; i <= limit; i += 2) {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }
}

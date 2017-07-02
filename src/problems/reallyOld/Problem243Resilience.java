package problems.reallyOld;

/**
 * Created by Sage on 9/15/2015.
 *
 * numer is just some number times the totient
 */
public class Problem243Resilience {

    private static final long NUMER = 15499;
    private static final long DENOM = 94744;

    public static void main(String[] args) {
        // skip first two primes to make life easier
        long phi = 2;
        long den = 6;
        long n,d=0;
        boolean found = false;
        // Iterate on primes, phi is (p0-1)*(p1-1)*...*(pk-1)/n,
        // so at each step multiply phi by prime-1,
        // and multiply denominator by prime itself
        for (long prime = 5; true; prime = getPrimeAfter(prime)) {
            phi *= (prime-1);
            den *= prime;
            // Need to check if any multiples will break ratio before next prime,
            // so check each multiple up to the value of most recent prime
            if (phi*DENOM >= (den-1)*NUMER) {
                for (int i = 1; i < prime; i++) {
                    n = phi*i;
                    d = den*i;
                    if (n*DENOM < (d-1)*NUMER) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
        }
        System.out.println("d is: "+d);
    }

    private static long getPrimeAfter(long p) {
        p += 2;
        while (!isPrime(p)) {
            p += 2;
        }
        System.out.println("next prime is "+p);
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

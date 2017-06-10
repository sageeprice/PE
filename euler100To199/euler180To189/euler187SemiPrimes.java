package euler100To199.euler180To189;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sage on 9/16/2015.
 *
 * Solution is veeeerrrrry slow. Go back to answer thread for better solution.
 * Answer: 17427258
 */
public class euler187SemiPrimes {

    private static final int CAP = 100000000;

    public static void main(String[] args) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        primes.add(3);
        long semiprimes = 3;
        int index = -1;
        for (int prime = 5; prime < CAP/2; prime = getPrimeAfter(prime)) {
            primes.add(prime);
            if (index == -1) {
                if (prime * prime < CAP) {
                    semiprimes += primes.size();
                } else {
                    index = primes.size()-2;
                    while (prime*primes.get(index) > CAP) {
                        index--;
                    }
                    semiprimes += index+1;
                }
            } else {
                if (index==0) {
                    semiprimes++;
                    continue;
                }
                while (prime*primes.get(index) > CAP) {
                    index--;
                }
                semiprimes += index+1;
            }
            System.out.println(prime);
        }
        System.out.println("There are "+semiprimes+" semi-primes less than "+CAP);

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

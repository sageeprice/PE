package problems.reallyOld;

import java.util.ArrayList;

/**
 * Correct: 104743
 * @author Sage
 *
 */
public class Problem7NthPrime {
    public static void main(String[] args) {
        final int N = 10001;
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        primes.add(3);
        primes.add(5);
        primes.add(7);
        primes.add(11);
        primes.add(13);
        while (primes.size() < N) {
            primes.add(nextPrime(primes, (int) primes.size()));
        }
        System.out.println("Prime number" + N + " is " + primes.get(10000));
    }

    public static int nextPrime(ArrayList<Integer> primes, int size) {
        int prime = primes.get(size-1) + 2;
        while (!isPrime(primes, prime))
            prime += 2;
        return prime;
    }

    public static boolean isPrime(ArrayList<Integer> primes, int numToCheck) {
        int root = (int) Math.sqrt((double) numToCheck);
        for (int i = 0; primes.get(i) <= root; i++) {
            if (numToCheck % primes.get(i) == 0)
                return false;
        }
        return true;
    }
}

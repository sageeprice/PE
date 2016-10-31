package problems.euler051to075;

import problems.Problem;

/**
 * Problem 69:
 * https://projecteuler.net/problem=69
 *
 * Solved by Sage on 10/30/16.
 */
public class Euler069 implements Problem {

    @Override
    public String solve() {

        /**
         * So totient function is super well known.
         * Let's say x = p1^i1 * p2^i2 * p3^i3 * ... * pn^in
         * where pk are primes and ik are positive integers.
         * Each x has a unique expression as such, due to FTA.
         * then phi(x) = x * (p1 - 1)/p1 * (p2 - 1)p2 * ... * (pn - 1)/pn
         *
         * Knowing this, it is clear than phi(n) is minimized when x has
         * a lot of unique prime factors, thus n/phi(n) is maximized at
         * this point. So all we need to do to find the answer is keep
         * multiplying by primes until our next op would bring us past
         * the limit, in this case 1000000. So the answer is just
         * 2*3*5*7*11*...
         */

        int[] primes = {2,3,5,7,11,13,17,19,23};

        int x = 1;
        for (int i = 0; i < primes.length; i++) {
            if (x * primes[i] > 1000000)
                break;
            x *= primes[i];
        }

        return String.valueOf(x);
    }
}

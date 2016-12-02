package problems.euler101To125;

import problems.Problem;

/**
 * Problem 110:
 * https://projecteuler.net/problem=110
 *
 * Solved by Sage on 11/10/16.
 */
public class Euler110 implements Problem {

    private long[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43};

    @Override
    public String solve() {

        long lowestSolution = 1;
        int highIndex = 0;

        int[] power = new int[primes.length];
        int divisors = 1;
        for (int i = 0; i < power.length; i++) {
            power[i] = 1;
            divisors *= 3;
            lowestSolution *= primes[i];

            if (divisors > 1000000) {
                highIndex = i;
                System.out.println("New low: " + lowestSolution + " with " + divisors + " solutions and high prime " + primes[highIndex]);
                break;
            }
        }

        power[highIndex] = 0;
        divisors /= 3;
        lowestSolution /= primes[highIndex];
        highIndex--;
        while (divisors < 1000000) {
            power[0]++;
            lowestSolution *= primes[0];
            divisors *= power[0] * 2 + 1;
            divisors /= power[0] * 2 - 1;
        }
        System.out.println("New low: " + lowestSolution + " with " + divisors + " solutions and high prime " + primes[highIndex]);
        System.out.println(power[0]);

        return String.valueOf(lowestSolution);

//        for (int i = 0; i < primes.length; i++) {
//
//            // Reset power map
//            for (long key : primeToPowerMap.keySet()) {
//                primeToPowerMap.put(key, 1);
//            }
//
//        }
    }
}

package problems.impl;

import problems.Problem;

import java.util.HashMap;
import java.util.Map;

import static problems.EulerLib.primesTo;

/**
 * Problem 51:
 * https://projecteuler.net/problem=51
 *
 * This problem has been killing me for a while, so I sat down and finally analyzed it.
 * First thought was to narrow down possibilities. If the most frequently repeated digit
 * appears 1 or 2 mod 3 times in the number, then the prime family size is at most 7.
 * This follows from the fact that if you check each sum of repetitions mod 3, you get
 * that 1 or 2 yield 4 numbers 0 mod 3 (0,3,6,9) and 3 numbers 1 or 2 mod 3 each. Thus
 * if we want a prime family of 8 or more, we need the number of digit repetitions to be
 * a multiple of 3. Furthermore, the interchangeable digit must not be the same as the
 * last digit of the number - primes can only end in 1,3,7, or 9 if p > 5.
 *
 * I initially ran the code below to check 5 digit numbers but didn't find anything, so
 * I've bumped it up to checking only 6 digit numbers. There is some additional logic it
 * took me a while to understand about the first digit - if the first digit is one of the
 * interchangeable characters, we cannot replace it with 0. This would yield a shorter
 * number, so it doesn't fit the pattern required and thus isn't part of the family. I was
 * hung up on this for longer than I'd like to admit, my code insisted the answer was 111857.
 *
 * Solved by Sage on 10/25/16.
 */
public class Euler051 implements Problem {

    private static final int TARGET_FAMILY_SIZE = 8;
    private static final int BASE = 10;

    @Override
    public String solve() {
        // Get all primes less than 1000000
        // I checked the 5 digit range and found no solution,
        // so we're starting at 6 digits now
        boolean[] primeSieve = primesTo(1000000);

        // Only need to check odds since we want primes
        for (int i = 100001; i < 1000000; i += 2) {
            // Only check primes
            if (primeSieve[i]) {
                // See if there is a digit that repeats 3 times or more (see analysis)
                int thriceDigit = containsDigitThrice(i);
                // A lot is happening here. The conditions are
                //  1. Most frequent digit must repeat 3 times, per analysis
                //  2. Most frequent digit cannot match last (primes end in 1,3,7,9)
                //  3. Family size must be large enough
                // If all of these are met, return the answer
                if (thriceDigit != -1
                        && thriceDigit != i % BASE
                        && TARGET_FAMILY_SIZE == getFamilySize(i, thriceDigit, primeSieve)) {
                    return String.valueOf(i);
                }
            }
        }
        return null;
    }

    /**
     * @param number the first member of the family
     * @param digit the digit to change around
     * @param sieve the list of all primes to check against
     * @return the amount of primes that occur by replacing number with each valid digit
     */
    private int getFamilySize(int number, int digit, boolean[] sieve) {
        // start represents the first digit substitution
        int start = 0;
        // max possible size of the prime family
        int familySize = 10;
        String num = String.valueOf(number);
        // if the first digit of number matches most frequent digit,
        // then we cannot replace it with 0 for the family (would
        // reduce the length of the element in the family, invalid)
        // hence we increment the start and decrease possible family size
        if (digit == number / 100000) {
            start++;
            familySize--;
        }
        // Check each digit substitution
        for (int i = start; i < BASE; i++) {
            // Check that replacing the digit yields a prime
            if (!sieve[Integer.valueOf(num.replaceAll(String.valueOf(digit), String.valueOf(i)))]) {
                // if not, decrement family size - we can break early if family is too small
                familySize--;
                if (familySize < TARGET_FAMILY_SIZE)
                    return 0;
            }
        }
        return familySize;
    }

    /**
     * Counts the most common char in int x
     * @param x some positive integer
     * @return -1 if no digit appears 3 times, else the digit appearing 3 times
     */
    private int containsDigitThrice(int x) {
        Map<Integer, Integer> digitCount = new HashMap<>();

        while (x > 0) {
            int y = x % BASE;
            if (digitCount.containsKey(y)) {
                digitCount.put(y, digitCount.get(y) + 1);
            } else {
                digitCount.put(y, 1);
            }
            x /= BASE;
        }

        for (int i = 0; i < BASE; i++) {
            if (digitCount.containsKey(i) && digitCount.get(i) >= 3) {
                return i;
            }
        }

        return -1;
    }
}

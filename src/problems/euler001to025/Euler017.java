package problems.euler001to025;

import problems.Problem;

/**
 * Problem 17:
 * If all the numbers from 1 to 1000 (one thousand) inclusive were
 * written out in words, how many letters would be used?
 *
 * Solved by Sage on 10/14/16.
 */
public class Euler017 implements Problem {

    // Dictionaries to look up words for each number
    private static final String[] ONES = {
            "",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
    };
    private static final String[] TEENS = {
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen"};
    private static final String[] TENS = {
            "",
            "",
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"
    };

    private static final String AND = "and";
    private static final String HUNDRED = "hundred";
    private static final String THOUSAND = "thousand";

    private static final int LAST_NUMBER = 1000;
    // you know we keep it
    private static final int ONE_HUNNID = 100;

    @Override
    public String solve() {

        int totalLetters = 0;
        for (int i = 1; i < LAST_NUMBER; i++) {
            totalLetters += getHundredLetters(i / ONE_HUNNID);
            totalLetters += getTensAndOnesLetters(i % ONE_HUNNID);
            // when you get to the hundreds you have to start adding 'and'
            // if there is a nonzero remainder when dividing by one hundred
            if (i > 100 && i % 100 != 0) {
                totalLetters += AND.length();
            }
        }
        totalLetters += ONES[1].length() + THOUSAND.length();
        return String.valueOf(totalLetters);
    }

    /**
     * Calculates the number of letters needed to represent a given two digit int
     * @param x a two-digit number
     * @return the number of letters required to spell out x
     */
    private int getTensAndOnesLetters(int x) {
        // teens break conventional numbering
        if (20 > x && x > 9) {
            return TEENS[x % 10].length();
        } else {
            return ONES[x % 10].length() + TENS[x / 10].length();
        }
    }

    /**
     * Calculates the number of letters needed to spell out x, plus the length of the word one hundred
     * @param x the hundreds digit of a number
     * @return the number of letters required to spell $x hundred
     */
    private int getHundredLetters(int x) {
        return ONES[x].length() + (x > 0 ? HUNDRED.length() : 0);
    }
}

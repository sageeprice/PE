package problems.impl;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Problem 22:
 * https://projecteuler.net/problem=22
 *
 * Solved by Sage on 10/15/16.
 */
public class Euler022 implements Problem {

    private static final String FILE_NAME = "src/text/p022_names.txt";
    private static final String SPLITTER = ",";

    @Override
    public String solve() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String[] names = reader.readLine().replaceAll("\"", "").split(SPLITTER);
            Arrays.sort(names);

            int nameScoreTotal = 0;

            for (int i = 0; i < names.length; i++) {
                nameScoreTotal += (i+1) * getNameScore(names[i]);
            }

            return String.valueOf(nameScoreTotal);

        } catch (Exception e) {
            throw new RuntimeException("Caught exception opening " + FILE_NAME, e);
        }
    }

    private int getNameScore(String name) {
        int score = 0;
        for (char c : name.toCharArray()) {
            // All letters are upper case, so subtract first upper case letter and add one
            // to get the score of each letter in the name.
            score += c - 'A' + 1;
        }
        return score;
    }
}

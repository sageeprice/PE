package problems.euler025to050;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Problem 42:
 * https://projecteuler.net/problem=42
 *
 * Solved by Sage on 10/23/16.
 */
public class Euler042 implements Problem {

    private static final String FILE_NAME = "p042_words.txt";
    private static final String SPLITTER = ",";

    @Override
    public String solve() {

        // Calculate some triangular numbers for quick reference
        Set<Integer> triangles = new HashSet();
        for (int i = 1; i < 50; i++) {
            triangles.add(i * (i+1) / 2);
        }

        int triangularWords = 0;
        try {
            // Read in file, remove '"' and ',' and split the words into an array
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String[] words = reader.readLine().replaceAll("\"", "").split(SPLITTER);

            // Check each word to see if it is triangular
            for (String word : words) {
                if (isTriangular(word, triangles)) {
                    triangularWords++;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Something broke: ", e);
        }

        return String.valueOf(triangularWords);
    }

    // Calculate digital sum for each word
    private boolean isTriangular(String word, Set<Integer> triangles) {
        return triangles.contains(word.chars().map(c -> c - 'A' + 1).sum());
    }
}

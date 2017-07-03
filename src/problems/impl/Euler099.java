package problems.impl;

import problems.Problem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Problem requests that from a file of integer pairs x,y, you select the line with the largest
 * value of x^y. A simple solution is to instead of calculating x^y, just calculate y * log(x).
 *
 * Solution very fast, line # is 709
 */
public class Euler099 implements Problem {

    private static final String FILE_NAME = "src/text/p099_base_exp.txt";
    private static final String SPLITTER = ",";

    @Override
    public String solve() {
        double prod;
        double best = 0;
        long x,y;
        int bestLine = 0;
        int lineIndex = 1;

        try {
            for (String line : Files.readAllLines(Paths.get(FILE_NAME))) {
                String[] nums = line.split(SPLITTER);
                x = Long.valueOf(nums[0]);
                y = Long.valueOf(nums[1]);
                prod = Math.log10(x) * y;
                if (prod > best) {
                    best = prod;
                    bestLine = lineIndex;
                }
                lineIndex++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file " + FILE_NAME);
        }
        return String.valueOf(bestLine);
    }
}

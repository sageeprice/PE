package problems.euler076To100;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem 89:
 * https://projecteuler.net/problem=89
 *
 * Solved by Sage on 11/4/16.
 */
public class Euler089 implements Problem {

    private static final String FILE_NAME = "src/text/p089_roman.txt";
    private static int saved;

    @Override
    public String solve() {

        // Read in the file
        List<String> romans = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                romans.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Something broke... ", e);
        }

        String[] romanStrs = new String[romans.size()];
        romans.toArray(romanStrs);

        saved = 0;

        // Perform valid numeral transformations
        for (int i = 0; i < romanStrs.length; i++) {
            romanStrs[i] = transformRoman(romanStrs[i], "IIII", "IV");
            romanStrs[i] = transformRoman(romanStrs[i], "VIV", "IX");
            romanStrs[i] = transformRoman(romanStrs[i], "XXXX", "XL");
            romanStrs[i] = transformRoman(romanStrs[i], "LXL", "XC");
            romanStrs[i] = transformRoman(romanStrs[i], "CCCC", "CD");
            romanStrs[i] = transformRoman(romanStrs[i], "DCD", "CM");
        }

        return String.valueOf(saved);
    }

    /**
     * Transform a roman numeral, replacing given pattern with a replacement string
     * and incrementing the count of characters saved.
     * @param numeral the valid, maybe not simplified roman numeral
     * @param pattern a string to match against
     * @param replacement a string to replace the pattern with
     * @return transformed numeral
     */
    private String transformRoman(String numeral, String pattern, String replacement) {
        if (numeral.contains(pattern)) {
            saved += pattern.length() - replacement.length();
            return numeral.replace(pattern, replacement);
        } else {
            return numeral;
        }
    }
}

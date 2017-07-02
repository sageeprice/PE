package problems.impl;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem 79:
 * https://projecteuler.net/problem=79
 *
 * Solved by Sage on 11/1/16.
 */
public class Euler079 implements Problem {

    private static final String FILE_NAME = "src/text/p079_keylog.txt";

    @Override
    public String solve() {

        // Solution assumes no digit repetition in passcode
        String passcode = "";
        List<String> codes = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                codes.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Something broke... ", e);
        }

        while (!codes.isEmpty()) {
            String nextChar = codes.get(0).substring(0,1);
            for (String code : codes) {
                if (code.contains(nextChar) && !code.substring(0,1).equals(nextChar)) {
                    nextChar = code.substring(0,1);
                }
            }
            passcode += nextChar;
            final String c = nextChar;
            // Remove determined character from codes, filter list to non-empty strings
            codes = codes.stream()
                    .map(s -> s.replace(c, ""))
                    .filter(s -> s.length() != 0)
                    .collect(Collectors.toList());
        }
        return passcode;
    }
}

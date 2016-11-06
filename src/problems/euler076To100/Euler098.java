package problems.euler076To100;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Problem 98:
 * https://projecteuler.net/problem=98
 *
 * Solved by Sage on 11/6/16.
 */
public class Euler098 implements Problem {

    private static final String FILE_NAME = "src/text/p098_words.txt";

    // TODO: clean this mess up

    @Override
    public String solve() {

        Map<String, Set<String>> anagramMap= new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));

            // Everything is on one line, strip the " marks and split into words
            String[] words = reader.readLine().replace("\"", "").split(",");

            // Add words to anagram map
            for (String word : words) {
                char[] wordChars = word.toCharArray();
                Arrays.sort(wordChars);
                String sortedWord = new String(wordChars);

                if (anagramMap.containsKey(sortedWord)) {
                    anagramMap.get(sortedWord).add(word);
                } else {
                    Set<String> anagrams = new HashSet<>();
                    anagrams.add(word);
                    anagramMap.put(sortedWord, anagrams);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Something broke... ", e);
        }

        // Clean up anagram map, remove words which don't have
        // anagrams to pair with
        List<String> badKeys = anagramMap.keySet().stream()
                .filter(s -> anagramMap.get(s).size() < 2)
                .collect(Collectors.toList());
        badKeys.forEach(anagramMap::remove);

        // Map of number length to set of squares of that length
        Map<Integer, Set<Integer>> squareSetMap = new HashMap<>();
        // longest string in set of anagrammatic words is 10 characters
        for (int i = 3; i < 11; i++) {
            squareSetMap.put(i, new HashSet<>());
        }

        Map<Integer, Map<String, List<Integer>>> squareMap = new HashMap<>();
        for (int i = 11; i < Math.sqrt(Integer.MAX_VALUE); i++) {

            // Get length of square, key to the square map
            int squareLength = 1 + (int) Math.floor(Math.log10(i * i));

            squareSetMap.get(squareLength).add(i * i);
        }


        // Order the set from longest to shortest so we can exit early
        String[] anagramKeys = anagramMap.keySet().toArray(new String[anagramMap.keySet().size()]);
        Arrays.sort(anagramKeys, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        String longestWord = "";
        int highestSquare = 0;

        for (int i = anagramKeys.length - 1; i >= 0; i--) {

            // Skip words that are too short
            if (anagramKeys[i].length() < longestWord.length())
                continue;

            // Anagrams to examine
            String[] anagrams = anagramMap
                    .get(anagramKeys[i])
                    .toArray(new String[anagramMap.get(anagramKeys[i]).size()]);

            // Squares to try with anagrams
            Set<Integer> squares = squareSetMap.get(anagramKeys[i].length());

            for (int j = 0; j < anagrams.length - 1; j++) {

                String wordOne = anagrams[j];

                for (int square : squares) {
                    // Get map, exit early if it cannot be constructed
                    Map<Character, Integer> digitMap = getDigitalMap(anagrams[j], square);
                    if (digitMap == null)
                        continue;

                    // Check other anagrams for valid pair
                    for (int k = j + 1; k < anagrams.length; k++) {
                        String wordTwo = anagrams[k];
                        int numTwo = mapWord(wordTwo, digitMap);

                        if (squares.contains(numTwo)) {
                            int best = Math.max(numTwo, mapWord(wordOne, digitMap));
                            if (best > highestSquare) {
                                highestSquare = best;
                                longestWord = wordOne;
                            }
                        }
                    }
                }
            }
        }

        return String.valueOf(highestSquare);
    }

    /**
     * Create a map from letter to digit for a given word and integer
     * @param word a String
     * @param x an integer of same length as word
     * @return map
     */
    private Map<Character, Integer> getDigitalMap(String word, int x) {
        Map<Character, Integer> digitalMap = new HashMap<>();
        char[] wordChars = word.toCharArray();
        for (int i = wordChars.length - 1; i >= 0; i--) {
            if (!digitalMap.containsValue(x % 10)) {
                digitalMap.put(wordChars[i], x % 10);
            } else {
                // Fail out if digit mapping is not unique
                if (!digitalMap.containsKey(wordChars[i]) || digitalMap.get(wordChars[i]) != x % 10)
                    return null;
            }
            x /= 10;
        }
        return digitalMap;
    }

    private int mapWord(String word, Map<Character, Integer> digitalMap) {
        int x = 0;
        char[] wordChars = word.toCharArray();
        for (int i = 0; i < wordChars.length; i++) {
            if (!digitalMap.containsKey(wordChars[i]))
                return 0;

            else {
                x *= 10;
                x += digitalMap.get(wordChars[i]);
            }
        }
        return x;
    }
}

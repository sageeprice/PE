package problems.reallyOld;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Bleh. Lazy implementation cuz I got frustrated. TODO: rewrite this more cleanly
 */
public class Problem62CubicPermutations {

    private static Set<Pair<char[], Integer>> cubeStrings;

    public static void main(String[] args) {
        long n = 345;
        char[] answer = null;
        int count;
        cubeStrings = new HashSet<>();
        cubeStrings.add(new Pair<>(String.valueOf(n*n*n).toCharArray(), 1));
        boolean done = false;
        while (!done) {
            n++;
            boolean found = false;
            for (Pair<char[], Integer> pair : cubeStrings) {
                char[] cube = String.valueOf(n*n*n).toCharArray();
                char[] firstCube = new char[pair.getKey().length];
                for (int i = 0; i < pair.getKey().length; i++) {
                    firstCube[i] = pair.getKey()[i];
                }
                if (cube.length > firstCube.length) {
                    cubeStrings = new HashSet<>();
                    break;
                }
                Arrays.sort(firstCube);
                Arrays.sort(cube);
                if (Arrays.equals(cube,firstCube)) {
                    found = true;
                    count = pair.getValue();
                    if (count == 4) {
                        answer = pair.getKey();
                        done = true;
                        break;
                    }
                    cubeStrings.remove(pair);
                    cubeStrings.add(new Pair<>(pair.getKey(), count + 1));
                    break;
                }
            }
            if (!found) {
                cubeStrings.add(new Pair<>(String.valueOf(n*n*n).toCharArray(), 1));
            }
        }
        System.out.println("Found answer of " + String.valueOf(answer));
    }
}

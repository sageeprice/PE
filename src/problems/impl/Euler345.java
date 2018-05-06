package problems.impl;

import problems.Problem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Solved via a modified version of Held-Karp, recursively calculating the max
 * possible sum of sub-matrices by iterating across columns. Uses memoization
 * to run in a reasonable amount of time.
 *
 * Answer: 13938
 */
public class Euler345 implements Problem{

  private static final String MATRIX =
      "7 53 183 439 863 497 383 563 79 973 287 63 343 169 583\n" +
      "627 343 773 959 943 767 473 103 699 303 957 703 583 639 913\n" +
      "447 283 463 29 23 487 463 993 119 883 327 493 423 159 743\n" +
      "217 623 3 399 853 407 103 983  89 463 290 516 212 462 350\n" +
      "960 376 682 962 300 780 486 502 912 800 250 346 172 812 350\n" +
      "870 456 192 162 593 473 915 45 989 873 823 965 425 329 803\n" +
      "973 965 905 919 133 673 665 235 509 613 673 815 165 992 326\n" +
      "322 148 972 962 286 255 941 541 265 323 925 281 601 95 973\n" +
      "445 721 11 525 473 65 511 164 138 672 18 428 154 448 848\n" +
      "414 456 310 312 798 104 566 520 302 248 694 976 430 392 198\n" +
      "184 829 373 181 631 101 969 613 840 740 778 458 284 760 390\n" +
      "821 461 843 513 17 901 711 993 293 157 274 94 192 156 574\n" +
      "34 124 4 878 450 476 712 914 838 669 875 299 823 329 699\n" +
      "815 559 813 459 522 788 168 586 966 232 308 833 251 631 107\n" +
      "813 883 451 509 615 77 281 613 459 205 380 274 302 35 805";

  @Override
  public String solve() {
    String[] lines = MATRIX.split("\n");
    int dim = lines.length;
    int[][] matrix = toIntMatrix(lines, dim);

    boolean[] visited = new boolean[dim];

    Map<String, Integer> bestSums = new HashMap<>();
    bestSums.put(Arrays.toString(new boolean[dim]), 0);

    for (int i = 0; i < dim; i++) {
      visited[i] = true;
    }
    return String.valueOf(getShortestPath(bestSums, visited, matrix));
  }

  private static int getShortestPath(
      Map<String, Integer> bestPaths, boolean[] visited, int[][] matrix) {
    if (bestPaths.containsKey(Arrays.toString(visited))) {
      return bestPaths.get(Arrays.toString(visited));
    }

    int col = 0;
    for (boolean x : visited) {
      if (x) {
        col++;
      }
    }
    for (int i = 0; i < visited.length; i++) {
      if (visited[i]) {
        visited[i] = false;
        int bestWithout = getShortestPath(bestPaths, visited, matrix) + matrix[i][col-1];
        visited[i] = true;
        bestPaths.compute(
            Arrays.toString(visited),
            (k, v) -> (v == null) || v < bestWithout ? bestWithout : v);
      }
    }
    return bestPaths.get(Arrays.toString(visited));
  }

  /**
   * Returns a transformation of the input {@code lines} into an {@code
   * int[][]}. Assumes that each line is a space separated string of valid
   * integers, with no leading or trailing space characters.
   */
  private static int[][] toIntMatrix(String[] lines, int dim) {
    int[][] matrix = new int[dim][dim];
    for (int i = 0; i < dim; i++) {
      String[] entries = lines[i].split(" +");
      for (int j = 0; j < dim; j++) {
        matrix[i][j] = Integer.valueOf(entries[j]);
      }
    }
    return matrix;
  }
}

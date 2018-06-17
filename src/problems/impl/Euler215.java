package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;

/**
 * Dynamic programming solution. Construct all levels, determine valid transitions between levels.
 * Then calculate number of ways to reach given level from the previous level.
 *
 * Answer: 806844323190414
 */
public class Euler215 implements Problem {

  private static final int ROW_LENGTH = 32;
  private static final int LEVELS = 10;

  @Override
  public String solve() {

    List<BitSet> rows = new ArrayList<>();
    recursiveConstructLayers(0, new BitSet(), rows);

    // Determine valid transitions between layers.
    boolean[][] transitions = new boolean[rows.size()][rows.size()];
    for (int i = 0; i < rows.size(); i++) {
      for (int j = i+1; j < rows.size(); j++) {
        if (!rows.get(i).intersects(rows.get(j))) {
          transitions[i][j] = true;
          transitions[j][i] = true;
        }
      }
    }
    // Dynamic programming base case.
    long[] oldPaths = new long[rows.size()];
    for (int i = 0; i < rows.size(); i++) {
      for (int j = 0; j < rows.size(); j++) {
        oldPaths[i] += transitions[i][j] ? 1 : 0;
      }
    }
    // Dynamic programming.
    for (int k = 0; k < LEVELS - 2; k++) {
      long[] paths = new long[rows.size()];
      for (int i = 0; i < rows.size(); i++) {
        for (int j = 0; j < rows.size(); j++) {
          if (transitions[i][j]) {
            paths[j] += oldPaths[i];
          }
        }
      }

      oldPaths = paths;
    }
    return String.valueOf(Arrays.stream(oldPaths).sum());
  }

  private static void recursiveConstructLayers(int index, BitSet base, Collection<BitSet> rows) {
    if (index == ROW_LENGTH) {
      base.clear(ROW_LENGTH);
      rows.add((BitSet) base.clone());
      base.set(ROW_LENGTH);
    }
    if (index >= (ROW_LENGTH - 1)) {
      return;
    }
    base.set(index + 2);
    recursiveConstructLayers(index + 2, base, rows);
    base.clear(index + 2);
    base.set(index + 3);
    recursiveConstructLayers(index + 3, base, rows);
    base.clear(index + 3);
  }

}

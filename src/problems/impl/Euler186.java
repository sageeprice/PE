package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Essentially keeping track of a bunch of disjoint sets. When doing this, we
 * only need to track the size of the set, and one element of the set.
 *
 * Answer: 2325629
 */
public class Euler186 implements Problem {

  private static final int PM_PHONE_NUMBER = 524_287;
  private static final int MOD = 1_000_000;

  @Override
  public String solve() {
    // rep as in representative...
    int[] reps = new int[MOD];
    for (int i = 0; i < reps.length; i++) {
      reps[i] = -1;
    }
    // Queue structure to track Fibs.
    List<Integer> lfgQ = new ArrayList<>();
    for (int i = 1; i < 56; i++) {
      lfgQ.add((int)((100_003L - 200_003L * i + 300_007L * i * i * i) % MOD));
    }

    int callCount = 0;
    int misdials = 0;
    Map<Integer, Integer> repCounts = new HashMap<>();
    while (repCounts.getOrDefault(getRep(PM_PHONE_NUMBER, reps), 0) < 990_000) {
      // Optimization: keep lookup fast by periodically setting all children
      // immediately below reps.
      if (callCount % 7500 == 0 && callCount > 500_000) {
        for (int i = 0; i < MOD; i++) {
          reps[i] = getRep(i, reps);
        }
      }
      if ((++callCount * 2) < 0) {
        throw new IllegalStateException("Error state: call count looped");
      }
      int f1, f2;
      if (callCount < 28) {
        f1 = lfgQ.get(2*callCount - 2);
        f2 = lfgQ.get(2*callCount - 1);
      } else if (callCount == 28) {
        f1 = lfgQ.get(lfgQ.size() - 1);
        f2 = nextNumber(lfgQ);
        lfgQ.add(f2);
        lfgQ.remove(0);
      } else {
        f1 = nextNumber(lfgQ);
        lfgQ.remove(0);
        lfgQ.add(f1);
        f2 = nextNumber(lfgQ);
        lfgQ.remove(0);
        lfgQ.add(f2);
      }
      if (f1 == f2) {
        misdials++;
        continue;
      }
      int h1 = getRep(f1, reps);
      int h2 = getRep(f2, reps);
      if (h1 == h2 && h1 != -1) {
        continue;
      }

      if (h1 == -1 && h2 == -1) {
        reps[f1] = f1;
        reps[f2] = f1;
        repCounts.put(f1, 2);
      } else if (h1 != -1 && h2 != -1) {
        reps[h2] = h1;
        repCounts.computeIfPresent(h1, (k, v) -> v + repCounts.get(h2));
        repCounts.remove(h2);
      } else {
        if (h1 == -1) {
          reps[f1] = h2;
          repCounts.computeIfPresent(h2, (k, v) -> v + 1);
        }
        if (h2 == -1) {
          reps[f2] = h1;
          repCounts.computeIfPresent(h1, (k, v) -> v + 1);
        }
      }
    }
    return String.valueOf(callCount - misdials);
  }

  private static int getRep(int id, int[] reps) {
    int h = id;
    while (h != -1 && reps[h] != h) {
      h = reps[h];
    }
    return h;
  }

  private int nextNumber(List<Integer> lfg) {
    return (int) (((long)lfg.get(lfg.size() - 55) + (long)lfg.get(lfg.size() - 24)) % MOD);
  }
}

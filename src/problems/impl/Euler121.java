package problems.impl;

import problems.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Brute force generate all possible draw sequences. Then filter for winning
 * sequences (blue draws > total / 2), and calculate probability of winning
 * sequence. Amount to charge is then just the floor of 1 / win probability.
 *
 * Answer: 2269
 */
public class Euler121 implements Problem {

  @Override
  public String solve() {
    int numDraws = 15;
    List<List<Boolean>> possibleDraws = new ArrayList<>();
    allPossibleDraws(numDraws, 0, new ArrayList<>(), possibleDraws);
    double winProb =
        possibleDraws.stream()
            .filter(game -> game.stream().filter(x -> x).count() > numDraws / 2)
            .mapToDouble(Euler121::drawProbability)
            .sum();
    return String.valueOf((int) Math.floor(1 / winProb));
  }

  private static void allPossibleDraws(
      int numDraws,
      int curDraw,
      List<Boolean> drawn,
      List<List<Boolean>> possibleDraws) {
    if (curDraw == numDraws) {
      possibleDraws.add(new ArrayList<>(drawn));
      return;
    }

    drawn.add(false);
    allPossibleDraws(numDraws, curDraw + 1, drawn, possibleDraws);
    drawn.remove(drawn.size() - 1);
    drawn.add(true);
    allPossibleDraws(numDraws, curDraw + 1, drawn, possibleDraws);
    drawn.remove(drawn.size() - 1);
  }

  private static double drawProbability(List<Boolean> draws) {
//    System.out.print(draws.toString() + ": ");
    double prob = 1;
    for (double i = 0; i < draws.size(); i++) {
      prob *= (draws.get((int) i) ? 1 : i + 1) / (i + 2);
    }
//    System.out.println(prob);
    return prob;
  }
}

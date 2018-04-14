package problems.impl;

import problems.Problem;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Descartes' Theorem destroys this problem, https://en.wikipedia.org/wiki/Descartes%27_theorem
 *
 * Answer: 0.00396087
 */
public class Euler199 implements Problem {

  private static final Double BIG_RADIUS = 100.0;

  @Override
  public String solve() {
    double bigRadius = BIG_RADIUS;
    double bigCurvature = -1 / bigRadius;
    double smallRadius = bigRadius / (1 + 2/Math.sqrt(3));
    double smallCurvature = 1 / smallRadius;
    // Radius of circles created at first iteration.
    double firstOuterCurvature = getFourthCurvature(smallCurvature, smallCurvature, bigCurvature);
    double firstInnerCurvature = getFourthCurvature(smallCurvature, smallCurvature, smallCurvature);

    // Curvatures which appear three times on the interior. The inner circle at first iteration appears only once, and
    // is thus added in later.
    List<Double> tripleCurvatures = new ArrayList<>();
    tripleCurvatures.add(smallCurvature);
    tripleCurvatures.add(firstOuterCurvature);

    // The triples which will define the next round of curvatures.
    List<KissingCircleTriple> kissingCircles = new ArrayList<>();
    kissingCircles.add(new KissingCircleTriple(smallCurvature, smallCurvature, firstInnerCurvature));
    kissingCircles.add(new KissingCircleTriple(smallCurvature, smallCurvature, firstOuterCurvature));
    kissingCircles.add(new KissingCircleTriple(smallCurvature, bigCurvature, firstOuterCurvature));
    kissingCircles.add(new KissingCircleTriple(smallCurvature, bigCurvature, firstOuterCurvature));

    // At each iteration, generate the curvature of the tangent circle for each triple of kissing circles and add it
    // to the list of triple curvatures. Then prepare the next iteration by adding each new triple of kissing circles
    // created by the new tangent circle and each pair in the prior triple.
    for (int i = 1; i < 10; i++) {
      List<KissingCircleTriple> nextKissingTriples = new ArrayList<>();
      for (KissingCircleTriple triple : kissingCircles) {
        double nextCurvature = getInnerTangentCircleCurvature(triple);
        tripleCurvatures.add(nextCurvature);
        nextKissingTriples.add(new KissingCircleTriple(triple.c1, triple.c2, nextCurvature));
        nextKissingTriples.add(new KissingCircleTriple(triple.c1, nextCurvature, triple.c3));
        nextKissingTriples.add(new KissingCircleTriple(nextCurvature, triple.c2, triple.c3));
      }
      kissingCircles = nextKissingTriples;
    }

    // Only want first 8 decimal places.
    DecimalFormat df = new DecimalFormat("#.########");
    df.setRoundingMode(RoundingMode.HALF_UP);
    return String.valueOf(
        df.format(1 - calculateOccupiedProportion(bigCurvature, tripleCurvatures, firstInnerCurvature)));
  }

  /**
   * Returns the proportion of the total area of circle defined by curvature {@code bigC} which is occupied by the
   * circle with curvature {@code firstInnerC} and triple the circles in {@code tripleCurvatures}.
   */
  private static double calculateOccupiedProportion(double bigC, List<Double> tripleCurvatures, double firstInnerC) {
    double totalArea = getCircleAreaForCurvature(bigC);
    double occupiedSpace =
        tripleCurvatures.stream()
            .map(Euler199::getCircleAreaForCurvature)
            .map(a -> a * 3)
            .mapToDouble(Double::doubleValue)
            .sum();
    occupiedSpace += getCircleAreaForCurvature(firstInnerC);
    return occupiedSpace / totalArea;
  }

  private static double getCircleAreaForCurvature(double curvature) {
    double r = 1.0 / curvature;
    return r * r * Math.PI;
  }

  private static double getInnerTangentCircleCurvature(KissingCircleTriple triple) {
    return getFourthCurvature(triple.c1, triple.c2, triple.c3);
  }

  private static double getFourthCurvature(double c1, double c2, double c3) {
    return c1 + c2 + c3 + 2 * Math.sqrt(c1*c2 + c2*c3 + c3*c1);
  }

  /** A triple of tangent circles, specified by curvatures. */
  private static class KissingCircleTriple {
    private static final String DELIMITER = ", ";
    final double c1;
    final double c2;
    final double c3;

    KissingCircleTriple(double c1, double c2, double c3) {
      this.c1 = c1;
      this.c2 = c2;
      this.c3 = c3;
    }

    @Override
    public String toString() {
      return String.valueOf(c1) + DELIMITER + c2 + DELIMITER + c3;
    }
  }
}

package problems.impl;

import problems.Problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There are tons of ways of doing this. I stole from StackOverflow:
 * https://stackoverflow.com/questions/2049582/how-to-determine-if-a-point-is-in-a-2d-triangle
 *
 * Answer: 228
 */
public class Euler102 implements Problem {

  private static final String FILE_NAME = "src/text/p102_triangles.txt";

  @Override
  public String solve() {

    List<String> triStrs = new ArrayList<>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
      String line;
      while ((line = reader.readLine()) != null) {
        triStrs.add(line);
      }
    } catch (Exception e) {
      throw new RuntimeException("Something broke... ", e);
    }

    return String.valueOf(triStrs.stream().map(Euler102::toTriangle).filter(Euler102::containsOrigin).count());
  }

  private static Triangle toTriangle(String triStr) {
    int[] coords = Arrays.stream(triStr.split(",")).mapToInt(Integer::valueOf).toArray();
    Point p0 = new Point(coords[0], coords[1]);
    Point p1 = new Point(coords[2], coords[3]);
    Point p2 = new Point(coords[4], coords[5]);
    return new Triangle(p0, p1, p2);
  }

  private static boolean containsOrigin(Triangle tri) {
    Point p0 = tri.p0;
    Point p1 = tri.p1;
    Point p2 = tri.p2;
    double area = .5 * (-p1.y * p2.x + p0.y * (-p1.x + p2.x) + p0.x * (p1.y - p2.y) + p1.x * p2.y);
    int sign = area > 0 ? 1 : -1;

    double s = (p0.y * p2.x - p0.x * p2.y) * sign;
    double t = (p0.x * p1.y - p0.y * p1.x) * sign;

    return s > 0 && t > 0 && s + t < 2 * area * sign;
  }

  private static class Point {
    final int x;
    final int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private static class Triangle {
    final Point p0;
    final Point p1;
    final Point p2;

    Triangle(Point p0, Point p1, Point p2) {
      this.p0 = p0;
      this.p1 = p1;
      this.p2 = p2;
    }
  }
}

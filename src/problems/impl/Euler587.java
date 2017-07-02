package problems.impl;

import problems.Problem;

/**
 * 2204. Not much to say after the geometry is done.
 */
public class Euler587 implements Problem {

    private static final double AREA = 1 - Math.PI/4;
    private static final double FRACTION = .001;

    @Override
    public String solve() {
        for (int i = 2; ; i++) {
            double x = getX(i);
            // Area from y-axis to point of intersection.
            double area1 = x/2;
            // Area from y=1 to point of intersection.
            double area2 = (1 - i*x)/2;
            // Area of wedge from circle.
            double circleArea = Math.PI * (getTheta(x, i) / 2 / Math.PI);
            // Principle of inclusion-exclusion.
            if ((area1 + area2 - circleArea) / AREA < FRACTION) {
                return String.valueOf(i);
            }
        }
    }

    /** Returns the (lesser) x-coordinate where the line y=nx and the circle of radius 1 centered at (1,1) intersect. */
    private static double getX(int n) {
        return (1 + n - Math.sqrt(2.0 * n)) / ((double) (n * n + 1));
    }

    /** Returns the angle of the arc of the circle from (0,1) to the point of intersection. */
    private static double getTheta(double x, int n) {
        return Math.atan((1-n*x)/(1-x));
    }
}

package problems.impl;

import problems.Problem;

public class Euler317 implements Problem {

    private static final double G = 9.81;
    private static final double H = 100;
    private static final double V = 20;

    @Override
    public String solve() {
        // High point when particle goes straight up. y = y0 + v*t + a*t*t/2
        double height = (2.0 * G * H + V * V)/(2.0 * G);
        // Use parabolic equation, substitute y = 0, solve for x. Differentiate
        // with respect to initial angle.
        double base_radius = V * V / G * Math.sqrt(1 + 2 * G * H / (V * V));

        // Formula for volume of hyperboloid -> h*r^2*PI/2
        return String.format("%.4f", Math.PI * height * base_radius * base_radius / 2.0);
    }
}

package problems.impl;

import problems.Problem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

/**
 * Given n points, the best fit polynomial of degree n-1 can be determined via the
 * Lagrange Interpolating Polynomial. This polynomial then just needs to be applied
 * to the desired term.
 *
 * Answer: 37076114526
 */
public class Euler101 implements Problem {

    @Override
    public String solve() {
        return String.valueOf(LongStream.rangeClosed(2, 11).map(Euler101::lagrange).sum());
    }

    /** http://mathworld.wolfram.com/LagrangeInterpolatingPolynomial.html */
    private static long lagrange(long degree) {
        long s = 0;

        for (int i = 1; i < degree; i++) {
            long num = 1;
            long den = 1;
            for (int j = 1; j < degree; j++) {
                if (i != j) {
                    num *= degree - j;
                    den *= i - j;
                }
            }
            s += num * evaluate(i) / den;
        }
        return s;
    }

    private static double evaluate(double x) {
        return (Math.pow(x, 11) + 1) / (x + 1);
    }

    private static double[][] calculateConstants(int degree, List<Double> actuals) {
        double[][] x = powerMatrix(degree);
        double[][] xt = transpose(x);

        double[][] a = invert(multiply(xt, x));
        double[][] b = multiply(a, xt);
        double[][] values = new double[degree+1][1];
        for (int i = 0; i <= degree; i++) {
            values[i][0] = actuals.get(i);
        }
        double[][] constants = multiply(b, values);
        for (int i = 0; i < constants.length; i++) {
            if (Math.abs(Math.round(constants[i][0]) - constants[i][0]) < .0000001) {
                constants[i][0] = Math.round(constants[i][0]);
            }
        }
        return constants;
    }

    private static double fit(int degree, double[][] constants) {
        double s = 0;
        for (int i = 0; i < constants.length; i++) {
            s += Math.pow(degree + 2, i) * constants[i][0];
        }
        return s;
    }

    /** Returns a matrix where 0-indexed entry (i, j) has value (i+1)^j. */
    private static double[][] powerMatrix(int degree) {
        double[][] m = new double[degree+1][degree + 1];
        for (int i = 0; i <= degree; i++) {
            for (int j = 0; j <= degree; j++) {
                m[i][j] = Math.pow((double) (i+1), j);
            }
        }
        return m;
    }

    /**
     * Returns the product AB. Implements standard matrix multiplication.
     *
     * @throws IllegalArgumentException when matrix dimensions do not enable multiplication.
     */
    private static double[][] multiply(double[][] a, double[][] b) {
        if (a[0].length != b.length) {
            throw new IllegalArgumentException(
                    "Matrices have conflicting sizes: "
                            + a.length + " by " + a[0].length
                            + " and "
                            + b.length + " by " + b[0].length);
        }

        double[][] c = new double[a.length][b[0].length];

        for (int ax = 0; ax < a.length; ax++) {
            for (int by = 0; by < b[0].length; by++) {
                for (int ay = 0; ay < a[0].length; ay++) {
                    double foo = a[ax][ay];
                    double bar = b[ay][by];
                    c[ax][by] += foo * bar;
                }
            }
        }
        return c;
    }

    /** Returns the transpose of the given matrix. */
    private static double[][] transpose(double[][] m) {
        double[][] t = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                t[j][i] = m[i][j];
            }
        }
        return t;
    }

    /**
     * Returns the inverse of matrix m. Uses Guass-Jordan elimination approach. Currently broken due to rounding issues
     * at higher values.
     *
     * @throws IllegalArgumentException when m has non-square dimensions.
     */
    private static double[][] invert(double[][] m) {
        if (m.length != m[0].length) {
            throw new IllegalArgumentException("Matrix must be square to invert!");
        }

        // Create identity matrix on which to mirror row-transformations.
        double[][] inv = identity(m.length);
        // A copy of m on which to apply transformation.
        double[][] n = Arrays.copyOf(m, m.length);

        for (int i = 0; i < n.length; i++) {
            for (int j = i+1; j < n.length; j++) {
                double x = n[j][i] / n[i][i];

                // Subtract rows to reduce entry (j, i) to 0. Mirror on the to-be-inverse matrix.
                n[j] = subtractRow(n[j], n[i], x);
                inv[j] = subtractRow(inv[j], inv[i], x);
            }
        }

        for (int i = n.length - 1; i > 0; i--) {
            for (int j = i-1; j >= 0; j--) {
                double x = n[j][i] / n[i][i];

                n[j] = subtractRow(n[j], n[i], x);
                inv[j] = subtractRow(inv[j], inv[i], x);

            }
        }
        for (int i = 0; i < n.length; i++) {
            double x = n[i][i];
            if (x == 0)
                continue;
            n[i] = divideRow(n[i], x);
            inv[i] = divideRow(inv[i], x);
        }

        return inv;
    }

    /** Returns r1 - a * r2. */
    private static double[] subtractRow(double[] r1, double[] r2, double a) {
        double[] result = Arrays.copyOf(r1, r1.length);
        for (int i = 0; i < result.length; i++) {
            result[i] -= r2[i] * a;
        }
        return result;
    }

    /** Returns r / a. */
    private static double[] divideRow(double[] r, double a) {
        double[] result = Arrays.copyOf(r, r.length);
        for (int i = 0; i < result.length; i++) {
            result[i] /= a;
        }
        return result;
    }

    /** Returns a {@code d} by {@code d} identity matrix. */
    private static double[][] identity(int d) {
        double[][] identity = new double[d][d];
        for (int i = 0; i < d; i++) {
            identity[i][i] = 1;
        }
        return identity;
    }
}

package euler000To099.euler080To089;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sage on 9/14/2015.
 */
public class Problem80SquareRootDecimalExpansion {

    private static final int DIGIT_PRECISION = 105;
    private static final BigDecimal TWO = new BigDecimal("2");
    private static final char DOT = '.';

    public static void main(String[] args) {
        long sum = 0;
        long digSum;
        BigDecimal root;
        String rootStr;
        ArrayList sqrs = new ArrayList(10);
        for (int i = 1; i <= 10; i++) {
            sqrs.add(i*i);
        }
        Set<Integer> squares = new HashSet<>(sqrs);

        for (int i = 1; i <= 100; i++) {
            // skip squares
            if (squares.contains(i))
                continue;

            root = bigSqrt(new BigDecimal(Integer.toString(i)));
            rootStr = root.toString();
            digSum = 0;
            for (int j = 0; j <= 100; j++) {
                // skip the decimal
                if (rootStr.charAt(j) == DOT)
                    continue;
                digSum += Integer.valueOf(rootStr.substring(j,j+1));
            }
            sum += digSum;

        }
        System.out.println("Sum of digits is: "+sum);
    }

    public static BigDecimal bigSqrt(BigDecimal A) {
        BigDecimal x0 = new BigDecimal("0");
        BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = A.divide(x0, DIGIT_PRECISION, RoundingMode.HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(TWO, DIGIT_PRECISION, RoundingMode.HALF_UP);

        }
        return x1;
    }
}

package euler000To099.euler060To69;

import java.math.BigInteger;

/**
 * Created by Sage on 9/13/2015.
 *
 * longs are too small, so big integer
 */
public class Problem65EConvergents {

    public static void main(String[] args) {
        BigInteger num = new BigInteger("1");
        BigInteger den = new BigInteger("1");
        BigInteger zero = new BigInteger("0");
        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        BigInteger ten = new BigInteger("10");
        BigInteger flip;
        for (BigInteger k = new BigInteger("33"); k.compareTo(zero) != 0; k = k.subtract(one)) {
            flip = den;
            den = den.multiply(k).multiply(two).add(num);
            num = flip;
            flip = den;
            den = den.add(num);
            num = flip;
            if (k.equals(one))
                break;
            flip = den;
            den = den.add(num);
            num = flip;
        }
        flip = den.multiply(two);
        num = num.add(flip);
        System.out.println(num);
        System.out.println(DigitSum(num));
    }

    private static int DigitSum(BigInteger number) {
        String k = number.toString();
        int ds = 0;

        for (int i = 0; i < k.length(); i++) {
            ds += k.charAt(i) - '0';
        }

        return ds;
    }
}

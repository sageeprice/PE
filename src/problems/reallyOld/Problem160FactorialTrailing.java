package problems.reallyOld;

/**
 * Go look in the forums, understand this better
 */
public class Problem160FactorialTrailing {

    private static final long MOD = 100000;
    private static final long CAP = 1000000000000L;

    public static void main(String[] args) {
        long factorial = fakeFac(CAP);
        System.out.println(factorial);
        long x = CAP;
        long twos = 0;
        while (x > 0) {
            x /= 2;
            twos += x;
        }
        x = CAP;
        while (x > 0) {
            x /= 5;
            twos -= x;
        }
        twos %= MOD;
        for (long i = 0; i < twos; i++) {
            factorial *= 2;
            factorial %= MOD;
        }
        System.out.println(factorial);
    }

    private static long fakeFac(long x) {
        if (x == 0) {
            return 1;
        } else {
            return killTwos(x) * killFives(x) % MOD;
        }
    }

    private static long killTwos(long x) {
        if (x == 0) {
            return 1;
        } else {
            return fakeFac(x/2);
        }
    }

    private static long killFives(long x) {
        if (x == 0) {
            return 1;
        } else {
            return killFives(x/5) * coprimeProd(x) % MOD;
        }
    }

    private static long coprimeProd(long x) {
        x %= MOD;
        long prod = 1;
        for (int i = 1; i <= x; i++) {
            if (i%2 != 0 && i%5 != 0) {
                prod *= i;
                prod %= MOD;
            }
        }
        return prod;
    }
}

package problems.reallyOld;

/**
 * Created by Sage on 9/13/2015.
 *
 * It works! But it's way too fucking slow.
 * Runtime  is 2247.307 seconds ~ 37.5 minutes.
 * Answer: 1209002624
 *         1209002666
 *         3627008000
 */
public class Problem202Laserbeam {

    static long BOUNCES = 12017639147L;
    static long LEVELS = (BOUNCES+3)/2;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("Calculating up to: "+LEVELS);
        long ways = 0;

        long factor = 3;
        long x = LEVELS;
        long relativePrimes = x;
        long primeFactors = 0;
        while (x != 1) {
            if (x % factor == 0) {
                relativePrimes /= factor;
                relativePrimes *= (factor - 1);
                while (x%factor == 0) {
                    x /= factor;
                }
                primeFactors++;
                System.out.println(factor + " is a factor");
            }
            factor += 2;
        }
        System.out.println("Number of relatively prime values is " + (long) (relativePrimes - Math.pow(2, primeFactors))/3);
        for (long i = 1; i < LEVELS; i++) {
            if ((i%3 == 2) && (gcd(i, LEVELS)==1)) {
                ways++;
            }
            if (i%10000000==0) {
                System.out.println("Done "+i);
            }
        }
        System.out.println("Answer: "+ways);
        long endTime = System.currentTimeMillis();
        System.out.println("Total runtime is " + (endTime - startTime));
    }

    public static long gcd(long a, long b) {
        long t;
        while (b!=0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}

package problems.reallyOld;

/*
 * Correct: 6857
 */
public class Problem3LargestPrimeFactor {

    public static void main(String[] args) {
        long base = 600851475143L;
        long test = base;
        int i = 2;
        while (i <= test) {
            while (test % i == 0)
                test = test / i;
            i++;
        }
        System.out.println("largest prime factor of " + base + " is " + --i);
    }

}

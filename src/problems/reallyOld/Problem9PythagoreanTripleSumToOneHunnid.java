package problems.reallyOld;

/**
 * Correct: 31875000
 */
public class Problem9PythagoreanTripleSumToOneHunnid {

    //brute force solution
    public static void main(String[] args) {
        int s = 1000;
        int a, b, c;
        for (a = 1; a < s/3; a++) {
            for (b = a + 1; b < s/2; b++) {
                c = s - a - b;
                if (c*c == a*a + b*b) {
                    System.out.println("Triple found: " + a + ", " + b + ", " + c);
                    System.out.println("Product is: " + a*b*c);
                }
            }
        }

    }

}

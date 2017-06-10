package euler000To099.euler001To009;

/**
 * Correct: 25164150
 */
public class Problem6DifferenceSumOfSquaresAndSquareOfSum {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 2; i <= 100; i++) {
            for (int j = 1; j < i; j++) {
                sum += i*j;
            }
        }
        System.out.println("Difference is " + 2*sum);
    }

    public static int slick(int x) {
        int sumOfSquares = x*(x+1)*(2*x+1)/6;
        int sum = x*(x+1)/2;
        int squareOfSum = sum*sum;
        return squareOfSum - sumOfSquares;
    }
}

package euler000To099.euler001To009;

/*
 * Correct: 4613732
 */
public class Problem2EvenFibonacciNumbersUnderFourMillion {

    public static void main(String[] args) {
        long f1 = 1;
        long f2 = 1;
        long f3;
        long sum = 0;
        while (f2 < 4000000) {
            f3 = f1 + f2;
            if (f3 % 2 == 0)
                sum += f3;
            f1 = f2;
            f2 = f3;
        }
        System.out.println("Fibonacci sum is " + sum);
    }
}
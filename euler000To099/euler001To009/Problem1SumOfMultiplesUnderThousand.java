package euler000To099.euler001To009;

/*
 * Correct: 233168
 */
public class Problem1SumOfMultiplesUnderThousand {

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 15; i < 1000; i += 15) {
            sum += i;
        }
        System.out.println("Sum is " + sum);
    }
}

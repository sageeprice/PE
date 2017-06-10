package euler300To350;

/**
 * Created by Sage on 9/14/2015.
 *
 * Lol XOR brute force. Forums say you can use Fibonacci but for this scale whatever, XOR is fast enough.
 */
public class Problem301Nim {

    public static void main(String[] args) {
        long wins = 0;
        long cap = (long) Math.pow(2, 30);
        for (int i = 1; i <= cap; i++) {
            wins += (i ^ (2*i) ^ (3*i)) == 0 ? 1 : 0;
        }
        System.out.println("Number of losses: "+wins);
    }
}

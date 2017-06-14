package euler100To199.euler180To189;

/**
 * Created by Sage on 6/13/2017.
 */
public class Problem188Hyperexponentiation {

    private static final long NUM = 1777;
    private static final long MOD = 100_000_000;
    private static final long POWER = 1855;

    public static void main(String[] args) {
        long[] numPowers = new long[(int)MOD];
        numPowers[0] = 1;
        numPowers[1] = NUM;
        for (int i = 2; i < MOD; i++) {
            numPowers[i] = numPowers[i-1] * NUM % MOD;
        }
        System.out.println("Done");

        int x = (int) NUM;
        for (int i = 1; i < POWER; i++) {
            x = (int) numPowers[x];
        }
        System.out.println(x);
    }
}

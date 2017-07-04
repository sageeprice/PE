package problems.incomplete;

import java.util.HashSet;
import java.util.Set;

public class Problem371Sim {

    private static final int SIMS = 100000;
    private static final int SET_SIZE = 999;

    public static void main(String[] args) {
        int sum = 0;
        int count;
        for (int i = 0; i < SIMS; i++) {
            count = 0;
            Set<Integer> nums = new HashSet<>();
            while (true) {
                count++;
                int x = (int) (Math.random() * SET_SIZE);
                nums.add(x);
                if (nums.contains(SET_SIZE-x)) {
                    break;
                }
            }
            sum += count;
        }
        System.out.println(1.0* sum / SIMS);
    }
}

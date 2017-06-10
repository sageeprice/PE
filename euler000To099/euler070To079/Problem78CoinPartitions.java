package euler000To099.euler070To079;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sage on 9/11/2015.
 *
 * The problem is to find the first number n such that p(n) (where p is the partition function)
 * is a multiple of 1000000.
 *
 * Solution using generating function for p(n), given here http://mathworld.wolfram.com/PartitionFunctionP.html
 * as formula 11.
 *
 * Solution is 55374, runs in a second or so.
 */
public class Problem78CoinPartitions {

    public static void main(String[] args) {
        List<Integer> partitions = new ArrayList<>();

        // start with the num partitions for 0,1
        // anything < 0 has 0 partitions by convention
        partitions.add(1);
        partitions.add(1);

        int index = 1;
        int pentNum;
        int p;

        do {
            index++;
            p = 0;

            for (int i = 1; i < index; i++) {
                pentNum = i*(3*i-1)/2;
                if (pentNum > index)
                    break;
                p += Math.pow(-1, i+1) * partitions.get(index - pentNum);
                p %= 1000000;

                pentNum = i*(3*i+1)/2;
                if (pentNum > index)
                    break;
                p+= Math.pow(-1, i+1) * partitions.get(index - pentNum);
                p %= 1000000;
            }

            partitions.add(p);
            //System.out.println("Added entry " + index + ": " + partitions.get(index));
        } while (partitions.get(index)%1000000 != 0);

        System.out.println("Solution found at index: " + index);
    }
}

package euler000To099.euler090To099;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Sage on 9/10/2015.
 *
 * Problem requests that from a file of integer pairs x,y, you select the line with the largest
 * value of x^y. A simple solution is to instead of calculating x^y, just calculate y * log(x).
 *
 * Solution very fast, line # is 709
 */
public class Problem99LargestExponential {

    public static void main(String[] args) throws Exception {

        double prod;
        double best = 0;
        long x,y;
        int bestLine = 0;
        int lineIndex = 1;
        Logger logger = Logger.getLogger(Problem99LargestExponential.class);
        BasicConfigurator.configure();

        for (String line : Files.readAllLines(Paths.get("C:\\Users\\Sage\\workspace\\SageKatas\\eulerTxtFiles\\p099_base_exp.txt"))) {
            String[] nums = line.split(",");
            x = Long.valueOf(nums[0]);
            y = Long.valueOf(nums[1]);
            prod = Math.log10(x) * y;
            if (prod > best) {
                best = prod;
                bestLine = lineIndex;
                logger.info("New best encountered on line: " + bestLine);
            }
            lineIndex++;
        }
        logger.info("Best line was: " + bestLine);
    }
}
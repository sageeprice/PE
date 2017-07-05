import org.junit.Test;
import problems.impl.Euler084;

import static org.junit.Assert.assertEquals;

/**
 * Validates tests that are flaky.
 */
public class EulerFlakyTests {

    @Test
    public void testFlakes() {
        // Flakes due to inconsistencies across runs. Randomly plays Monopoly.
        assertEquals(new Euler084().solve(), "101524");
    }
}

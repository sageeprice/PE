import org.junit.Test;
import problems.impl.Euler001;
import problems.impl.Euler002;
import problems.impl.Euler003;

import static junit.framework.TestCase.assertEquals;

/** Tests to verify problems complete successfully. */
public class EulerTest {

    @Test
    public void testFastProblems() {
        assertEquals(new Euler001().solve(), "233168");
        assertEquals(new Euler002().solve(), "4613732");
        assertEquals(new Euler003().solve(), "6857");
    }
}

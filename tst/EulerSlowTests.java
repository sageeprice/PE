import org.junit.Test;
import problems.impl.Euler060;
import problems.impl.Euler070;
import problems.impl.Euler094;
import problems.impl.Euler095;
import problems.impl.Euler136;
import problems.impl.Euler145;
import problems.impl.Euler146;
import problems.impl.Euler153;
import problems.impl.Euler179;
import problems.impl.Euler188;
import problems.impl.Euler206;
import problems.impl.Euler357;
import problems.impl.Euler387;
import problems.impl.Euler407;
import problems.impl.Euler429;
import problems.impl.Euler504;
import problems.impl.Euler351;
import problems.impl.Euler122;
import problems.impl.Euler118;
import problems.impl.Euler135;
import problems.impl.Euler132;
import problems.impl.Euler186;
import problems.impl.Euler510;
import problems.impl.Euler293;

import static org.junit.Assert.assertEquals;

/**
 * Validates slow running tests. Mainly used for keeping track
 * of inefficient tests.
 */
public class EulerSlowTests {

  @Test
  public void testSlowProblems() {
    assertEquals(new Euler060().solve(), "26033");
    assertEquals(new Euler070().solve(), "8319823");
    assertEquals(new Euler094().solve(), "518408346");
    // Takes ~70 seconds. Very slow.
    assertEquals(new Euler095().solve(), "14316");
    // ~2 seconds
    assertEquals(new Euler118().solve(), "44680");
    // Takes ~30 seconds.
    assertEquals(new Euler122().solve(), "1582");
    // ~9 seconds
    assertEquals(new Euler132().solve(), "843296");
    // ~4 seconds
    assertEquals(new Euler135().solve(), "4989");
    // ~90 seconds
    assertEquals(new Euler136().solve(), "2544559");
    // 8-9 seconds.
    assertEquals(new Euler145().solve(), "608720");
    // Takes ~70 seconds.
    assertEquals(new Euler146().solve(), "676333270");
    // 10 seconds.
    assertEquals(new Euler153().solve(), "17971254122360635");
    // ~2 seconds
    assertEquals(new Euler179().solve(), "986262");
    // ~5 seconds
    assertEquals(new Euler186().solve(), "2325629");
    // ~2 seconds.
    assertEquals(new Euler188().solve(), "95962097");
    // ~5 seconds.
    assertEquals(new Euler206().solve(), "1389019170");
    // ~15 seconds
    assertEquals(new Euler293().solve(), "2209");
    // ~12 seconds
    assertEquals(new Euler351().solve(), "11762187201804552");
    // ~6 seconds
    assertEquals(new Euler357().solve(), "1739023853137");
    // <6 minutes!!!
    assertEquals(new Euler387().solve(), "147534623725724718");
    // 2 seconds
    assertEquals(new Euler387().solve(), "696067597313468");
    // 2 seconds
    assertEquals(new Euler429().solve(), "98792821");
    // 40 seconds
    assertEquals(new Euler407().solve(), "39782849136421");
    // 2 seconds
    assertEquals(new Euler504().solve(), "694687");
    // ~5 seconds
    assertEquals(new Euler510().solve(), "315306518862563689");

  }
}

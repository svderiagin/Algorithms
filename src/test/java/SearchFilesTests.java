import org.testng.annotations.Test;
import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


public class SearchFilesTests {

    @Test
    public void searchJpg() {
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        String workingDirectory = System.getProperty("user.dir");

        System.out.println("Files: ");
        Algorithms.searchFile(new File(workingDirectory), ".java");
    }

    @Test
    public void calcFactorial() {
        assertEquals(Algorithms.calcFactorial(4), 24);
    }

    @Test
    public void checkPalindrome() {
        assertTrue(Algorithms.isPalindrome(""));
        assertTrue(Algorithms.isPalindrome(" "));
        assertTrue(Algorithms.isPalindrome("asddsa"));
        assertTrue(Algorithms.isPalindrome("asdsa"));
        assertTrue(Algorithms.isPalindrome("a s d d s a"));
        assertFalse(Algorithms.isPalindrome("qwerty"));

    }

    /**
     * AA    =                                 (26**1 *  1)  +  (26**0 * 20)     = 27
     * JA    =                                 (26**1 * 10)  +  (26**0 *  1)     = 261
     * ZZ    =                                 (26**1 * 26)  +  (26**0 * 26)     = 702
     * AAA   =                 (26**2 * 1)  +  (26**1 *  1)  +  (26**0 *  1)     = 703
     * ABC   =                 (26**2 * 1)  +  (26**1 *  2)  +  (26**0 * 26)     = 731
     * ZTJ   =                 (26**2 * 1)  +  (26**1 * 20)  +  (26**0 * 10)     = 18106
     * BCDE  = (26**3 * 2)  +  (26**2 * 3)  +  (26**1 *  4)  +  (26**0 *  5)     = 37289
     *
     */
    @Test
    public void calcStringAsCharValues() {
        assertEquals(Algorithms.calcCharValues("AA"), 27);
        assertEquals(Algorithms.calcCharValues("JA"), 261);
        assertEquals(Algorithms.calcCharValues("ZZ"), 702);
        assertEquals(Algorithms.calcCharValues("AAA"), 703);
        assertEquals(Algorithms.calcCharValues("ABC"), 731);
        assertEquals(Algorithms.calcCharValues("ZTJ"), 18106);
        assertEquals(Algorithms.calcCharValues("BCDE"), 37289);
    }
}

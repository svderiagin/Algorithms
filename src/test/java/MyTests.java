import Algorithm.TwoSum;
import org.testng.annotations.Test;
import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


public class MyTests {

    @Test
    public void twoSum() {
        // array should be sorted
        int[] array = new int[]{-3, 0, 1, 3, 4};
        assertEquals(TwoSum.loop(array, 5), new int[]{1, 4});
        assertEquals(TwoSum.hashSet(array, 5), new int[]{1, 4});
        assertEquals(TwoSum.binarySearch(array, 5), new int[]{1, 4});
        assertEquals(TwoSum.twoPointers(array, 5), new int[]{1, 4});
        array = new int[]{-3, 0, 1, 3, 30};
        assertEquals(TwoSum.twoPointersClosestValues(array, 5), new int[]{1, 3});
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
        assertEquals(Algorithms.calcExcelCharValues("AA"), 27);
        assertEquals(Algorithms.calcExcelCharValues("JA"), 261);
        assertEquals(Algorithms.calcExcelCharValues("ZZ"), 702);
        assertEquals(Algorithms.calcExcelCharValues("AAA"), 703);
        assertEquals(Algorithms.calcExcelCharValues("ABC"), 731);
        assertEquals(Algorithms.calcExcelCharValues("ZTJ"), 18106);
        assertEquals(Algorithms.calcExcelCharValues("BCDE"), 37289);
    }

    @Test
    public void searchJpg() {
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        String workingDirectory = System.getProperty("user.dir");

        System.out.println("Files: ");
        Algorithms.searchFile(new File(workingDirectory), ".java");
    }
}

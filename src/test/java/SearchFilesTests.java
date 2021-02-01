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
}

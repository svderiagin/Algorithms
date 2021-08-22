import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Algorithms {

    public static boolean isPalindrome(String value) {
        boolean result = true;
        StringBuilder inputString = new StringBuilder(value);
        for (int i = 0; i < value.length(); i++) {
            if (!(inputString.charAt(i) == inputString.charAt(inputString.length() - i - 1))) {
                result = false;
            }
        }
        return result;
    }

    public static long calcFactorial(int value) {
        if (value > 0) {
            if (value == 1) {
                return 1;
            } else {
                return value * calcFactorial(value - 1);
            }
        } else return 0;
    }

    public static void searchFile(File rootFile, String fileName) {
        if (rootFile.isDirectory()) {
            File[] files = rootFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    searchFile(file, fileName);
                }
            }
        } else if (rootFile.getName().toLowerCase().endsWith(fileName)) {
            System.out.println(rootFile.getName());
        }
    }

    /**
     * CONDITION:
     * A 1
     * B 2
     * C 3
     * ..
     * AA 27
     * AB 28
     * …
     * ZZ
     * input parameters it is a string and you need find a number of this string.
     * Ex. If in function send a sting as “AB”, you function will return number 27.
     *
     * SOLUTION:
     * **AA  =                                (26**1 *  1)  +  (26**0 * 20)     = 27
     * **JA  =                                (26**1 * 10)  +  (26**0 *  1)     = 261
     * **ZZ  =                                (26**1 * 26)  +  (26**0 * 26)     = 702
     * *AAA  =                (26**2 * 1)  +  (26**1 *  1)  +  (26**0 *  1)     = 703
     * *ABC  =                (26**2 * 1)  +  (26**1 *  2)  +  (26**0 * 26)     = 731
     * *ZTJ  =                (26**2 * 1)  +  (26**1 * 20)  +  (26**0 * 10)     = 18106
     * BCDE = (26**3 * 2)  +  (26**2 * 3)  +  (26**1 *  4)  +  (26**0 *  5)     = 37289
     *
     * @param value int
     */
    public static int calcCharValues(String value) {
        int result = 0;
        int offset = 64;
        char[] valueArr = value.toUpperCase().toCharArray();
        for (int i = 0; i < value.length(); i++) {
            int index = valueArr[i] - offset;
            result += Math.pow(26, valueArr.length - i - 1) * index;
        }
        return result;
    }
}

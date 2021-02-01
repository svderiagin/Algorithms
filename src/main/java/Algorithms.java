import java.io.File;

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
}

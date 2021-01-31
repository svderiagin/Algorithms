
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;

public class SearchFilesTests {

    @Test
    public static void searchJpg() {
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        String workingDirectory = System.getProperty("user.dir");

        ArrayList<File> filesList = new ArrayList<>();
        Main.searchFiles(new File(workingDirectory), filesList);

        System.out.println("Found files: ");
        for (File file : filesList) {
            System.out.println(file.getName());
        }
    }
}

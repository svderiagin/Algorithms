import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
    }

    public static void searchFiles(File rootFile, ArrayList<File> filesList) {
        if (rootFile.isDirectory()) {
            File[] fileList = rootFile.listFiles();
            if (fileList != null) {
                for (File file : fileList) {
                    if (file.isDirectory()) {
                        searchFiles(file, filesList);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".jpg")) {
                            filesList.add(file);
                        }
                    }
                }
            }
        }
    }
}

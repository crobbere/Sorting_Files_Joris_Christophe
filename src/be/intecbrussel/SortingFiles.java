package be.intecbrussel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class SortingFiles {

    static Path originalDirectory = Paths.get("C:/Users/Joris/Data/unsorted");
    static File[] files = new File(String.valueOf(originalDirectory)).listFiles();
    static List<String> content = new ArrayList<>();

    public static void main(String[] args) {

        Path summaryFile = originalDirectory.resolve("summary.txt");
        content.add("name    |    " + "Writeable     |     " + "Readable      |");
        content.add("----------------------------------------------------------");

        showAllFiles(files);
        checkFilesForExtension(files);
        checkFileInfo(files);
        writeToSummaryFile(summaryFile);
        printSummaryFile();
    }

    // show all files
    public static void showAllFiles(File[] files) {
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("Directory: " + file.getName());
                    showAllFiles(file.listFiles()); // Calls same method again.
                } else {
                    System.out.println("File: " + file.getName());
                }
            }
        }
    }

    // scan files for extension
    // create map based on extension
    // put files with that extension in the right map
    public static void checkFilesForExtension(File[] files) {
        for (File file : files) {
            if (file.getName().contains(".")) {
                try {
                    Path newDirectory = Path.of(originalDirectory + "/" + file.getName().substring(file.getName().lastIndexOf(".") + 1));
                    Files.createDirectories(newDirectory);
                    Files.copy(Path.of(file.getAbsolutePath()), Path.of(newDirectory + File.separator + file.getName()));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void checkFileInfo(File[] files) {
        for (File file : files) {
            boolean isWritable = file.setWritable(true);
            boolean isReadadble = file.setReadable(true);

            content.add(file.getName() + ":" + "Writeable: " + isWritable + "|" + "Readable: " + isReadadble);
        }
    }

    // create summary map with overview
    public static void writeToSummaryFile(Path summaryFile) {
        try {
            Files.write(summaryFile, content, Charset.defaultCharset(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printSummaryFile() {
        content.forEach(System.out::println);
    }

}


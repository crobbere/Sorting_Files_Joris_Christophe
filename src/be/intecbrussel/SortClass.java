package be.intecbrussel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortClass {
    Path originalDirectory = Paths.get("C:/Users/Joris/Data/unsorted/unsorted");
    Path destinationDirectory = Paths.get("home/test/test");


    Stream<Path> pathStream;

    private void listOfExtensions(Path originalDirectory){
        try {
            pathStream = Files.walk(originalDirectory);
            List<String> result = pathStream.sorted()
                    .map(Path::toString)
                    .filter(e -> e.endsWith(Objects.requireNonNull(findExtension(e))))
                    .collect(Collectors.toList());
            for(String f : result){
                Files.createDirectory(destinationDirectory);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String findExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex == -1) {
            return null;
        }
        return (fileName.substring(lastIndex + 1));
    }

}

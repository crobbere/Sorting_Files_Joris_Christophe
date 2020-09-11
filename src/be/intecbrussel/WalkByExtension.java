package be.intecbrussel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class WalkByExtension {

    String dirName = "Downloads/unsorted";

    Stream<Path> wma;
    Stream<Path> txt;

    {
        try {
            wma = Files.walk((Paths.get(dirName)))
                       .filter(o -> o.endsWith(".wma"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    {
        try {
            txt = Files.walk((Paths.get(dirName)))
                       .filter(o -> o.endsWith(".txt"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

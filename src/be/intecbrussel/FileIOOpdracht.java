package be.intecbrussel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

    public class FileIOOpdracht {
        public static void main(String[] args) {

            // Hier kunt ge alle .extensies invullen om te filteren
            String extension = ".jpg";

            Path originalDirectory = Paths.get("C:/Users/Joris/Data/unsorted/unsorted");
            Path destinationDirectory = originalDirectory.resolve(extension);


            FileFilter fileFilter = new FileFilter(extension);

            String[] listOfCertainFiles = originalDirectory.toFile().list(fileFilter);


            assert listOfCertainFiles != null;
            if (listOfCertainFiles.length == 0) {
                System.out.println("there are no " + extension + " files in the unsorted map");
                return;
            } else {
                try {
                    Files.createDirectories(destinationDirectory);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (String file : listOfCertainFiles) {
                    Path parentDirectory = Path.of(destinationDirectory + File.separator + file);
                    try {
                        Files.createDirectories(parentDirectory);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(parentDirectory);
                }
            }


            try (Stream<Path> paths = Files.walk(originalDirectory)){
                paths.sorted()
                        .filter(e-> e.endsWith(".txt"))
                        .collect(Collectors.toUnmodifiableList())
                        .forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    class FileFilter implements FilenameFilter {

        private final String fileExtension;

        public FileFilter(String fileExtension) {
            this.fileExtension = fileExtension;
        }

        @Override
        public boolean accept(File dir, String fileName) {
            return (fileName.endsWith(this.fileExtension));
        }

    }



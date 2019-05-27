package sample.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static void writeToFile(String text, String filename) {
        try {
            Files.write(Paths.get(filename), text.getBytes());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

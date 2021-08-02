package com.dmilut;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Util {

    public static ArrayList<String> readFile(Path path) throws IOException {
        return new ArrayList<>(Files.readAllLines(path));
    }

    public static void writeFile(Path path, String message, int key) throws IOException {
        ArrayList<String> content = new ArrayList<>();
        content.add(Integer.toString(key));
        content.add(message);

        Files.write(path, content, StandardCharsets.UTF_8);
    }
}

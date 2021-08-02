package com.dmilut;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Util {

    public static ArrayList<String> readFile(Path path) throws IOException {

        return new ArrayList<>(Files.readAllLines(path));
    }

    public static Scanner getScanner() {

        return new Scanner(System.in);
    }

    public static ArrayList<String> getRandomWordByLetter(ArrayList<String> words) {
        int index = new Random().nextInt(words.size());

        return new ArrayList<>(Arrays.asList(words.get(index).split("")));
    }

    public static void writeScoreToFile(Path path, String name, int score) throws IOException {
        ArrayList<String> content = readFile(path);
        String record = name + " " + score;
        AtomicInteger index = new AtomicInteger(-1);

        if (content.isEmpty()) {
            Files.write(path, record.getBytes(StandardCharsets.UTF_8));
        } else {
            IntStream.range(0, content.size())
                    .forEach(tmpIndex -> {
                        if (content.get(tmpIndex).split(" ")[0].equals(name)) {
                            index.set(tmpIndex);
                        }
                    });

            if (index.get() != -1) {
                content.set(index.get(), record);
            } else {
                content.add(record);
            }

            Files.write(path, content, StandardCharsets.UTF_8);
        }
    }
}

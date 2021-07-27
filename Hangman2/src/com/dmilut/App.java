package com.dmilut;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    private static final Path PATH_TO_DICTIONARY = Paths.get("Hangman2/src/resources/dictionary.txt");
    private static final Path PATH_TO_ART = Paths.get("Hangman2/src/resources/art.txt");
    private static final Path PATH_TO_SCORE = Paths.get("Hangman2/src/resources/score.txt");
    private static boolean isContinue = true;
    private static String input = null;
    private static String name = null;
    private static ArrayList<String> letters = null;
    private static ArrayList<Letter> template;
    private static ArrayList<String> missedLetters;
    private static AtomicInteger score;
    private static AtomicInteger bestScore;

    public static void main(String[] args) throws IOException {

        while (isContinue) {
            AtomicBoolean isMissed = new AtomicBoolean(true);

            if (letters == null) {
                letters = Util.getRandomWordByLetter(Util.readFile(PATH_TO_DICTIONARY));
                template = buildTemplate(letters);
                missedLetters = new ArrayList<>();
                score = new AtomicInteger();
                bestScore = new AtomicInteger();

                printTitle();
                System.out.println("Enter your name");
                name = Util.getScanner().nextLine();

                if (!isNewPlayer(name)) {
                    bestScore.set(getScore(name).get());
                    System.out.println("Your best score is " + bestScore);
                }
            }

            System.out.println("Guess a letter");
            input = Util.getScanner().nextLine();

            template
                    .forEach(i -> {
                        if (input.equals(i.getValue())) {
                            i.setVisible(true);
                            score.getAndIncrement();
                            isMissed.set(false);
                        }
                    });

            if (isMissed.get()) {
                missedLetters.add(input);
            }

            printMissedLetters(missedLetters);
            printTemplate(template);

            if (isWin(template).get()) {
                if (bestScore.get() < score.get()) {
                    Util.writeScoreToFile(PATH_TO_SCORE, name, score.get());
                }

                System.out.println("You win! Score is " + score);
                System.out.println("Continue? y/n");

                input = Util.getScanner().nextLine();

                if (input.equals("n")) {
                    isContinue = false;
                } else {
                    letters = null;
                }

            }
        }

    }

    private static AtomicInteger getScore(String name) throws IOException {
        ArrayList<String> rows = Util.readFile(PATH_TO_SCORE);
        AtomicInteger score = new AtomicInteger();

        rows
                .forEach(i -> {
                    if (i.split(" ")[0].equals(name)) {
                        score.set(Integer.parseInt(i.split(" ")[1]));
                    }
                });

        return score;
    }

    private static boolean isNewPlayer(String name) throws IOException {
        ArrayList<String> rows = Util.readFile(PATH_TO_SCORE);
        AtomicBoolean result = new AtomicBoolean(true);

        rows
                .forEach(i -> {
                    if (i.split(" ")[0].equals(name)) {
                        result.set(false);
                    }
                });

        return result.get();
    }

    private static AtomicBoolean isWin(ArrayList<Letter> template) {
        AtomicBoolean result = new AtomicBoolean(true);

        template
                .forEach(i -> {
                    if (!i.isVisible()) {
                        result.set(false);
                    }
                });

        return result;
    }

    private static void printMissedLetters(ArrayList<String> missedLetters) {
        if (!missedLetters.isEmpty()) {
            System.out.println();

            if (missedLetters.contains(input)) {
                System.out.println("New missed letter is " + input);
            }

            System.out.print("Missed letters: ");
            missedLetters
                    .forEach(i -> {
                        System.out.print(i + " ");
                    });
        }
    }

    private static ArrayList<Letter> buildTemplate(ArrayList<String> letters) {
        ArrayList<Letter> template = new ArrayList<>();

        letters
                .forEach(i -> {
                    template.add(new Letter(i, false));
                });

        return template;
    }

    private static void printTitle() throws IOException {
        ArrayList<String> arts = Util.readFile(PATH_TO_ART);

        System.out.println(arts.get(0));
        System.out.println(arts.get(1));
    }

    private static void printTemplate(ArrayList<Letter> template) {
        System.out.println();
        System.out.print("|");

        template
                .forEach(i -> {
                    if (i.isVisible()) {
                        System.out.print(" " + i.getValue() + " ");

                    } else {
                        System.out.print(" * ");
                    }
                    System.out.print("|");
                });
        System.out.println();
    }
}


package com.dmilut;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        boolean isContinue = true;
        boolean isWin = false;
        int numberOfCoincidences;
        String input = null;
        int wordIndex;
        List<String> letters;
        ArrayList<String> attempts;
        ArrayList<String> missedLetters;
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<>();
        words.add("cat");
        words.add("pet");
        words.add("java");
        words.add("dog");
        words.add("linux");

        System.out.println("H A N G M A N\n"
                + "+---+");

        while (isContinue) {
            wordIndex = new Random().nextInt(words.size());
            letters = new ArrayList<>(Arrays.asList(words.get(wordIndex).split("")));
            attempts = new ArrayList<>();
            missedLetters = new ArrayList<>();

            while (!isWin) {

                printAttempts(attempts);

                System.out.println("Guess a letter.");
                try {
                    input = scanner.nextLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                numberOfCoincidences = 0;

                if (letters.contains(input)) {
                    for (String aChar : letters) {
                        if (aChar.equals(input)) {
                            attempts.add("O");
                            numberOfCoincidences++;
                        }
                    }
                } else {
                    attempts.add("|");
                }

                if (numberOfCoincidences == 0) {
                    System.out.println("0 letters");
                    missedLetters.add(input);
                    System.out.println("Missed letters " + missedLetters);
                } else {
                    System.out.println(numberOfCoincidences + " Coincidences!");
                }

                int count = 0;
                for (String item : attempts) {
                    if (item.equals("O")) {
                        count++;
                    }
                }

                if (count == letters.size()) {
                    isWin = true;
                }
            }

            System.out.println("Yes! The secret word is \"" + words.get(wordIndex) + "\"! You have won!\n" +
                    "Do you want to play again? (yes or no)\n");

            try {
                input = scanner.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (input.equals("no")) {
                isContinue = false;
            } else {
                isWin = false;
            }

        }
    }

    private static void printAttempts(ArrayList<String> attempts) {
        for (String item : attempts) {
            if (item.equals("O")) {
                System.out.print("O");
            } else if (item.equals("|")) {
                System.out.print("|");
            }
            System.out.print(" |\n");
        }
    }
}

package com.dmilut;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        boolean isContinue = true;
        Scanner scanner;

        while (isContinue) {
            System.out.println("\033[0;1m" + "Hello! What is your name?");
            String playerName = null;

            try {
                scanner = new Scanner(System.in);
                playerName = scanner.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            int numberOfTrying = 0;

            System.out.println("\033[0;1m" + "Well, " + playerName + ", I am thinking of a number between 1 and 20.\n" +
                    "Take a guess.");
            int number = getRandomNumber();
            int guessNumber = 0;

            try {
                scanner = new Scanner(System.in);
                guessNumber = scanner.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
            }
            numberOfTrying++;

            while (numberOfTrying <= 7) {
                if (guessNumber > number) {
                    printHighMessage();

                    try {
                        scanner = new Scanner(System.in);
                        guessNumber = scanner.nextInt();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    numberOfTrying++;
                } else if (guessNumber < number) {
                    printLowMessage();

                    try {
                        scanner = new Scanner(System.in);
                        guessNumber = scanner.nextInt();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    numberOfTrying++;
                } else {
                    printWinMessage(playerName, numberOfTrying);
                    String confirmation = null;

                    try {
                        confirmation = new Scanner(System.in).nextLine();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (confirmation.equals("n")) {
                        isContinue = false;
                    }
                    break;
                }
            }
        }

    }

    private static int getRandomNumber() {
        return random.nextInt(20) + 1;
    }

    private static void printHighMessage() {
        System.out.println("\033[0;1m" + "Your guess is too high.\n" +
                "Take a guess.");
    }

    private static void printLowMessage() {
        System.out.println("\033[0;1m" + "Your guess is too low.\n" +
                "Take a guess.");
    }

    private static void printWinMessage(String playerName, int numberOfTrying) {
        System.out.println("\033[0;1m" + "Good job, " + playerName + "! You guessed my number in " + numberOfTrying + " guesses!\n" +
                "Would you like to play again? (y or n)");
    }
}

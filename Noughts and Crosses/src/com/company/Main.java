package com.company;

import java.util.*;

public class Main {
    private static String[][] world;
    private static List<List<Integer>> winningOptions;
    private static Scanner scanner;

    public static void main(String[] args) {
        String userSymbol;
        String opponentSymbol;
        boolean isMoveOfUser;
        boolean isContinue = true;
        boolean isWin;

        while (isContinue) {
            buildWorld();
            isWin = false;
            initWinningOptions();
            scanner = new Scanner(System.in);
            printTitle();
            userSymbol = checkUserSymbol().toUpperCase(Locale.ROOT);

            if (userSymbol.equals("O")) {
                isMoveOfUser = true;
                opponentSymbol = "X";
            } else {
                isMoveOfUser = false;
                opponentSymbol = "O";
                System.out.println("The computer will go first.");
            }

            while (!isWin) {
                if (isMoveOfUser) {
                    System.out.println("What is your next move? (1-9)");
                    int input = scanner.nextInt();

                    if (isInputValid(input)) {
                        String[] responseByDigits = String.valueOf(translateInput(input)).split("\\.");
                        world[Integer.parseInt(responseByDigits[0])][Integer.parseInt(responseByDigits[1])] = userSymbol;
                    } else {
                        System.out.println("\nError!");
                    }

                    printWorld();
                }

                if (isWin(userSymbol)) {
                    System.out.println("\nYou win!");
                    break;
                }

                if(getEmptyCells().isEmpty()) {
                    System.out.println("Draw victory!");
                    break;
                }

                System.out.println("\nThe move of the opponent!");

                String[] responseByDigits = String.valueOf(getResponse()).split("\\.");
                world[Integer.parseInt(responseByDigits[0])][Integer.parseInt(responseByDigits[1])] = opponentSymbol;
                isMoveOfUser = true;

                printWorld();

                if (isWin(opponentSymbol)) {
                    System.out.println("You lose!");
                    isWin = true;
                }


            }

            System.out.println("\nDo you want to play again? (yes or no)");
            scanner = new Scanner(System.in);
            isContinue = scanner.nextLine().equals("yes");
        }
    }

    private static String checkUserSymbol() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void printTitle() {
        System.out.println("Welcome to Tic-Tac-Toe!\n" +
                "Do you want to be X or O?");
    }

    private static boolean isWin(String symbol) {
        int count;

        for (List<Integer> list : winningOptions) {
            count = 0;

            for (Integer item : list) {
                String[] translatedInputByDigits = String.valueOf(translateInput(item)).split("\\.");
                String cell = world[Integer.parseInt(translatedInputByDigits[0])][Integer.parseInt(translatedInputByDigits[1])];

                if (symbol.equals(cell)) {
                    count++;
                }
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    private static void initWinningOptions() {
        winningOptions = new ArrayList<>();
        winningOptions.add(Arrays.asList(1, 2, 3));
        winningOptions.add(Arrays.asList(4, 5, 6));
        winningOptions.add(Arrays.asList(7, 8, 9));
        winningOptions.add(Arrays.asList(1, 4, 7));
        winningOptions.add(Arrays.asList(2, 5, 8));
        winningOptions.add(Arrays.asList(3, 6, 9));
        winningOptions.add(Arrays.asList(1, 5, 9));
        winningOptions.add(Arrays.asList(3, 5, 7));
    }

    private static float getResponse() {
        ArrayList<Float> emptyCells = getEmptyCells();
        int attempt = new Random().nextInt(emptyCells.size() - 1);

        return emptyCells.get(attempt);
    }

    private static ArrayList<Float> getEmptyCells() {
        ArrayList<Float> emptyCells = new ArrayList<>();

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                if (world[i][j].equals(" ")) {
                    emptyCells.add((float) i + (float) j / 10);
                }
            }
        }

        return emptyCells;
    }

    private static void buildWorld() {
        world = new String[3][3];

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                world[i][j] = " ";
            }
        }
    }

    private static void printWorld() {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                System.out.print(world[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            if (i < world.length - 1) {
                System.out.println("\n---------");
            }
        }
        System.out.println();
    }

    private static boolean isInputValid(int input) {
        if (input > 0 && input < 10) {
            String[] translatedInputByDigits = String.valueOf(translateInput(input)).split("\\.");
            String field = world[Integer.parseInt(translatedInputByDigits[0])][Integer.parseInt(translatedInputByDigits[1])];

            return field.equals(" ");
        }
        return false;
    }

    private static float translateInput(int input) {
        float translatedInput = 0;

        if (input - 1 < 3) {
            translatedInput = 0F + (float) (input - 1) / 10;
        } else if (input - 1 < 6) {
            translatedInput = 1F + (float) (input - 1 - 3) / 10;
        } else if (input - 1 < 9) {
            translatedInput = 2F + (float) (input - 1 - 6) / 10;
        }
        return translatedInput;
    }
}

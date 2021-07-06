package com.dmilut;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean isContinue = true;
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        while (isContinue) {
            String input = null;
            try {
                input = scanner.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (input.equals("1")) {
                printMessageForChoose1();
                isContinue = false;
            } else if (input.equals("2")) {
                printMessageForChoose2();
                isContinue = false;
            } else {
                System.out.println("Please, try again!");
            }
        }
    }

    private static void printGreeting(){
        System.out.println("\033[0;1m" + "You are in a land full of dragons. In front of you,\n" +
                "you see two caves. In one cave, the dragon is friendly\n" +
                "and will share his treasure with you. The other dragon\n" +
                "is greedy and hungry and will eat you on sight.\n" +
                "Which cave will you go into? (1 or 2)");
    }

    private static void printMessageForChoose1(){
        System.out.println("\033[0;1m" + "Now you are rich!");
    }

    private static void printMessageForChoose2(){
        System.out.println("\033[0;1m" + "You approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Gobbles you down in one bite!");
    }
}

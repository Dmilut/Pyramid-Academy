package com.dmilut;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;


        while (isContinue) {
            World world = World.getInstance();
            boolean isWinOrLose = true;

            world.generateBoard();
            world.printBoard();

            while (isWinOrLose) {
                System.out.println("Please enter direction n/s/w/e");
                String input = null;
                try {
                    input = scanner.nextLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (world.getHuman().move(input)) {
                    System.out.println("Success!");
                } else {
                    System.out.println("Error!");
                }

                world.printBoard();

                ArrayList<Actor> collidedEntity = world.getCollidedEntity();
                if (collidedEntity != null) {
                    fight(collidedEntity);
                    isWinOrLose = false;
                }
            }

            System.out.println("Would you like to continue? yes/no");
            String confirm = null;
            try {
                confirm = scanner.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            isContinue = confirm.equals("yes");
        }

    }

    private static void fight(ArrayList<Actor> collidedEntity) {
        Human human = null;
        Goblin goblin = null;

        for (Actor actor : collidedEntity) {
            if (actor instanceof Human) {
                human = (Human) actor;
            } else if (actor instanceof Goblin) {
                goblin = (Goblin) actor;
            }
        }

        if (human != null && goblin != null) {
            System.out.println("Fight! " + human.toString() + " | " + goblin.toString());

            while (human.getHealth() > 0 && goblin.getHealth() > 0) {
                System.out.println("Goblin attacks human!");
                human.setHealth(human.getHealth() - goblin.getAttackPower());
                System.out.println(human.toString() + " | " + goblin.toString());

                if (human.getHealth() <= 0) {
                    System.out.println("Human is dead!");
                } else {
                    System.out.println("Human attacks goblin!");
                    goblin.setHealth(goblin.getHealth() - human.getAttackPower());
                    System.out.println(human.toString() + " | " + goblin.toString());

                    if (goblin.getHealth() <= 0) {
                        System.out.println("Goblin is dead!");
                    }
                }
            }
        }

    }
}

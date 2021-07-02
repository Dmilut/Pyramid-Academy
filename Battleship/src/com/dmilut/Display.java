package com.dmilut;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Display {
    private static Display instance;
    private final World world = World.getInstance();

    private Display() {
    }

    public static Display getInstance() {
        if (instance == null) {
            instance = new Display();
        }
        return instance;
    }

    public Coordinates enterCoordinate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter coordinate!");
        String[] rawCoordinates = scanner.nextLine().split("");
        scanner.close();

        String[] xByLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

        for (int i = 0; i < xByLetters.length; i++) {
            if (rawCoordinates[0].toUpperCase(Locale.ROOT).equals(xByLetters[i])) {
                return new Coordinates(i, Integer.parseInt(rawCoordinates[1]) - 1);
            }
        }
        return null;
    }

    public void printShotPastGoalMessage() {
        System.out.println("Shot past a goal!");
    }

    public void printGotIntoShipMessage() {
        System.out.println("Shot got into the ship!");
    }

    public void printShipDiedMessage(Ship ship) {
        String type = ship.getType().toString().toLowerCase(Locale.ROOT);
        System.out.print(type + " is sunk!");
    }

    public void printWorld() {
        printHeader(World.COMPUTER_NAME);
        printGrid(world.getComputerBoard().getGrid());
        printFooter(world.getComputerBoard().getShips());

        printHeader(World.playerName);
        printGrid(world.getPlayerBoard().getGrid());
        printFooter(world.getPlayerBoard().getShips());
    }

    private void printHeader(String playerName) {
        System.out.println("\n                 " + playerName + "                 ");
        System.out.println("    A   B   C   D   E   F   G   H   I   J");
        System.out.println("  _________________________________________");
    }

    private void printGrid(Cell[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            if (y == grid.length - 1) {
                System.out.print(y + 1 + "");
            } else {
                System.out.print(y + 1 + " ");
            }
            for (int x = 0; x < grid[0].length; x++) {
                if (x == 0) {
                    System.out.print("|");
                }

                System.out.print(grid[y][x].getSymbol());
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("  -----------------------------------------");
    }

    private void printFooter(ArrayList<Ship> ships) {
        int numberOfCarrier = 0;
        int numberOfBattleship = 0;
        int numberOfDestroyer = 0;
        int numberOfSubmarine = 0;
        int numberOfPatrolBoat = 0;
        for (Ship item : ships) {
            if (item.getType().equals(ShipType.CARRIER)) {
                numberOfCarrier++;
            }
            if (item.getType().equals(ShipType.BATTLESHIP)) {
                numberOfBattleship++;
            }
            if (item.getType().equals(ShipType.DESTROYER)) {
                numberOfDestroyer++;
            }
            if (item.getType().equals(ShipType.SUBMARINE)) {
                numberOfSubmarine++;
            }
            if (item.getType().equals(ShipType.PATROL_BOAT)) {
                numberOfPatrolBoat++;
            }
        }
        System.out.println("Carrier = " + numberOfCarrier);
        System.out.println("Battleship = " + numberOfBattleship);
        System.out.println("Destroyer = " + numberOfDestroyer);
        System.out.println("Submarine = " + numberOfSubmarine);
        System.out.println("Patrol Boat = " + numberOfPatrolBoat);
    }

}

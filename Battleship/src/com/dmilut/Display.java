package com.dmilut;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Display {
    //private static Display instance;
    private static final String[] X_BY_LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private Scanner scanner;

/*    private Display() {
    }

    public static Display getInstance() {
        if (instance == null) {
            instance = new Display();
        }
        return instance;
    }*/

    public Coordinates enterCoordinates() {
        scanner = new Scanner(System.in);
        System.out.println("Please enter coordinates!");

        return translateRawCoordinates(scanner.nextLine());
    }

    public Coordinates translateRawCoordinates(String rawCoordinates) {
        String[] rawCoordinatesArray = rawCoordinates.split("");

        if (rawCoordinatesArray.length == 3) {
            rawCoordinatesArray[1] = rawCoordinatesArray[1] + rawCoordinatesArray[2];
        }

        for (int i = 0; i < X_BY_LETTERS.length; i++) {
            if (rawCoordinatesArray[0].toUpperCase(Locale.ROOT).equals(X_BY_LETTERS[i])) {
                return new Coordinates(i, Integer.parseInt(rawCoordinatesArray[1]) - 1);
            }
        }

        return null;
    }

    public String translateCoordinatesToRaw(Coordinates coordinates) {

        return X_BY_LETTERS[coordinates.getX()] + (coordinates.getY() + 1);
    }

    public void enterPlayerName() {
        scanner = new Scanner(System.in);
        System.out.println("Please enter your name");
        Game.playerName = scanner.nextLine();
    }

    public boolean isConfirm() {
        System.out.println("Would you like to continue? yes/no");
        String confirm = scanner.nextLine();

        return confirm.equals("yes");
    }

    public void printWinMessage(String playerName) {
        System.out.println(playerName + " is win!");
    }

    public void printShotPastGoalMessage(Coordinates coordinates) {
        System.out.println("*** Coordinates " + translateCoordinatesToRaw(coordinates) + " - Shot past a goal!");
    }

    public void printGotIntoShipMessage(Coordinates coordinates) {
        System.out.println("*** Coordinates " + translateCoordinatesToRaw(coordinates) + " - Shot got into the ship!");
    }

    public void printShipDiedMessage(Ship ship) {
        String type = ship.getType().toString().toLowerCase(Locale.ROOT);
        System.out.print(type + " is sunk!");
    }

    public void printBoard(Board board) {
        printHeader(board.getPlayerName());
        printGrid(board.getGrid());
        printFooter(board.getShips());
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

                if (grid[y][x].isVisible()) {
                    System.out.print(grid[y][x].getSymbol());
                } else {
                    System.out.print(" ? ");
                }
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
        System.out.println("*** Carrier = " + numberOfCarrier + " | Battleship = " + numberOfBattleship +
                " | Destroyer = " + numberOfDestroyer + " | Submarine = " + numberOfSubmarine +
                " | Patrol Boat = " + numberOfPatrolBoat);
    }

}

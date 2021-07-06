package com.dmilut;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static final String COMPUTER_NAME = "Computer";
    public static String playerName;
    private static Display display;
    private static World world;

    public static void main(String[] args) {
        boolean isWin;
        boolean isContinue = true;
        Board computerBoard;
        Board playerBoard;

        while (isContinue) {
            isWin = false;
            Random random = new Random();
            display = new Display();

            display.enterPlayerName();
            world = new World();
            computerBoard = world.getComputerBoard();
            playerBoard = world.getPlayerBoard();

            display.printBoard(computerBoard);
            display.printBoard(playerBoard);

            while (!isWin) {
                // Test
/*                Cell[][] computerGrid = world.getComputerBoard().getGrid();
                for (int y = 0; y < computerGrid.length; y++) {
                    for (int x = 0; x < computerGrid[0].length; x++) {
                        shot(world.getComputerBoard(), new Coordinates(y, x));
                    }
                }*/

                shot(computerBoard, display.enterCoordinates());
                display.printBoard(world.getComputerBoard());
                if (computerBoard.getShips().isEmpty()) {
                    display.printWinMessage(COMPUTER_NAME);
                    isWin = true;
                }

                ArrayList<Coordinates> unKnownCoordinates = getUnKnownCoordinates(playerBoard);
                shot(playerBoard, unKnownCoordinates.get(random.nextInt(unKnownCoordinates.size())));
                display.printBoard(playerBoard);
                if (playerBoard.getShips().isEmpty()) {
                    display.printWinMessage(playerName);
                    isWin = true;
                }

                if (isWin) {
                    isContinue = display.isConfirm();
                }
            }
        }
    }

    private static ArrayList<Coordinates> getUnKnownCoordinates(Board board) {
        ArrayList<Coordinates> unKnownCoordinates = new ArrayList<>();
        Cell[][] grid = board.getGrid();

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (!grid[x][y].isKnown()) {
                    unKnownCoordinates.add(grid[x][y].getCoordinates());
                }
            }
        }

        return unKnownCoordinates;
    }

    private static void shot(Board board, Coordinates coordinates) {
        Cell[][] grid = board.getGrid();
        Cell cell = grid[coordinates.getY()][coordinates.getX()];
        Ship ship = cell.getShip();

        if (ship == null) {
            cell.setSymbol(" . ");
            display.printShotPastGoalMessage(coordinates);
        } else {
            int countDiedPart = 0;

            for (ShipPart shipPart : ship.getShipParts()) {
                if (shipPart.getCoordinates().equals(coordinates)) {
                    cell.setSymbol(" X ");
                    shipPart.setDied(true);
                }
                if (shipPart.isDied()) {
                    countDiedPart++;
                }
            }
            display.printGotIntoShipMessage(coordinates);

            if (countDiedPart == ship.getShipParts().size()) {
                board.getShips().remove(ship);
                display.printShipDiedMessage(ship);
            }
        }

        cell.setVisible(true);
    }
}

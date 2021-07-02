package com.dmilut;

public class Game {
    private static World world;
    private static Display display;

    public static void main(String[] args) {
        World.playerName = "Test";
        world = World.getInstance();
        display = Display.getInstance();

        Display display = Display.getInstance();
        display.printWorld();

/*        // Test
        Cell[][] computerGrid = world.getComputerBoard().getGrid();
        for (int y = 0; y < computerGrid.length - 5; y++) {
            for (int x = 0; x < computerGrid[0].length - 5; x++) {
                shot(world.getComputerBoard(), new Coordinates(y, x));

            }
        }*/

        shot(world.getComputerBoard(), display.enterCoordinate());

        display.printWorld();
    }

    private static void shot(Board board, Coordinates coordinates) {
        Cell[][] grid = board.getGrid();
        Cell cell = grid[coordinates.getY()][coordinates.getX()];
        Ship ship = cell.getShip();

        if (ship == null) {
            cell.setSymbol(" . ");
            display.printShotPastGoalMessage();
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
            display.printGotIntoShipMessage();

            if (countDiedPart == ship.getShipParts().size()) {
                board.getShips().remove(ship);
                display.printShipDiedMessage(ship);
            }

        }
    }
}

package com.dmilut;

import java.util.Random;

public class World {
    //private static World instance;
    private Board computerBoard;
    private Board playerBoard;

    public World() {
        init();
    }

    private void init() {
        computerBoard = new Board(Game.COMPUTER_NAME);
        setUpShips(computerBoard);
        playerBoard = new Board(Game.playerName);
        setUpShips(playerBoard);
    }

    private void setUpShips(Board board) {
        setUpShip(board, ShipType.CARRIER);
        setUpShip(board, ShipType.BATTLESHIP);
        setUpShip(board, ShipType.BATTLESHIP);
        setUpShip(board, ShipType.DESTROYER);
        setUpShip(board, ShipType.DESTROYER);
        setUpShip(board, ShipType.DESTROYER);
        setUpShip(board, ShipType.SUBMARINE);
        setUpShip(board, ShipType.SUBMARINE);
        setUpShip(board, ShipType.SUBMARINE);
        setUpShip(board, ShipType.SUBMARINE);
        setUpShip(board, ShipType.PATROL_BOAT);
        setUpShip(board, ShipType.PATROL_BOAT);
        setUpShip(board, ShipType.PATROL_BOAT);
        setUpShip(board, ShipType.PATROL_BOAT);
        setUpShip(board, ShipType.PATROL_BOAT);
    }

    private void setUpShip(Board board, ShipType type) {
        Random random = new Random();
        Cell[][] grid = board.getGrid();
        int headX;
        int headY;
        Coordinates headCoordinates;
        boolean isVertical;
        boolean isPlaceEmpty;
        Ship ship = new Ship(type);
        ShipPart shipPart;

        do {
            isVertical = random.nextBoolean();

            if (isVertical) {
                headX = random.nextInt(Board.SIZE);
                headY = random.nextInt(Board.SIZE - type.size);
            } else {
                headX = random.nextInt(Board.SIZE - type.size);
                headY = random.nextInt(Board.SIZE);
            }

            headCoordinates = new Coordinates(headX, headY);

            isPlaceEmpty = isPlaceEmpty(board, headCoordinates, type, isVertical);
        } while (!isPlaceEmpty);

        for (int i = 0; i < type.size; i++) {
            shipPart = ship.getShipParts().get(i);

            if (i == 0) {
                shipPart.setCoordinates(headCoordinates);
                grid[headCoordinates.getY()][headCoordinates.getX()].setShip(ship);
                grid[headCoordinates.getY()][headCoordinates.getX()].setSymbol(" 0 ");
            } else if (isVertical) {
                shipPart.setCoordinates(new Coordinates(headX, headY + i));
                grid[headCoordinates.getY() + i][headCoordinates.getX()].setShip(ship);
                grid[headCoordinates.getY() + i][headCoordinates.getX()].setSymbol(" 0 ");
            } else {
                shipPart.setCoordinates(new Coordinates(headX + i, headY));
                grid[headCoordinates.getY()][headCoordinates.getX() + i].setShip(ship);
                grid[headCoordinates.getY()][headCoordinates.getX() + i].setSymbol(" 0 ");
            }
        }

        board.getShips().add(ship);
    }

    private boolean isPlaceEmpty(Board board, Coordinates headCoordinates, ShipType type, boolean isVertical) {
        Cell[][] grid = board.getGrid();

        for (int i = 0; i < type.size; i++) {
            if (isVertical) {
                if (grid[headCoordinates.getY() + i][headCoordinates.getX()].getShip() != null) {
                    return false;
                }
            } else {
                if (grid[headCoordinates.getY()][headCoordinates.getX() + i].getShip() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board getComputerBoard() {
        return computerBoard;
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }
}

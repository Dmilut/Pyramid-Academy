package com.dmilut;

import java.util.ArrayList;

public class Board {
    public static final short SIZE = 10;
    private final Cell[][] grid;
    private ArrayList<Ship> ships;
    private String playerName;

    public Board(String playerName) {
        this.playerName = playerName;
        this.grid = generateGrid(playerName);
        this.ships = new ArrayList<>();
    }

    private Cell[][] generateGrid(String playerName) {
        boolean isVisible;
        Cell[][] grid = new Cell[SIZE][SIZE];

        for (int x = 0; x < grid[0].length; x++) {
            for (int y = 0; y < grid.length; y++) {
                if (playerName.equals(Game.COMPUTER_NAME)) {
                    isVisible = false;
                } else {
                    isVisible = true;
                }
                grid[x][y] = new Cell(new Coordinates(x, y), isVisible);
            }
        }

        return grid;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }
}

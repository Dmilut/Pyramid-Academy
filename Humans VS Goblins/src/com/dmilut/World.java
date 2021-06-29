package com.dmilut;

import java.util.ArrayList;
import java.util.Random;

public class World {
    public static final short WORLD_SIZE = 5;
    private Cell[][] board;
    private static World instance;

    private World() {
        this.board = new Cell[WORLD_SIZE][WORLD_SIZE];
    }

    public static World getInstance() {
        if (instance == null) {
            instance = new World();
        }
        return instance;
    }

    public void printBoard() {
        ArrayList<Actor> collidedEntity = getCollidedEntity();
        System.out.println("\n------------------------");
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                if (j == 0) {
                    System.out.print("|");
                }
                if (!this.board[i][j].getActors().isEmpty() && collidedEntity == null) {
                    System.out.print(" " + this.board[i][j].getActors().get(0).getSymbol() + " ");
                } else if (!this.board[i][j].getActors().isEmpty() && collidedEntity != null) {
                    System.out.print(" " + "☠" + " ");
                } else {
                    System.out.print("   ");
                }
                System.out.print("|");
            }
            System.out.println("\n------------------------");
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public Cell getCell(Coordinates coordinates) {
        return board[coordinates.getX()][coordinates.getY()];
    }

    public Human getHuman() {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (!cell.getActors().isEmpty()) {
                    for (Actor actor : cell.getActors()) {
                        if (actor instanceof Human) {
                            return (Human) actor;
                        }
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Actor> getCollidedEntity() {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell.getActors().size() > 1) {
                    return cell.getActors();
                }
            }
        }
        return null;
    }

    public void generateBoard() {
        Random random = new Random();
        board = new Cell[WORLD_SIZE][WORLD_SIZE];

        for (short i = 0; i < board.length; i++) {
            for (short j = 0; j < board[0].length; j++) {
                board[i][j] = new Cell(new Coordinates(i, j));
            }
        }

        Coordinates coordinates = new Coordinates(random.nextInt(WORLD_SIZE - 1), random.nextInt(WORLD_SIZE - 1));
        board[coordinates.getX()][coordinates.getY()].getActors().add(new Human(coordinates));

        ArrayList<Cell> emptyCells = getEmptyCells();
        int index = random.nextInt(emptyCells.size());
        coordinates = emptyCells.get(index).getCoordinates();
        board[coordinates.getX()][coordinates.getY()].getActors().add(new Goblin(coordinates));
    }

    private ArrayList<Cell> getEmptyCells() {
        ArrayList<Cell> result = new ArrayList<>();
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell.getActors().isEmpty()) {
                    result.add(cell);
                }
            }
        }

        return result;
    }


}

package com.dmilut;

public class Cell {
    private final Coordinates coordinates;
    private Ship ship;
    private boolean isKnown;
    private boolean isVisible;
    private String symbol;

    public Cell(Coordinates coordinates, boolean isVisible) {
        this.coordinates = coordinates;
        this.isVisible = isVisible;
        this.symbol = "   ";
    }

    public Cell(Coordinates coordinates, String symbol) {
        this.coordinates = coordinates;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean isKnown() {
        return isKnown;
    }

    public void setKnown(boolean known) {
        isKnown = known;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

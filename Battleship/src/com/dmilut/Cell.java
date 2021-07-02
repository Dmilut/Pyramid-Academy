package com.dmilut;

public class Cell {
    private final Coordinates coordinates;
    private Ship ship;
    private ShipPart shipPart;
    private boolean isKnown;
    private String symbol;

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

    public ShipPart getShipPart() {
        return shipPart;
    }

    public void setShipPart(ShipPart shipPart) {
        this.shipPart = shipPart;
    }

    public boolean isKnown() {
        return isKnown;
    }

    public void setKnown(boolean known) {
        isKnown = known;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

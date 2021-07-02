package com.dmilut;

public enum ShipType {
    CARRIER(5, 1),
    BATTLESHIP(4, 2),
    DESTROYER(3, 3),
    SUBMARINE(2, 4),
    PATROL_BOAT(1, 5);

    public final int size;
    public final int number;

    ShipType(int size, int number) {
        this.size = size;
        this.number = number;
    }
}

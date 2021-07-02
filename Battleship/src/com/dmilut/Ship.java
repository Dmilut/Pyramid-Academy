package com.dmilut;

import java.util.ArrayList;

public class Ship {
    private final ShipType type;
    private ArrayList<ShipPart> shipParts;

    public Ship(ShipType type) {
        this.type = type;
        if (this.type.equals(ShipType.CARRIER)) {
            shipParts = buildShipParts(ShipType.CARRIER.size);
        }
        if (this.type.equals(ShipType.BATTLESHIP)) {
            shipParts = buildShipParts(ShipType.BATTLESHIP.size);
        }
        if (this.type.equals(ShipType.DESTROYER)) {
            shipParts = buildShipParts(ShipType.DESTROYER.size);
        }
        if (this.type.equals(ShipType.SUBMARINE)) {
            shipParts = buildShipParts(ShipType.SUBMARINE.size);
        }
        if (this.type.equals(ShipType.PATROL_BOAT)) {
            shipParts = buildShipParts(ShipType.PATROL_BOAT.size);
        }
    }

    public ShipType getType() {
        return type;
    }

    public ArrayList<ShipPart> getShipParts() {
        return shipParts;
    }

    private ArrayList<ShipPart> buildShipParts(int count) {
        ArrayList<ShipPart> shipParts = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            shipParts.add(new ShipPart());
        }

        return shipParts;
    }

}

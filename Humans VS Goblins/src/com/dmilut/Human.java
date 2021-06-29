package com.dmilut;

import java.util.Random;

public class Human extends Entity {

    public Human(Coordinates coordinates) {
        this.setCoordinates(coordinates);
        this.setSymbol("\uD83D\uDC68");

        this.setHealth(new Random().nextInt(10) + 2);
        this.setAttackPower(new Random().nextInt(getHealth()) / 2 + 1);
    }

    @Override
    public String toString() {
        return "Human: " + "health = " + getHealth() + "; attackPower = " + getAttackPower();
    }
}

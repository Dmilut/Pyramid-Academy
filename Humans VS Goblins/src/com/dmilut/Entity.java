package com.dmilut;

public class Entity extends Actor {
    private int health;
    private int attackPower;

    public boolean move(String direction) {
        World world = World.getInstance();
        Human human = world.getHuman();
        Coordinates fromCoordinates = human.getCoordinates();
        Coordinates toCoordinates;
        Cell fromCell = world.getCell(fromCoordinates);
        Cell toCell;
        boolean result = false;

        switch (direction) {
            case "n":
                if (fromCoordinates.getX() > 0) {
                    toCoordinates = new Coordinates(fromCoordinates.getX() - 1, fromCoordinates.getY());
                    toCell = world.getCell(toCoordinates);

                    human.setCoordinates(toCoordinates);
                    fromCell.getActors().remove(human);
                    toCell.getActors().add(human);
                    result = true;
                }
                break;
            case "s":
                if (fromCoordinates.getX() < World.WORLD_SIZE - 1) {
                    toCoordinates = new Coordinates(fromCoordinates.getX() + 1, fromCoordinates.getY());
                    toCell = world.getCell(toCoordinates);

                    human.setCoordinates(toCoordinates);
                    fromCell.getActors().remove(human);
                    toCell.getActors().add(human);
                    result = true;
                }
                break;
            case "w":
                if (fromCoordinates.getY() > 0) {
                    toCoordinates = new Coordinates(fromCoordinates.getX(), fromCoordinates.getY() - 1);
                    toCell = world.getCell(toCoordinates);

                    human.setCoordinates(toCoordinates);
                    fromCell.getActors().remove(human);
                    toCell.getActors().add(human);
                    result = true;
                }
                break;
            case "e":
                if (fromCoordinates.getY() < World.WORLD_SIZE - 1) {
                    toCoordinates = new Coordinates(fromCoordinates.getX(), fromCoordinates.getY() + 1);
                    toCell = world.getCell(toCoordinates);

                    human.setCoordinates(toCoordinates);
                    fromCell.getActors().remove(human);
                    toCell.getActors().add(human);
                    result = true;
                }
                break;
        }

        return result;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

}
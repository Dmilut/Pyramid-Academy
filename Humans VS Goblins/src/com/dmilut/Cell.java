package com.dmilut;

import java.util.ArrayList;

public class Cell extends Actor{
    private ArrayList<Actor> actors;

    public Cell(Coordinates coordinates) {
        setCoordinates(coordinates);
    }

    public ArrayList<Actor> getActors() {
        if (actors == null) {
            actors = new ArrayList<>();
        }
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }
}

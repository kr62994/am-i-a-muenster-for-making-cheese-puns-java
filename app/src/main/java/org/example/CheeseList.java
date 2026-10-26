package org.example;

import java.util.ArrayList;

public class CheeseList {
    private final ArrayList<Cheese> cheeses = new ArrayList<>();

    public void add(Cheese cheese) {
        cheeses.add(cheese);
    }

    public ArrayList<Cheese> getCheeses() {
        return cheeses;
    }
}

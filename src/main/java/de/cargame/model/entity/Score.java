package de.cargame.model.entity;


public class Score {

    private int value = 0;


    public double increaseScore(int value) {
        value += value;
        return value;
    }

    public int getValue() {
        return value;
    }

    public void reset() {
        value = 0;
    }
}

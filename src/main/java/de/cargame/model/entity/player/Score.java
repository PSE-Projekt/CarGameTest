package de.cargame.model.entity.player;


public class Score {

    private double value = 0;


    public double increaseScore(double value) {
        this.value += value;
        return value;
    }

    public double getValue() {
        return value;
    }

    public void reset() {
        value = 0;
    }
}

package de.cargame.model.entity.player;


import lombok.Getter;

public class Score {

    @Getter
    private double value = 0;

    private final double DEFAULT_VALUE = 0;


    public double increaseScore(double value) {
        this.value += value;
        return value;
    }

    public void reset() {
        value = DEFAULT_VALUE;
    }
}

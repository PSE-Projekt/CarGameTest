package de.cargame.model.entity.gameobject;

import lombok.Getter;

import java.awt.*;

public abstract class GameObjectBound {

    @Getter
    protected Coordinate coordinate;


    public GameObjectBound(double x, double y) {
        coordinate = new Coordinate(x, y);
    }

    public abstract Shape getBound();


    public void moveBy(double xAmount, double yAmount) {
        coordinate.addX(xAmount);
        coordinate.addY(yAmount);
    }
}

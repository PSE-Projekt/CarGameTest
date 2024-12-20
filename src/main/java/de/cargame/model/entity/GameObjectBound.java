package de.cargame.model.entity;

import lombok.Getter;

import java.awt.*;

public abstract class GameObjectBound {

    @Getter
    protected Coordinate coordinate;


     public GameObjectBound(int x, int y){
         coordinate = new Coordinate(x,y);
     }
    public abstract Shape getBound();


    public void moveBy(int xAmount, int yAmount) {
        coordinate.addX(xAmount);
        coordinate.addY(yAmount);
    }
}

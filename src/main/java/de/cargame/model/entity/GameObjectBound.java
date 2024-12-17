package de.cargame.model.entity;

import de.cargame.model.entity.gameobject.GameObject;

import java.awt.*;

public abstract class GameObjectBound {

    protected Coordinate coordinate;

    public GameObjectBound(int x, int y){
        this.coordinate = new Coordinate(x,y);
    }
    public abstract Shape getBound();
}

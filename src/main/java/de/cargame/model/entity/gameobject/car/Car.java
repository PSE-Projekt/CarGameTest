package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import de.cargame.model.entity.gameobject.GameObject;

public abstract class Car extends GameObject {
    protected int speed;
    protected int inertia;

    protected Car(Coordinate coordinate, Dimension dimension) {
        super(coordinate, dimension);
    }
}

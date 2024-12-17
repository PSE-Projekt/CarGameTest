package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;

public abstract class PlayerCar extends Car{
    protected PlayerCar(Coordinate coordinate, Dimension dimension) {
        super(coordinate, dimension);
    }
}

package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;

public abstract class AICar extends Car{
    protected AICar(Coordinate coordinate, Dimension dimension) {
        super(coordinate, dimension);
    }
}

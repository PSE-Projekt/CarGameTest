package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import lombok.Getter;

public class KamikazeCar extends AICar{

    @Getter
    private MovementStrategy movementStrategy;
    public KamikazeCar(Coordinate coordinate, Dimension dimension, MovementStrategy movementStrategy) {
        super(coordinate, dimension);
        this.movementStrategy = movementStrategy;
    }
}

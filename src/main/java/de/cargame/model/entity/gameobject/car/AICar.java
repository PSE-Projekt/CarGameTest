package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;
import lombok.Getter;

public abstract class AICar extends Car {

    @Getter
    private MovementStrategy movementStrategy;

    public AICar(int x, int y, int width, int height, GameObjectBoundType gameObjectBoundType, MovementStrategy movementStrategy) {
        super(x, y, width, height, gameObjectBoundType);
        this.movementStrategy = movementStrategy;
    }

    public AICar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType, MovementStrategy movementStrategy) {
        super(coordinate, dimension, gameObjectBoundType);
    }

}

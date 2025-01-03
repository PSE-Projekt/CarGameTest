package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.gameobject.Coordinate;
import de.cargame.model.entity.gameobject.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;
import lombok.Getter;

public abstract class AICar extends Car {

    @Getter
    private MovementStrategy movementStrategy;


    public AICar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType, MovementStrategy movementStrategy, String belongingPlayerId) {
        super(coordinate, dimension, gameObjectBoundType, belongingPlayerId);
        this.movementStrategy = movementStrategy;
    }

    @Override
    protected void setDespawnable() {
        this.isDespawnable = true;
    }

    @Override
    protected void setCollidable() {
        this.isCollidable = true;
    }

}

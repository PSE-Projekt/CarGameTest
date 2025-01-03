package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.gameobject.Coordinate;
import de.cargame.model.entity.gameobject.Dimension;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.gameobject.GameObjectBoundType;
import lombok.Getter;

@Getter
public abstract class Car extends GameObject {

    protected double speed;

    public Car(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType, String belongingPlayerId) {
        super(coordinate, dimension, gameObjectBoundType, belongingPlayerId);
    }

    @Override
    protected void setIsStatic() {
        this.isStatic = false;
    }

    abstract protected void setSpeed();

}

package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.gameobject.Coordinate;
import de.cargame.model.entity.gameobject.Dimension;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.gameobject.GameObjectBoundType;
import lombok.Getter;

@Getter
public abstract class Car extends GameObject {

    protected double speed;

    public Car(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType) {
        super(coordinate, dimension, gameObjectBoundType);
    }

    @Override
    protected void setIsStatic() {
        this.isStatic = false;
    }

    abstract protected void setSpeed();

}

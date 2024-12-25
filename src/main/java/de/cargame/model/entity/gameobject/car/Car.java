package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.gameobject.GameObjectBoundType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Car extends GameObject {

    public Car(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType) {
        super(coordinate, dimension, gameObjectBoundType);
    }

    @Override
    protected void setIsStatic() {
        this.isStatic = false;
    }
}

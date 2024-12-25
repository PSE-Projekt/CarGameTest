package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class PlayerCar extends Car {


    private String playerId;
    private int speed;
    private double inertia;

    public PlayerCar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType) {
        super(coordinate, dimension, gameObjectBoundType);;
    }

    @Override
    public void setDespawnable() {
        this.isDespawnable = false;
    }
}

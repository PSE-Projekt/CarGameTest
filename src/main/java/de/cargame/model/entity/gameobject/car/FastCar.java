package de.cargame.model.entity.gameobject.car;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;

public class FastCar extends PlayerCar {

    public FastCar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType) {
        super(coordinate, dimension, gameObjectBoundType);
        setSpeed(GameConfig.FAST_CAR_SPEED);
        setInertia(GameConfig.FAST_CAR_INERTIA);
    }
}

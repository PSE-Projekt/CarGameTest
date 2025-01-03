package de.cargame.model.entity.gameobject.car;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.gameobject.Coordinate;
import de.cargame.model.entity.gameobject.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;

public class FastCar extends PlayerCar {

    public FastCar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType, String belongingPlayerId) {
        super(coordinate, dimension, gameObjectBoundType, belongingPlayerId);
        setSpeed();
        setInertia(GameConfig.FAST_CAR_INERTIA);
    }

    @Override
    protected void setSpeed() {
        this.speed = GameConfig.FAST_CAR_SPEED;
    }
}

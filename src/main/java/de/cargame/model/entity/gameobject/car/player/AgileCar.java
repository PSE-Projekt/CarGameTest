package de.cargame.model.entity.gameobject.car.player;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.gameobject.Coordinate;
import de.cargame.model.entity.gameobject.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;

public class AgileCar extends PlayerCar {

    public AgileCar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType) {
        super(coordinate, dimension, gameObjectBoundType);
        setSpeed();
        setInertia(GameConfig.AGILE_CAR_INERTIA);
    }

    @Override
    protected void setSpeed() {
        this.speed = GameConfig.AGILE_CAR_SPEED;
    }
}

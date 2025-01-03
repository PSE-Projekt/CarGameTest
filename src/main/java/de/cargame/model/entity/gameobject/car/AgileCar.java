package de.cargame.model.entity.gameobject.car;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.gameobject.Coordinate;
import de.cargame.model.entity.gameobject.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;

public class AgileCar extends PlayerCar {

    public AgileCar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType, String belongingPlayerId) {
        super(coordinate, dimension, gameObjectBoundType, belongingPlayerId);
        setSpeed();
        setInertia(GameConfig.AGILE_CAR_INERTIA);
    }

    @Override
    protected void setSpeed() {
        this.speed = GameConfig.AGILE_CAR_SPEED;
    }
}

package de.cargame.model.entity.gameobject.car;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;

public class AgileCar extends PlayerCar {


    public AgileCar(int x, int y, int width, int height, GameObjectBoundType gameObjectBoundType) {
        super(x, y, width, height, gameObjectBoundType);
        setSpeed(GameConfig.AGILE_CAR_SPEED);
        setInertia(GameConfig.AGILE_CAR_INERTIA);
    }

    public AgileCar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType) {
        super(coordinate, dimension, gameObjectBoundType);
        setSpeed(GameConfig.AGILE_CAR_SPEED);
        setInertia(GameConfig.AGILE_CAR_INERTIA);
    }
}

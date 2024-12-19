package de.cargame.model.entity.gameobject.car;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;

public class FastCar extends PlayerCar {


    public FastCar(Coordinate coordinate, Dimension dimension) {
        super(coordinate, dimension);
        setSpeed(GameConfig.FAST_CAR_SPEED);
        setInertia(GameConfig.FAST_CAR_INERTIA);
    }

}

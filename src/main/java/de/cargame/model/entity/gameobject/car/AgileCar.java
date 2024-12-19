package de.cargame.model.entity.gameobject.car;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;

public class AgileCar extends PlayerCar {


    public AgileCar(Coordinate coordinate, Dimension dimension) {
        super(coordinate, dimension);
        setSpeed(GameConfig.AGILE_CAR_SPEED);
        setInertia(GameConfig.AGILE_CAR_INERTIA);
    }


}

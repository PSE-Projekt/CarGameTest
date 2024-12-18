package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;

public class FastCar extends PlayerCar{


    public FastCar(Coordinate coordinate, Dimension dimension){
        super(coordinate, dimension);
        setSpeed(70);
        setInertia(0.1);
    }

}

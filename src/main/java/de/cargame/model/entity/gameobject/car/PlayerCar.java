package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;

public abstract class PlayerCar extends Car {


    public PlayerCar(int x, int y, int width, int height, GameObjectBoundType gameObjectBoundType) {
        super(x, y, width, height, gameObjectBoundType);
    }

    public PlayerCar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType) {
        super(coordinate, dimension, gameObjectBoundType);
    }
}

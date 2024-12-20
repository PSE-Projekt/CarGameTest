package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;
import lombok.Getter;

public class KamikazeCar extends AICar{



    public KamikazeCar(int x, int y, int width, int height, GameObjectBoundType gameObjectBoundType, MovementStrategy movementStrategy) {
        super(x, y, width, height, gameObjectBoundType, movementStrategy);

    }

    public KamikazeCar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType, MovementStrategy movementStrategy) {
        super(coordinate, dimension, gameObjectBoundType, movementStrategy);
    }
}

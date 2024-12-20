package de.cargame.model.entity.gameobject;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;

public class Life extends Reward {

    public Life(int x, int y, int width, int height, GameObjectBoundType gameObjectBoundType) {
        super(x, y, width, height, gameObjectBoundType);
    }

    public Life(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType) {
        super(coordinate, dimension, gameObjectBoundType);
    }
}

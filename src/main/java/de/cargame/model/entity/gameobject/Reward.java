package de.cargame.model.entity.gameobject;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;

public abstract class Reward extends GameObject implements Collectable {
    protected Reward(Coordinate coordinate, Dimension dimension) {
        super(coordinate, dimension);
    }
}

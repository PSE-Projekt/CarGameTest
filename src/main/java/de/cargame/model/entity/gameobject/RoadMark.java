package de.cargame.model.entity.gameobject;

import de.cargame.config.GameConfig;

public class RoadMark extends GameObject {

    public RoadMark(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType) {
        super(coordinate, dimension, gameObjectBoundType);
    }

    @Override
    public void move(double deltaTime) {
        moveBy(-GameConfig.GAME_SPEED * deltaTime, 0);
    }

    @Override
    protected void setIsStatic() {
        this.isStatic = true;
    }

    @Override
    protected void setDespawnable() {
        this.isDespawnable = true;
    }

    @Override
    protected void setCollidable() {
        this.isCollidable = false;
    }
}

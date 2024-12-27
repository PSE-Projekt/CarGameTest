package de.cargame.model.entity.gameobject;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Reward extends GameObject {

    private boolean collected = false;

    public Reward(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType) {
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
        this.isCollidable = true;
    }
}

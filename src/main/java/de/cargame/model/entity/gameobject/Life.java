package de.cargame.model.entity.gameobject;

public class Life extends Reward {

    public Life(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType, String belongingPlayerId) {
        super(coordinate, dimension, gameObjectBoundType, belongingPlayerId);
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

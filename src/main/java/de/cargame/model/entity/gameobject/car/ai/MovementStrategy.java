package de.cargame.model.entity.gameobject.car.ai;

import de.cargame.model.entity.gameobject.Coordinate;

public abstract class MovementStrategy {

    protected Coordinate targetPos;
    protected final Coordinate gameObjectSpawnCoordinate;


    public MovementStrategy(Coordinate gameObjectSpawnCoordinate) {
        this.gameObjectSpawnCoordinate = gameObjectSpawnCoordinate;
        calcTargetPos();
    }

    public abstract void calcTargetPos();


    public double getTargetPosX() {
        return targetPos.getX();
    }

    public double getTargetPosY() {
        return targetPos.getY();
    }
}

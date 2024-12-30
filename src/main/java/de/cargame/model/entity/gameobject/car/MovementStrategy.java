package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.gameobject.Coordinate;

public abstract class MovementStrategy {

    protected Coordinate targetPos;
    protected Coordinate gameObjectSpawnCoordinate;


    public MovementStrategy(Coordinate gameObjectSpawnCoordinate) {
        this.gameObjectSpawnCoordinate = gameObjectSpawnCoordinate;
        calcTargetPos();
    }

    public abstract Coordinate calcTargetPos();


    public double getTargetPosX() {
        return targetPos.getX();
    }

    public double getTargetPosY() {
        return targetPos.getY();
    }
}

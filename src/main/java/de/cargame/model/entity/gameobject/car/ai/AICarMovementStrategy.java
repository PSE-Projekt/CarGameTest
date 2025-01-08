package de.cargame.model.entity.gameobject.car.ai;

import de.cargame.model.entity.gameobject.Coordinate;

public abstract class AICarMovementStrategy implements MovementStrategy {

    protected Coordinate targetPos;
    protected final Coordinate gameObjectSpawnCoordinate;


    public AICarMovementStrategy(Coordinate gameObjectSpawnCoordinate) {
        this.gameObjectSpawnCoordinate = gameObjectSpawnCoordinate;
        calcTargetPos();
    }

    @Override
    public abstract void calcTargetPos();

    @Override

    public double getTargetPosX() {
        return targetPos.getX();
    }

    @Override
    public double getTargetPosY() {
        return targetPos.getY();
    }
}

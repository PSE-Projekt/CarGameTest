package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;

public class StraightMovementStrategy extends MovementStrategy {

    public StraightMovementStrategy(Coordinate gameObjectSpawnCoordinate) {
        super(gameObjectSpawnCoordinate);
    }

    @Override
    public Coordinate calcTargetPos() {
        this.targetPos = new Coordinate(-1000, gameObjectSpawnCoordinate.getY());
        return targetPos;
    }
}

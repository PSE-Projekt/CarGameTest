package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;

public class StraightMovementStrategy extends MovementStrategy {

    @Override
    public Coordinate calcNewTargetPos(Coordinate currentPos) {
        return new Coordinate(0,0);
    }
}

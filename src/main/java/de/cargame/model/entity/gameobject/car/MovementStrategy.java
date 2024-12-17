package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;

public abstract class MovementStrategy {


    public abstract Coordinate calcNewTargetPos(Coordinate currentPos);
}

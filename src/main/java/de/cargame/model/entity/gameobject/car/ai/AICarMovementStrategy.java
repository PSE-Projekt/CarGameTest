package de.cargame.model.entity.gameobject.car.ai;

public interface AICarMovementStrategy {

    void calcTargetPos();
    double getTargetPosX();
    double getTargetPosY();

}

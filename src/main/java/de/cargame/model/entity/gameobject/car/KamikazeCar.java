package de.cargame.model.entity.gameobject.car;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.gameobject.Coordinate;
import de.cargame.model.entity.gameobject.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;

public class KamikazeCar extends AICar {

    public KamikazeCar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType, MovementStrategy movementStrategy, String belongingPlayerId) {
        super(coordinate, dimension, gameObjectBoundType, movementStrategy, belongingPlayerId);
        setSpeed();
        System.out.println("Spawn");
    }

    @Override
    public void move(double deltaTime, boolean isFastForwarding) {
        double aiCarSpeed;
        if (isFastForwarding) {
            aiCarSpeed = getSpeed() * GameConfig.GAME_SPEED_FAST_FORWARD;
        } else {
            aiCarSpeed = getSpeed() * GameConfig.GAME_SPEED;
        }
        MovementStrategy movementStrategy = getMovementStrategy();

        double deltaX = movementStrategy.getTargetPosX() - getX();
        double deltaY = movementStrategy.getTargetPosY() - getY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (distance > 0) {

            double directionX = deltaX / distance;
            double directionY = deltaY / distance;

            double moveX = directionX * getSpeed() * deltaTime * aiCarSpeed;
            double moveY = directionY * getSpeed() * deltaTime * aiCarSpeed;

            moveBy(moveX, moveY);
        } else {
            movementStrategy.calcTargetPos();
        }
    }


    @Override
    protected void setSpeed() {
        this.speed = GameConfig.AI_CAR_SPEED;
    }
}

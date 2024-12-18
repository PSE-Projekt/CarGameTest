package de.cargame.model.handler;

import de.cargame.model.entity.Collision;
import de.cargame.model.entity.CollisionType;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.PlayerCar;

import java.util.Optional;

public class CollisionHandler {

    private PlayerHandler playerHandler;

    public CollisionHandler(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    public Optional<Collision> checkCollision(PlayerCar car, GameObject collidedObject) {
        //Collision Dectection Logik
        boolean collisionOccurred = false;
        int collisionsX = 34;
        int collisionsY = 176;
        Coordinate collisionCoordinate = new Coordinate(collisionsX, collisionsY);

        if (collisionOccurred) {
            Collision collision = handleCollision(car, collidedObject, collisionCoordinate);
            return Optional.of(collision);
        }
        return Optional.empty();
    }


    private Collision handleCollision(PlayerCar playerCar, GameObject collisionObject, Coordinate coordinate) {

        if (collisionObject instanceof Reward) {
            handleCollisionReward(playerCar, (Reward) collisionObject);
            return new Collision(coordinate, CollisionType.REWARD, playerCar, collisionObject);
        }
        //Kollision mit Lebensverlust logische Konsequenz
        handleCollisionCrash(playerCar);
        return new Collision(coordinate, CollisionType.CRASH, playerCar, collisionObject);

    }

    private void handleCollisionReward(PlayerCar playerCar, Reward reward) {
        if (reward instanceof Life) {
            playerHandler.increaseLife(playerCar);
        }
    }

    private void handleCollisionCrash(PlayerCar playerCar) {
        playerHandler.decreaseLife(playerCar);
    }
}

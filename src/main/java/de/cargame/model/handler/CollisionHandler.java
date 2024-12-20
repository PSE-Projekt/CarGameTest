package de.cargame.model.handler;

import de.cargame.exception.IllegalGameObjectBoundException;
import de.cargame.model.entity.Collision;
import de.cargame.model.entity.CollisionType;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.gameobject.Life;
import de.cargame.model.entity.gameobject.Reward;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Optional;

@Slf4j
public class CollisionHandler {

    private PlayerHandler playerHandler;

    public CollisionHandler(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    public Optional<Collision> checkCollision(PlayerCar car, GameObject collidedObject) {
        Shape carBound = car.getBound();
        Shape collidedObjectBound = collidedObject.getBound();
        boolean collisionOccurred;
        if(collidedObjectBound instanceof Rectangle2D){
            collisionOccurred = carBound.intersects((Rectangle2D) collidedObjectBound);
        }else{
            log.error("The collision detection algorithm does not support this kind of collision detection yet.");
            throw new IllegalGameObjectBoundException("The collision detection algorithm does not support this kind of collision detection yet.");
        }

        if (collisionOccurred) {
            Collision collision = handleCollision(car, collidedObject);
            return Optional.of(collision);
        }
        return Optional.empty();
    }


    private Collision handleCollision(PlayerCar playerCar, GameObject collisionObject) {

        if (collisionObject instanceof Reward) {
            handleCollisionReward(playerCar, (Reward) collisionObject);
            return new Collision(CollisionType.REWARD, playerCar, collisionObject);
        }
        //Kollision mit Lebensverlust logische Konsequenz
        handleCollisionCrash(playerCar);
        return new Collision(CollisionType.CRASH, playerCar, collisionObject);

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

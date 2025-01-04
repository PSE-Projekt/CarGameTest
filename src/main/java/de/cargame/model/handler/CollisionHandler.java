package de.cargame.model.handler;

import de.cargame.exception.IllegalGameObjectBoundException;
import de.cargame.model.entity.collision.Collision;
import de.cargame.model.entity.collision.CollisionType;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.gameobject.Life;
import de.cargame.model.entity.gameobject.Reward;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import de.cargame.model.entity.player.Player;
import de.cargame.model.service.SoundService;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {

    private final PlayerHandler playerHandler;
    private final SoundService soundService;

    public CollisionHandler(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
        this.soundService = new SoundService();
    }


    public List<Collision> checkCollision(List<GameObject> gameObjects) {
        Player player = playerHandler.getPlayer();
        PlayerCar playerCar = player.getPlayerCar();
        List<GameObject> collidableObjects = gameObjects
                .stream()
                .filter(GameObject::isCollidable)
                .toList();
        List<Collision> collisions = new ArrayList<>();


        for (GameObject collidableObject : collidableObjects) {
            Shape playerCarBound = playerCar.getBound();
            Shape collidableObjectBound = collidableObject.getBound();

            if (collidableObjectBound instanceof Rectangle2D) {
                if (playerCarBound.intersects((Rectangle2D) collidableObjectBound)) {
                    collisions.add(handleCollision(playerCar, collidableObject));
                }
            } else {
                System.out.println("The collision detection algorithm does not support this kind of collision detection yet.");
                throw new IllegalGameObjectBoundException("The collision detection algorithm does not support this kind of collision detection yet.");
            }
        }

        return collisions;
    }


    private Collision handleCollision(PlayerCar playerCar, GameObject collisionObject) {
        if (collisionObject instanceof Reward) {
            handleCollisionReward(playerCar, (Reward) collisionObject);
            return new Collision(CollisionType.REWARD, playerCar, collisionObject);
        }
        handleCollisionCrash(playerCar);
        return new Collision(CollisionType.CRASH, playerCar, collisionObject);
    }


    private void handleCollisionReward(PlayerCar playerCar, Reward reward) {
        if (reward instanceof Life && !reward.isCollected()) {
            playerHandler.increaseLife();
            soundService.collectRewardSound();
        }
        reward.setCollected(true);
    }


    private void handleCollisionCrash(PlayerCar playerCar) {
        if (!playerCar.hasCrashCooldown()) {
            soundService.playCrashSound();
            playerHandler.decreaseLife();
            playerCar.setLastCrashTime();
        }
    }

}

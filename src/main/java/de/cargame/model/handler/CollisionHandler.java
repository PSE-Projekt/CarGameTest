package de.cargame.model.handler;

import de.cargame.exception.IllegalGameObjectBoundException;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.gameobject.Life;
import de.cargame.model.entity.gameobject.Reward;
import de.cargame.model.entity.gameobject.car.player.PlayerCar;
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


    public void checkCollision(List<GameObject> gameObjects) {
        Player player = playerHandler.getPlayer();
        PlayerCar playerCar = player.getPlayerCar();
        List<GameObject> collidableObjects = new ArrayList<>(gameObjects
                .stream()
                .filter(GameObject::isCollidable)
                .toList());
        collidableObjects.remove(playerCar);


        for (GameObject collidableObject : collidableObjects) {
            Shape playerCarBound = playerCar.getBound();
            Shape collidableObjectBound = collidableObject.getBound();

            if (collidableObjectBound instanceof Rectangle2D) {
                if (playerCarBound.intersects((Rectangle2D) collidableObjectBound)) {
                    handleCollision(playerCar, collidableObject);
                }
            } else {
                System.out.println("The collision detection algorithm does not support this kind of collision detection yet.");
                throw new IllegalGameObjectBoundException("The collision detection algorithm does not support this kind of collision detection yet.");
            }
        }
    }


    private void handleCollision(PlayerCar playerCar, GameObject collisionObject) {
        if (collisionObject instanceof Reward reward) {
            handleCollisionReward(reward);
        }
        handleCollisionCrash(playerCar);
    }


    private void handleCollisionReward(Reward reward) {
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

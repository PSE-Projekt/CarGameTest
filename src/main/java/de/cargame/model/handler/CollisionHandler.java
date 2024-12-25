package de.cargame.model.handler;

import de.cargame.exception.IllegalGameObjectBoundException;
import de.cargame.model.entity.Collision;
import de.cargame.model.entity.CollisionType;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.gameobject.Life;
import de.cargame.model.entity.gameobject.Reward;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CollisionHandler {

    private final PlayerHandler playerHandler;

    public CollisionHandler(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }


    public List<Collision> checkCollision(List<GameObject> gameObjects) {
        List<Player> activeAndAlivePlayers = playerHandler.getActiveAndAlivePlayers();
        List<PlayerCar> playerCars = activeAndAlivePlayers.stream().map(Player::getPlayerCar).toList();
        List<GameObject> collidableObjects = gameObjects.stream().filter(GameObject::isCollidable).toList();
        List<Collision> collisions = new ArrayList<>();


        for (PlayerCar playerCar : playerCars) {
            for (GameObject collidableObject : collidableObjects) {
                Shape playerCarBound = playerCar.getBound();
                Shape collidableObjectBound = collidableObject.getBound();

                if (collidableObjectBound instanceof Rectangle2D) {
                    if (playerCarBound.intersects((Rectangle2D) collidableObjectBound)) {
                        collisions.add(handleCollision(playerCar, collidableObject));
                    }
                } else {
                    log.error("The collision detection algorithm does not support this kind of collision detection yet.");
                    throw new IllegalGameObjectBoundException("The collision detection algorithm does not support this kind of collision detection yet.");
                }
            }
        }
        return collisions;
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
            playerHandler.increaseLife(playerCar.getPlayerId());
        }
    }

    private void handleCollisionCrash(PlayerCar playerCar) {
        if(!playerCar.hasCrashCooldown()){
            System.out.println("Crash - Lives decreased");
            playerHandler.decreaseLife(playerCar.getPlayerId());
            playerCar.setLastCrashTime();
        }{
            System.out.println("Crash - Cooldown");
        }
    }

}

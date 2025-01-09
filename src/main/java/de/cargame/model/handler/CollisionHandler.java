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


    /**
     * Checks for collisions between the player's car and other collidable objects in the game.
     * Iterates through the list of game objects, filters out non-collidable objects and the player's car,
     * and detects intersections between the player's car and the remaining objects. If a collision
     * occurs, appropriate handling mechanisms are invoked. Currently, the collision detection
     * algorithm supports rectangular boundaries only.
     *
     * @param gameObjects A list of all game objects in the game world, which may include static
     *                    and dynamic objects, both collidable and non-collidable. The method
     *                    filters this list for processable objects.
     */
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


    /**
     * Handles the collision between the player's car and another game object.
     * If the collision is with a reward, invokes the reward-specific handling mechanism.
     * Handles damage or crash events if applicable.
     *
     * @param playerCar       The player's car involved in the collision.
     * @param collisionObject The game object that the player's car collided with.
     */
    private void handleCollision(PlayerCar playerCar, GameObject collisionObject) {
        if (collisionObject instanceof Reward reward) {
            handleCollisionReward(reward);
            return;
        }
        handleCollisionCrash(playerCar);
    }


    /**
     * Handles the logic for processing a collision with a reward object.
     * If the reward is of type {@code Life} and has not yet been collected,
     * it increases the player's life count and plays the appropriate reward collection sound.
     * The reward is marked as collected after the operation.
     *
     * @param reward The reward object that is involved in the collision.
     */
    private void handleCollisionReward(Reward reward) {
        if (reward instanceof Life && !reward.isCollected()) {
            playerHandler.increaseLife();
            soundService.collectRewardSound();
        }
        reward.setCollected(true);
    }


    /**
     * Handles the aftermath of a crash collision involving the player's car.
     * This method ensures that, if the player car is not under a crash cooldown,
     * the crash sound is played, the player's life is decreased, and the last crash
     * time is updated to the current time.
     *
     * @param playerCar The player's car involved in the crash collision.
     */
    private void handleCollisionCrash(PlayerCar playerCar) {
        if (!playerCar.hasCrashCooldown()) {
            soundService.playCrashSound();
            playerHandler.decreaseLife();
            playerCar.setLastCrashTime();
        }
    }

}

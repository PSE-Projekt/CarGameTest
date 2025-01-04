package de.cargame.model.entity.gameobject.car;

import de.cargame.config.GameConfig;
import de.cargame.controller.input.UserInputType;
import de.cargame.model.entity.gameobject.Coordinate;
import de.cargame.model.entity.gameobject.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;
import de.cargame.model.handler.PlayerHandler;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class PlayerCar extends Car {


    private String playerId;
    private double inertia;
    private long lastCrashTime;
    private PlayerHandler playerHandler;

    public PlayerCar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType, String belongingPlayerId) {
        super(coordinate, dimension, gameObjectBoundType, belongingPlayerId);
        setPlayerHandler(playerHandler);
    }


    @Override
    public void move(double deltaTime, boolean isFastForwarding) {
        UserInputType currentUserInputType = playerHandler.getCurrentUserInput();

        double width = getBound().getBounds().getWidth();
        double height = getBound().getBounds().getHeight();

        double distance = getSpeed() * deltaTime;
        if (isFastForwarding) {
            double speedUpFactor = (distance + GameConfig.SCORE_INCREASE_FAST_FORWARD_SPEED);
            distance = distance + speedUpFactor;
            playerHandler.increaseScore(GameConfig.SCORE_INCREASE_FAST_FORWARD_SPEED);
        } else {
            playerHandler.increaseScore(GameConfig.SCORE_INCREASE_NORMAL_SPEED);
        }

        switch (currentUserInputType) {
            case UP:
                moveByRespectingGameBoundaries(0, -distance, width, height);
                break;
            case DOWN:
                moveByRespectingGameBoundaries(0, distance, width, height);
                break;
        }
    }

    @Override
    public void setDespawnable() {
        this.isDespawnable = false;
    }

    @Override
    protected void setCollidable() {
        this.isCollidable = false;
    }

    public boolean hasCrashCooldown() {
        long now = System.currentTimeMillis();
        return (now - lastCrashTime) < GameConfig.CRASH_COOLDOWN_TIME;
    }

    public void setLastCrashTime() {
        lastCrashTime = System.currentTimeMillis();
    }
}

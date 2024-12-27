package de.cargame.model.entity.gameobject.car;

import de.cargame.config.GameConfig;
import de.cargame.controller.input.UserInput;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import de.cargame.model.entity.gameobject.GameObjectBoundType;
import de.cargame.model.handler.PlayerHandler;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class PlayerCar extends Car {


    private String playerId;
    private int speed;
    private double inertia;
    private long lastCrashTime;
    private PlayerHandler playerHandler;

    public PlayerCar(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType) {
        super(coordinate, dimension, gameObjectBoundType);
        setPlayerHandler(playerHandler);
    }


    @Override
    public void move(double deltaTime) {
        UserInput currentUserInput = playerHandler.getCurrentUserInput(playerId);

        switch (currentUserInput) {
            case UP:
                moveBy(0, -getSpeed() * deltaTime, true);
                break;
            case DOWN:
                moveBy(0, getSpeed() * deltaTime, true);
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

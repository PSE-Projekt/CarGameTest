package de.cargame.model.entity.gameobject;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.gameobject.interfaces.Collidable;
import de.cargame.model.entity.gameobject.interfaces.Despawnable;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.UUID;

@Getter
@Setter
@Slf4j
public abstract class GameObject implements Collidable, Despawnable {


    private final String id = UUID.randomUUID().toString();
    protected GameObjectBound gameObjectBound;
    protected boolean isStatic;
    protected boolean isDespawnable;
    protected boolean isCollidable;

    public GameObject(double x, double y, int width, int height, GameObjectBoundType gameObjectBoundType) {
        setDespawnable();
        setIsStatic();
        setCollidable();
        switch (gameObjectBoundType) {
            case RECTANGLE:
                gameObjectBound = new RectangularGameObjectBound(x, y, width, height);
                return;
        }
        log.error("A hitbox was tried to initialize with an illegal shape");
    }

    public GameObject(Coordinate coordinate, Dimension dimension, GameObjectBoundType gameObjectBoundType) {
        this(coordinate.getX(), coordinate.getY(), dimension.getWidth(), dimension.getHeight(), gameObjectBoundType);
    }

    protected abstract void setIsStatic();

    protected abstract void setDespawnable();

    protected abstract void setCollidable();

    @Override
    public boolean isDespawnable() {
        return isDespawnable;
    }

    @Override
    public boolean isCollidable() {
        return isCollidable;
    }


    public abstract void move(double deltaTime, boolean isFastForwarding);

    protected void moveBy(double xAmount, double yAmount) {
        gameObjectBound.moveBy(xAmount, yAmount);
    }


    protected void moveByRespectingGameBoundaries(double xAmount, double yAmount, double objectWidth, double objectHeight) {
        double xOld = gameObjectBound.getCoordinate().getX();
        double yOld = gameObjectBound.getCoordinate().getY();

        // Objekt bewegen
        gameObjectBound.moveBy(xAmount, yAmount);

        double xNew = gameObjectBound.getCoordinate().getX();
        double yNew = gameObjectBound.getCoordinate().getY();

        if (xNew < 0 || xNew + objectWidth > GameConfig.SCREEN_WIDTH || yNew < 0 || yNew + objectHeight > GameConfig.SCREEN_HEIGHT) {
            gameObjectBound.getCoordinate().setX(xOld);
            gameObjectBound.getCoordinate().setY(yOld);
        }
    }


    public Shape getBound() {
        return gameObjectBound.getBound();
    }

    public Coordinate getCoordinates() {
        return gameObjectBound.getCoordinate();
    }

    public double getX() {
        return getCoordinates().getX();
    }

    public double getY() {
        return getCoordinates().getY();
    }

    public int getWidth() {
        return gameObjectBound.getBound().getBounds().width;
    }

    public int getHeight() {
        return gameObjectBound.getBound().getBounds().height;
    }


}

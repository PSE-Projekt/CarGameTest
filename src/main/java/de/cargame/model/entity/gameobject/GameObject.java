package de.cargame.model.entity.gameobject;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import de.cargame.model.entity.GameObjectBound;
import de.cargame.model.entity.RectangularGameObjectBound;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;

@Getter
@Setter
@Slf4j
public abstract class GameObject {

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

    public boolean isStatic() {
        return isStatic;
    }

    public boolean isDespawnable() {
        return isDespawnable;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public void moveBy(double xAmount, double yAmount) {
        gameObjectBound.moveBy(xAmount, yAmount);
    }


    public void moveBy(double xAmount, double yAmount, boolean respectingGameBounds) {
        double xOld = gameObjectBound.getCoordinate().getX();
        double yOld = gameObjectBound.getCoordinate().getY();

        gameObjectBound.moveBy(xAmount, yAmount);

        if(respectingGameBounds){
            double xNew = gameObjectBound.getCoordinate().getX();
            double yNew = gameObjectBound.getCoordinate().getY();
            if(xNew<0 || xNew > GameConfig.SCREEN_WIDTH || yNew<0 || yNew > GameConfig.SCREEN_HEIGHT){
                gameObjectBound.getCoordinate().setX(xOld);
                gameObjectBound.getCoordinate().setY(yOld);
            }
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


}

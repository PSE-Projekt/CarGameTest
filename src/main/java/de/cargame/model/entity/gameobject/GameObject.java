package de.cargame.model.entity.gameobject;

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

    public GameObject(double x, double y, int width, int height, GameObjectBoundType gameObjectBoundType) {

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

    public void moveBy(double xAmount, double yAmount) {
        gameObjectBound.moveBy(xAmount, yAmount);
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

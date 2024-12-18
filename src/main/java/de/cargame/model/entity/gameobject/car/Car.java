package de.cargame.model.entity.gameobject.car;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import de.cargame.model.entity.gameobject.GameObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Car extends GameObject {
    private int speed;
    private double inertia;

    protected Car(Coordinate coordinate, Dimension dimension) {
        super(coordinate, dimension);
    }
}

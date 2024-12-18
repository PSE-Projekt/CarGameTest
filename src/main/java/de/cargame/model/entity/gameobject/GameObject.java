package de.cargame.model.entity.gameobject;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GameObject {

    protected Coordinate coordinate;
    protected Dimension dimension;

    protected GameObject(Coordinate coordinate, Dimension dimension){
        setCoordinate(coordinate);
        setDimension(dimension);
    }

    public void move(int xAmount, int yAmount){
        coordinate.addX(xAmount);
        coordinate.addY(yAmount);
    }

}

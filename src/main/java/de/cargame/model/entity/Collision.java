package de.cargame.model.entity;

import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Collision {

    private Coordinate coordinate;
    private CollisionType collisionType;
    private PlayerCar playerCar;
    private GameObject gameObject2;

}

package de.cargame.model.entity.collision;

import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.gameobject.car.player.PlayerCar;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Collision {

    private CollisionType collisionType;
    private PlayerCar playerCar;
    private GameObject collidedObject;

}

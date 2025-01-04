package de.cargame.controller.entity;

import de.cargame.model.entity.gameobject.GameObject;
import lombok.Getter;

import java.util.List;

@Getter
public class GameModelData {

    private final String playerId;
    private final List<GameObject> gameObjects;


    public GameModelData(String playerId, final List<GameObject> gameObjects) {
        this.playerId = playerId;
        this.gameObjects = gameObjects;
    }
}

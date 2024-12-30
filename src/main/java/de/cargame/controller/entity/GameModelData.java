package de.cargame.controller.entity;

import de.cargame.model.entity.gameobject.GameObject;
import lombok.Getter;

import java.util.List;

@Getter
public class GameModelData {

    private final List<GameObject> gameObjects;


    public GameModelData(final List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
}

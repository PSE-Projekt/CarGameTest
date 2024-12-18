package de.cargame.controller.entity;

import de.cargame.model.entity.gameobject.GameObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GameModelData {

    private List<GameObject> gameObjects;


    public GameModelData(){
        this.gameObjects = new ArrayList<>();
    }

    public GameModelData(List<GameObject> gameObjects){
        this.gameObjects = gameObjects;
    }
}

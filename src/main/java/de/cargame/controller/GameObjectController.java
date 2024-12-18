package de.cargame.controller;

import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.handler.GameObjectHandler;

import java.util.List;

public class GameObjectController {


    private GameObjectHandler gameObjectHandler;


    public GameObjectController(){
        this.gameObjectHandler = new GameObjectHandler();
    }

    public List<GameObject> getAllGameObjects(){
        return gameObjectHandler.getGameAllObjects();
    }


    public void update(double deltaTime){
        gameObjectHandler.update(deltaTime);
    }


}

package de.cargame.model.handler;

import de.cargame.model.entity.gameobject.GameObject;

import java.util.ArrayList;
import java.util.List;

public class GameObjectHandler {
    private CollisionHandler collisionHandler;

    private PlayerHandler playerHandler;
    private List<GameObject> gameObjects = new ArrayList<>();


    public GameObjectHandler(){
        this.playerHandler = new PlayerHandler();
        this.collisionHandler = new CollisionHandler(playerHandler);
    }


    public List<GameObject> update(double deltaTime){
        for (GameObject gameObject : gameObjects) {

            //determine new pos
        }
        return null;//todo
    }

    public List<GameObject> getGameAllObjects(){
        return gameObjects;
    }
}

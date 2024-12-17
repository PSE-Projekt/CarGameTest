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


    public void update(){}
}

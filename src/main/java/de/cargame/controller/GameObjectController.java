package de.cargame.controller;

import de.cargame.api.GameObjectApi;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.handler.GameObjectHandler;
import de.cargame.model.handler.PlayerHandler;

import java.util.List;

public class GameObjectController implements GameObjectApi {


    private GameObjectHandler gameObjectHandler;


    public GameObjectController(GameStateController gameStateController, PlayerHandler playerHandler) {
        this.gameObjectHandler = new GameObjectHandler(gameStateController, playerHandler);
    }

    public void startGame() {
        gameObjectHandler.startGame();
    }

    public void stopGame() {
        gameObjectHandler.stopGame();
    }

    public List<GameObject> getAllGameObjects() {
        return gameObjectHandler.getGameAllObjects();
    }


    public void update(double deltaTime) {
        gameObjectHandler.update(deltaTime);
    }

}

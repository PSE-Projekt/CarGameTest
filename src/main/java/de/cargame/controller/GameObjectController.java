package de.cargame.controller;

import de.cargame.api.GameObjectApi;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.handler.PlayerHandler;
import de.cargame.model.service.GameObjectService;

import java.util.List;

public class GameObjectController implements GameObjectApi {


    private final GameObjectService gameObjectService;


    public GameObjectController(GameStateController gameStateController, PlayerHandler playerHandler) {
        this.gameObjectService = new GameObjectService(gameStateController, playerHandler);
    }

    public void startGame() {
        gameObjectService.startGame();
    }

    public void stopGame() {
        gameObjectService.stopGame();
    }

    public List<GameObject> getAllGameObjects() {
        return gameObjectService.getGameAllObjects();
    }


    public void update(double deltaTime) {
        gameObjectService.update(deltaTime);
    }

}

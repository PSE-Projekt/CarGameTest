package de.cargame.controller;

import de.cargame.controller.api.GameObjectAPI;
import de.cargame.controller.api.GameStateAPI;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.handler.PlayerHandler;
import de.cargame.model.service.GameObjectService;

import java.util.List;

public class GameObjectController implements GameObjectAPI {


    private final GameObjectService gameObjectService;


    public GameObjectController(GameStateAPI gameStateController, PlayerHandler playerHandler) {
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

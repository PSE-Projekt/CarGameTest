package de.cargame.controller;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.CarType;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.handler.GameObjectHandler;
import de.cargame.model.handler.PlayerHandler;
import de.cargame.model.handler.entity.GameStartParameter;

import java.util.List;

public class GameObjectController {


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
        gameObjectHandler.moveElements(deltaTime);
        gameObjectHandler.despawnPassedObjects();
    }

    public void spawnPlayerCar(Coordinate coordinate, Player player, CarType carType) {
        gameObjectHandler.spawnPlayerCar(coordinate, player, carType);
    }

}

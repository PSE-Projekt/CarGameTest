package de.cargame.controller;

import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.AICar;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import de.cargame.model.handler.GameObjectHandler;

import java.util.List;

public class GameObjectController {


    private GameObjectHandler gameObjectHandler;


    public GameObjectController() {
        this.gameObjectHandler = new GameObjectHandler();
    }

    public void startGame(){
        gameObjectHandler.startGame();
    }

    public void stopGame(){
        gameObjectHandler.stopGame();
    }

    public List<GameObject> getAllGameObjects() {
        return gameObjectHandler.getGameAllObjects();
    }


    public void update(double deltaTime) {
        gameObjectHandler.moveElements(deltaTime);
    }

    public void spawnPlayerCar(Coordinate coordinate, Player player, CarType carType){
        gameObjectHandler.spawnPlayerCar(coordinate, player, carType);
    }

    public void spawnBuilding(Coordinate coordinate){
        gameObjectHandler.spawnBuilding(coordinate);
    }

    public void spawnRoad(Coordinate coordinate){
        gameObjectHandler.spawnRoad(coordinate);
    }

    public void spawnObstacle(Coordinate coordinate){
        gameObjectHandler.spawnObstacle(coordinate);
    }

    public void spawnReward(Coordinate coordinate){
        gameObjectHandler.spawnReward(coordinate);
    }

    public void spawnAICar(Coordinate coordinate, AICarType aiCarType){
        gameObjectHandler.spawnAICar(coordinate, aiCarType);
    }




}

package de.cargame.model.handler;

import de.cargame.model.entity.AICarType;
import de.cargame.model.entity.CarType;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.AICar;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import de.cargame.model.service.GameObjectCreationService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GameObjectHandler {
    private final CollisionHandler collisionHandler;
    private final GameObjectCreationService gameObjectCreationService;
    private final PlayerHandler playerHandler;
    private List<GameObject> gameObjects = new ArrayList<>();


    public GameObjectHandler() {
        this.playerHandler = new PlayerHandler();
        this.collisionHandler = new CollisionHandler(playerHandler);
        gameObjectCreationService = new GameObjectCreationService();
    }


    public List<GameObject> update(double deltaTime) {
        for (GameObject gameObject : gameObjects) {

        }
        return null;//todo
    }


    public void spawnPlayerCar(Coordinate coordinate, Player player, CarType carType){
        PlayerCar playerCar = gameObjectCreationService.createPlayerCar(coordinate, player, carType);
        gameObjects.add(playerCar);
    }

    public void spawnBuilding(Coordinate coordinate){
        Building building = gameObjectCreationService.createBuilding(coordinate);
        gameObjects.add(building);
    }

    public void spawnRoad(Coordinate coordinate){
        Road road = gameObjectCreationService.createRoad(coordinate);
        gameObjects.add(road);
    }

    public void spawnObstacle(Coordinate coordinate){
        Obstacle obstacle = gameObjectCreationService.createObstacle(coordinate);
        gameObjects.add(obstacle);
    }

    public void spawnReward(Coordinate coordinate){
        Reward reward = gameObjectCreationService.createReward(coordinate);
        gameObjects.add(reward);
    }

    public void spawnAICar(Coordinate coordinate, AICarType aiCarType){
        AICar aiCar = gameObjectCreationService.createAICar(coordinate, aiCarType);
        gameObjects.add(aiCar);
    }

    public List<GameObject> getGameAllObjects() {
        return gameObjects;
    }
}

package de.cargame.model.handler;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.gameobject.AICarType;
import de.cargame.model.entity.gameobject.CarType;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.AICar;
import de.cargame.model.entity.gameobject.car.MovementStrategy;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import de.cargame.model.service.GameObjectCreationService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GameObjectHandler {
    private final CollisionHandler collisionHandler;
    private final GameObjectCreationService gameObjectCreationService;

    private final GameObjectSpawnHandler gameObjectSpawnHandler;
    private final PlayerHandler playerHandler;
    private List<GameObject> gameObjects = new ArrayList<>();


    public GameObjectHandler() {
        this.playerHandler = new PlayerHandler();
        this.collisionHandler = new CollisionHandler(playerHandler);
        this.gameObjectCreationService = new GameObjectCreationService();
        this.gameObjectSpawnHandler = new GameObjectSpawnHandler();
    }


    public void startGame(){
        gameObjectSpawnHandler.startSpawning(this);
    }

    public void stopGame(){
        gameObjectSpawnHandler.stopSpawning();
    }

    public void moveElements(double deltaTime) {
        for (GameObject gameObject : gameObjects) {
            if(isStaticElement(gameObject)){
                moveObjectStatic(gameObject, deltaTime);
            } else if (gameObject instanceof AICar) {
                moveAICar(gameObject, deltaTime); //todo logik für verschiedene Fahrweisen
            }
        }
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

    public List<GameObject> getAllStaticElements(){
        return gameObjects.stream()
                .filter(this::isStaticElement)
                .toList();
    }

    private boolean isStaticElement(GameObject gameObject){
        boolean isRoad = gameObject instanceof Road;
        boolean isObstacle = gameObject instanceof Obstacle;
        boolean isReward = gameObject instanceof Reward;
        boolean isBuilding = gameObject instanceof Building;

        return isRoad || isObstacle || isReward || isBuilding;
    }


    private void moveObjectStatic(GameObject gameObject, Double deltaTime){
        gameObject.moveBy(-GameConfig.GAME_SPEED * deltaTime, 0);
    }

    private void moveAICar(GameObject gameObject, Double deltaTime){

        gameObject.moveBy(-GameConfig.GAME_SPEED * deltaTime *GameConfig.AI_CAR_SPEED, 0);
    }
}

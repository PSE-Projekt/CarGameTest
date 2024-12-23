package de.cargame.model.handler;

import de.cargame.config.GameConfig;
import de.cargame.controller.GameStateController;
import de.cargame.controller.entity.GameMode;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.AICar;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import de.cargame.model.service.GameObjectCreationService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class GameObjectHandler {
    private final CollisionHandler collisionHandler;
    private final GameObjectCreationService gameObjectCreationService;
    private final GameStateController gameStateController;
    private final PlayerHandler playerHandler;
    private final GameObjectSpawnScheduler gameObjectSpawnScheduler;
    private final List<GameObject> gameObjects = new CopyOnWriteArrayList<>();


    public GameObjectHandler(GameStateController gameStateController) {
        this.gameStateController = gameStateController;
        this.playerHandler = new PlayerHandler();
        this.collisionHandler = new CollisionHandler(playerHandler);
        this.gameObjectCreationService = new GameObjectCreationService();
        this.gameObjectSpawnScheduler = new GameObjectSpawnScheduler();
    }


    public void startGame() {

        GameMode gameMode = gameStateController.getGameMode();
        switch (gameMode) {
            case SINGLEPLAYER:
                gameObjectSpawnScheduler.startSpawning(this);
                gameObjectSpawnScheduler.stopSpawning();
                gameObjectSpawnScheduler.startSpawning(this);
            case MULTIPLAYER:
        }

    }

    public void stopGame() {
        gameObjectSpawnScheduler.stopSpawning();
    }

    public void moveElements(double deltaTime) {
        for (GameObject gameObject : gameObjects) {
            if (isStaticElement(gameObject)) {
                moveObjectStatic(gameObject, deltaTime);
            } else if (gameObject instanceof AICar) {
                moveAICar(gameObject, deltaTime); //todo logik für verschiedene Fahrweisen
            }
        }
    }

    public void despawnPassedObjects() {
        List<GameObject> gameObjectsToRemove = new ArrayList<>();

        for (GameObject gameObject : gameObjects) {
            if (!(gameObject instanceof PlayerCar) && !(gameObject instanceof Road)) {
                if (gameObject.getX() + GameConfig.SCREEN_WIDTH < 0) {
                    gameObjectsToRemove.add(gameObject);
                }
            }
        }
        gameObjects.removeAll(gameObjectsToRemove);
    }


    public void spawnPlayerCar(Coordinate coordinate, Player player, CarType carType) {
        PlayerCar playerCar = gameObjectCreationService.createPlayerCar(coordinate, player, carType);
        gameObjects.add(playerCar);
    }

    public void spawnBuilding() {
        GameMode gameMode = gameStateController.getGameMode();
        List<Building> building = gameObjectCreationService.createBuildings(gameMode);
        gameObjects.addAll(building);
    }

    public void spawnRoad(Coordinate coordinate) {
        Road road = gameObjectCreationService.createRoad(coordinate);
        gameObjects.add(road);
    }

    public void spawnObstacle() {
        GameMode gameMode = gameStateController.getGameMode();
        List<Obstacle> obstacle = gameObjectCreationService.createObstacle(gameMode);
        gameObjects.addAll(obstacle);
    }

    public void spawnReward() {
        GameMode gameMode = gameStateController.getGameMode();
        Reward reward = gameObjectCreationService.createReward(gameMode);
        gameObjects.add(reward);
    }

    public void spawnAICar(AICarType aiCarType) {
        GameMode gameMode = gameStateController.getGameMode();
        AICar aiCar = gameObjectCreationService.createAICar(gameMode, aiCarType);
        System.out.println("Spawn AI Car: " + aiCar.getCoordinates().toString() + " /\\ \t" + System.currentTimeMillis());
        gameObjects.add(aiCar);
    }

    public List<GameObject> getGameAllObjects() {
        return gameObjects;
    }

    public List<GameObject> getAllStaticElements() {
        return gameObjects.stream()
                .filter(this::isStaticElement)
                .toList();
    }

    private boolean isStaticElement(GameObject gameObject) {
        boolean isRoad = gameObject instanceof Road;
        boolean isObstacle = gameObject instanceof Obstacle;
        boolean isReward = gameObject instanceof Reward;
        boolean isBuilding = gameObject instanceof Building;

        return isRoad || isObstacle || isReward || isBuilding;
    }


    private void moveObjectStatic(GameObject gameObject, Double deltaTime) {
        gameObject.moveBy(-GameConfig.GAME_SPEED * deltaTime, 0);
    }

    private void moveAICar(GameObject gameObject, Double deltaTime) {
        gameObject.moveBy(-GameConfig.GAME_SPEED * deltaTime * GameConfig.AI_CAR_SPEED, 0);
    }

}

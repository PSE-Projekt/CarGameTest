package de.cargame.model.handler;

import de.cargame.config.GameConfig;
import de.cargame.controller.GameStateController;
import de.cargame.controller.entity.GameMode;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.AICar;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import de.cargame.model.handler.entity.GameStartParameter;
import de.cargame.model.handler.entity.MultiplayerSpawningStrategy;
import de.cargame.model.handler.entity.SinglePlayerSpawningStrategy;
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


    public GameObjectHandler(GameStateController gameStateController, PlayerHandler playerHandler) {
        this.gameStateController = gameStateController;
        this.playerHandler = playerHandler;
        this.collisionHandler = new CollisionHandler(playerHandler);
        this.gameObjectCreationService = new GameObjectCreationService();
        this.gameObjectSpawnScheduler = new GameObjectSpawnScheduler();
    }


    public void startGame() {
        GameMode gameMode = gameStateController.getGameMode();
        switch (gameMode) {
            case SINGLEPLAYER:
                gameObjectCreationService.setGameObjectSpawningStrategy(new SinglePlayerSpawningStrategy());
                break;
            case MULTIPLAYER:
                gameObjectCreationService.setGameObjectSpawningStrategy(new MultiplayerSpawningStrategy());
                break;
        }
        gameObjectSpawnScheduler.startSpawning(this);
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
        List<Building> building = gameObjectCreationService.createBuildings();
        gameObjects.addAll(building);
    }

    public void spawnRoad(Coordinate coordinate) {
        Road road = gameObjectCreationService.createRoad(coordinate);
        gameObjects.add(road);
    }

    public void spawnObstacle() {
        List<Obstacle> obstacle = gameObjectCreationService.createObstacle();
        gameObjects.addAll(obstacle);
    }

    public void spawnReward() {
        Reward reward = gameObjectCreationService.createReward();
        gameObjects.add(reward);
    }

    public void spawnAICar(AICarType aiCarType) {
        AICar aiCar = gameObjectCreationService.createAICar(aiCarType);
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

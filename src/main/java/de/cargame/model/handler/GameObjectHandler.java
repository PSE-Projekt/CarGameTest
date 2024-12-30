package de.cargame.model.handler;

import de.cargame.config.GameConfig;
import de.cargame.controller.GameStateController;
import de.cargame.controller.entity.GameMode;
import de.cargame.controller.entity.GameState;
import de.cargame.model.entity.Collision;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.AICar;
import de.cargame.model.entity.gameobject.car.PlayerCar;
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
        this.playerHandler = playerHandler;
        this.gameStateController = gameStateController;
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
        spawnPlayerCar(playerHandler.getKeyboardPlayerId(), CarType.AGILE_CAR);
        gameObjectSpawnScheduler.startSpawning(this);
    }

    public void stopGame() {
        gameObjectSpawnScheduler.stopSpawning();
        gameObjects.removeAll(gameObjects);
        gameStateController.setGameState(GameState.SCORE_BOARD);
    }

    public void moveElements(double deltaTime) {
        for (GameObject gameObject : gameObjects) {
            gameObject.move(deltaTime);
        }
    }

    public void despawnPassedObjects() {
        List<GameObject> gameObjectsToRemove = new ArrayList<>();
        List<GameObject> despawnableObjects = gameObjects.stream()
                .filter(GameObject::isDespawnable)
                .toList();

        for (GameObject gameObject : despawnableObjects) {
            if ((gameObject.getX() + GameConfig.SCREEN_WIDTH) < 0) {
                gameObjectsToRemove.add(gameObject);
            }
        }
        gameObjects.removeAll(gameObjectsToRemove);
    }


    public void spawnPlayerCar(String playerId, CarType carType) {
        PlayerCar playerCar = gameObjectCreationService.createPlayerCar(carType);
        playerCar.setPlayerId(playerId);
        playerCar.setPlayerHandler(playerHandler);
        playerHandler.setPlayerCar(playerId, playerCar);
        gameObjects.add(playerCar);
    }

    public void spawnBuilding() {
        List<Building> building = gameObjectCreationService.createBuildings();
        gameObjects.addAll(building);
    }

    public void spawnRoadMarks() {
        List<RoadMark> roadMark = gameObjectCreationService.createRoadMark();
        gameObjects.addAll(roadMark);
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


    public void checkCollision() {
        List<Collision> collisions = collisionHandler.checkCollision(gameObjects);
        if (!playerHandler.atLeastOneActivePlayerAlive()) {
            stopGame();
        }
    }
}

package de.cargame.model.handler;

import de.cargame.config.GameConfig;
import de.cargame.controller.GameStateController;
import de.cargame.controller.entity.GameMode;
import de.cargame.controller.entity.GameState;
import de.cargame.model.entity.collision.Collision;
import de.cargame.model.entity.player.Player;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.AICar;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import de.cargame.model.handler.entity.MultiplayerSpawningStrategy;
import de.cargame.model.handler.entity.SinglePlayerSpawningStrategy;
import de.cargame.model.service.GameObjectCreationService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        this.gameObjectSpawnScheduler = new GameObjectSpawnScheduler(this.playerHandler);
    }


    public void startGame() {
        GameMode gameMode = gameStateController.getGameMode();
        gameStateController.setGameState(GameState.IN_GAME);
        List<Player> activeAndAlivePlayers = playerHandler.getActiveAndAlivePlayers();
        switch (gameMode) {
            case SINGLEPLAYER:
                gameObjectCreationService.setGameObjectSpawningStrategy(new SinglePlayerSpawningStrategy());
                Optional<Player> playerOptional = activeAndAlivePlayers.stream().findFirst();
                if (playerOptional.isPresent()) {
                    Player player = playerOptional.get();
                    spawnPlayerCar(player.getId(), player.getCarSelection());
                }
                break;
            case MULTIPLAYER:
                gameObjectCreationService.setGameObjectSpawningStrategy(new MultiplayerSpawningStrategy());
                break;
        }
        playerHandler.resetScores();
        gameObjectSpawnScheduler.startSpawning(this);
    }

    public void stopGame() {
        gameObjectSpawnScheduler.stopSpawning();
        gameObjects.removeAll(gameObjects);
        gameStateController.setGameState(GameState.SCORE_BOARD);
    }


    public void update(double deltaTime) {
        moveElements(deltaTime);
        despawnPassedObjects();
        checkCollision();
        System.out.println(gameObjects.size());
    }

    public void moveElements(double deltaTime) {
        for (GameObject gameObject : gameObjects) {
            String belongingPlayerId = gameObject.getBelongingPlayerId();
            boolean fastForwarding = playerHandler.isFastForwarding(belongingPlayerId);
            gameObject.move(deltaTime, fastForwarding);
        }
    }

    public void despawnPassedObjects() {
        List<GameObject> gameObjectsToRemove = new ArrayList<>();
        List<GameObject> despawnableObjects = gameObjects.stream()
                .filter(GameObject::isDespawnable)
                .toList();

        for (GameObject gameObject : despawnableObjects) {
            if ((gameObject.getX() +gameObject.getWidth() < 0)) {
                gameObjectsToRemove.add(gameObject);
            }
        }
        System.out.println("Remove: "+gameObjectsToRemove.size());
        gameObjects.removeAll(gameObjectsToRemove);
    }


    public void spawnPlayerCar(String playerId, CarType carType) {
        PlayerCar playerCar = gameObjectCreationService.createPlayerCar(carType, playerId);
        playerCar.setPlayerId(playerId);
        playerCar.setPlayerHandler(playerHandler);
        playerHandler.setPlayerCar(playerId, playerCar);
        gameObjects.add(playerCar);
    }

    public void spawnBuilding(String playerId) {
        List<Building> building = gameObjectCreationService.createBuildings(playerId);
        gameObjects.addAll(building);
    }

    public void spawnRoadMarks(String playerId) {
        List<RoadMark> roadMark = gameObjectCreationService.createRoadMark(playerId);
        gameObjects.addAll(roadMark);
    }

    public void spawnObstacle(String playerId) {
        List<Obstacle> obstacle = gameObjectCreationService.createObstacle(playerId);
        gameObjects.addAll(obstacle);
    }

    public void spawnReward(String playerId) {
        Reward reward = gameObjectCreationService.createReward(playerId);
        gameObjects.add(reward);
    }

    public void spawnAICar(String playerId) {
        AICar aiCar = gameObjectCreationService.createAICar(playerId);
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

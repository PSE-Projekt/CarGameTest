package de.cargame.model.service;

import de.cargame.config.GameConfig;
import de.cargame.controller.GameStateController;
import de.cargame.controller.entity.GameMode;
import de.cargame.exception.InvalidCarSelectionException;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Dimension;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.*;
import de.cargame.model.handler.entity.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GameObjectCreationService {

    private SinglePlayerSpawningStrategy singlePlayerSpawningStrategy;
    private GameObjectSpawningStrategy multiplayerSpawningStrategy;

    public GameObjectCreationService(){
        singlePlayerSpawningStrategy = new SinglePlayerSpawningStrategy();
        multiplayerSpawningStrategy = new MultiplayerSpawningStrategy();
    }


    public PlayerCar createPlayerCar(Coordinate coordinate, Player player, CarType carType) {
        Dimension dimension;
        switch (carType) {
            case FAST_CAR:
                dimension = new Dimension(GameConfig.FAST_CAR_WIDTH, GameConfig.FAST_CAR_HEIGHT);
                return new FastCar(coordinate, dimension, GameObjectBoundType.RECTANGLE);
            case AGILE_CAR:
                dimension = new Dimension(GameConfig.AGILE_CAR_WIDTH, GameConfig.AGILE_CAR_HEIGHT);
                return new AgileCar(coordinate, dimension, GameObjectBoundType.RECTANGLE);
        }
        log.error("No valid car-selection has been made for player {}", player.getId());
        throw new InvalidCarSelectionException("No valid car-selection has been made");
    }

    public List<Building> createBuildings(GameMode gameMode) {
        SpawnAreaList spawnAreas;
        if(gameMode.equals(GameMode.SINGLEPLAYER)){
            spawnAreas = singlePlayerSpawningStrategy.getBuildingSpawnAreas();
            List<Coordinate> spawnCoordinates = spawnAreas.getRandomCoordinateOfEach();
            Dimension dimension = new Dimension(GameConfig.BUILDING_WIDTH, GameConfig.BUILDING_HEIGHT);
            return spawnCoordinates.stream()
                    .map(c -> new Building(c, dimension, GameObjectBoundType.RECTANGLE))
                    .toList();
        } else if (gameMode.equals(GameMode.MULTIPLAYER)) {
            throw new RuntimeException(); //todo
        }
        throw new RuntimeException(); //todo
    }


    public Road createRoad(Coordinate coordinate) {
        Dimension dimension = new Dimension(GameConfig.ROAD_WIDTH, GameConfig.ROAD_HEIGHT);
        return new Road(coordinate, dimension, GameObjectBoundType.RECTANGLE);
    }

    public Obstacle createObstacle(Coordinate coordinate) {
        Dimension dimension = new Dimension(GameConfig.OBSTACLE_WIDTH, GameConfig.OBSTACLE_HEIGHT);
        return new Obstacle(coordinate, dimension, GameObjectBoundType.RECTANGLE);
    }

    public Reward createReward(Coordinate coordinate) {
        Dimension dimension = new Dimension(GameConfig.REWARD_WIDTH, GameConfig.REWARD_HEIGHT);
        return new Life(coordinate, dimension, GameObjectBoundType.RECTANGLE);
    }


    public AICar createAICar(Coordinate coordinate, AICarType aiCarType) {
        Dimension dimension = new Dimension(GameConfig.AI_CAR_WIDTH, GameConfig.AI_CAR_HEIGHT);

        return switch (aiCarType) {
            case CROSS_MOVING ->
                    new KamikazeCar(coordinate, dimension, GameObjectBoundType.RECTANGLE, new CrossMovementStrategy());
            case STRAIGHT_MOVING ->
                    new KamikazeCar(coordinate, dimension, GameObjectBoundType.RECTANGLE, new StraightMovementStrategy());
            default -> {
                log.error("No valid ai moving strategy has been chosen");
                throw new InvalidCarSelectionException("No valid ai moving strategy has been chosen");
            }
        };
    }
}

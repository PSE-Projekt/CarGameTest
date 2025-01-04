package de.cargame.model.service;

import de.cargame.config.GameConfig;
import de.cargame.exception.InvalidCarSelectionException;
import de.cargame.model.entity.gameobject.Coordinate;
import de.cargame.model.entity.gameobject.Dimension;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.*;
import de.cargame.model.handler.entity.GameObjectSpawningStrategy;
import de.cargame.model.handler.entity.SpawnArea;
import de.cargame.model.handler.entity.SpawnAreaList;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;



@Slf4j
public class GameObjectCreationService {

    @Setter
    private GameObjectSpawningStrategy gameObjectSpawningStrategy;

    public PlayerCar createPlayerCar(CarType carType, String playerId) {
        Dimension dimension;
        SpawnAreaList playerSpawnAreas = gameObjectSpawningStrategy.getPlayerSpawnAreas();
        SpawnArea spawnArea = playerSpawnAreas.getRandomSpawnArea();
        playerSpawnAreas.remove(spawnArea);
        Coordinate spawnCoordinate = spawnArea.getRandomCoordinateInArea();

        switch (carType) {
            case FAST_CAR:
                dimension = new Dimension(GameConfig.FAST_CAR_WIDTH, GameConfig.FAST_CAR_HEIGHT);
                return new FastCar(spawnCoordinate, dimension, GameObjectBoundType.RECTANGLE, playerId);
            case AGILE_CAR:
                dimension = new Dimension(GameConfig.AGILE_CAR_WIDTH, GameConfig.AGILE_CAR_HEIGHT);
                return new AgileCar(spawnCoordinate, dimension, GameObjectBoundType.RECTANGLE, playerId);
        }
        log.error("No valid car-selection has been made");
        throw new InvalidCarSelectionException("No valid car-selection has been made");
    }

    public List<Building> createBuildings(String playerId) {
        Dimension dimension = new Dimension(GameConfig.BUILDING_WIDTH, GameConfig.BUILDING_HEIGHT);
        SpawnAreaList spawnAreas = gameObjectSpawningStrategy.getBuildingSpawnAreas();
        List<Coordinate> spawnCoordinates = spawnAreas.getRandomCoordinateOfEach();
        return spawnCoordinates.stream()
                .map(c -> new Building(c, dimension, GameObjectBoundType.RECTANGLE, playerId))
                .toList();
    }


    public List<RoadMark> createRoadMark(String playerId) {
        SpawnAreaList spawnAreas;
        spawnAreas = gameObjectSpawningStrategy.getRoadSpawnAreas();
        List<Coordinate> spawnCoordinates = spawnAreas.getRandomCoordinateOfEach();
        Dimension dimension = new Dimension(GameConfig.ROAD_MARK_WIDTH, GameConfig.ROAD_MARK_HEIGHT);
        return spawnCoordinates.stream()
                .map(c -> new RoadMark(c, dimension, GameObjectBoundType.RECTANGLE, playerId))
                .toList();
    }

    public List<Obstacle> createObstacle(String playerId) {
        SpawnAreaList spawnAreas;
        spawnAreas = gameObjectSpawningStrategy.getObstacleSpawnAreas();
        List<Coordinate> spawnCoordinates = spawnAreas.getRandomCoordinateOfEach();
        Dimension dimension = new Dimension(GameConfig.OBSTACLE_WIDTH, GameConfig.OBSTACLE_HEIGHT);
        return spawnCoordinates.stream()
                .map(c -> new Obstacle(c, dimension, GameObjectBoundType.RECTANGLE, playerId))
                .toList();

    }

    public Reward createReward(String playerId) {
        SpawnAreaList spawnAreas = gameObjectSpawningStrategy.getRewardSpawnAreas();
        Coordinate spawnCoordinate = spawnAreas.getRandomCoordinate();
        Dimension dimension = new Dimension(GameConfig.REWARD_WIDTH, GameConfig.REWARD_HEIGHT);
        return new Life(spawnCoordinate, dimension, GameObjectBoundType.RECTANGLE, playerId);
    }


    public AICar createAICar(String playerId) {
        Dimension dimension = new Dimension(GameConfig.AI_CAR_WIDTH, GameConfig.AI_CAR_HEIGHT);
        SpawnAreaList spawnAreas = gameObjectSpawningStrategy.getAiCarSpawnAreas();
        Coordinate spawnCoordinate = spawnAreas.getRandomCoordinate();

        AICarType aiCarType = getRandomAICarType();

        return switch (aiCarType) {
            case CROSS_MOVING ->
                new KamikazeCar(spawnCoordinate, dimension, GameObjectBoundType.RECTANGLE, new CrossMovementStrategy(spawnCoordinate), playerId);
            case STRAIGHT_MOVING ->
                new KamikazeCar(spawnCoordinate, dimension, GameObjectBoundType.RECTANGLE, new StraightMovementStrategy(spawnCoordinate), playerId);
        };
    }


    private AICarType getRandomAICarType(){
        return AICarType.values()[new Random().nextInt(AICarType.values().length)];
    }

}

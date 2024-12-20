package de.cargame.model.service;

import de.cargame.config.GameConfig;
import de.cargame.exception.InvalidCarSelectionException;
import de.cargame.model.entity.*;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameObjectCreationService {


    public PlayerCar createPlayerCar(Coordinate coordinate, Player player, CarType carType){
        Dimension dimension;
        switch (carType){
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

    public Building createBuilding(Coordinate coordinate) {
        Dimension dimension = new Dimension(GameConfig.BUILDING_WIDTH, GameConfig.BUILDING_HEIGHT);
        return new Building(coordinate, dimension, GameObjectBoundType.RECTANGLE);
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
            case CROSS_MOVING -> new KamikazeCar(coordinate, dimension,GameObjectBoundType.RECTANGLE, new CrossMovementStrategy());
            case STRAIGHT_MOVING -> new KamikazeCar(coordinate, dimension, GameObjectBoundType.RECTANGLE, new StraightMovementStrategy());
            default -> {
                log.error("No valid ai moving strategy has been chosen");
                throw new InvalidCarSelectionException("No valid ai moving strategy has been chosen");
            }
        };
    }
}

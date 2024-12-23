package de.cargame.model.handler.entity;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.Coordinate;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SinglePlayerSpawningStrategy extends GameObjectSpawningStrategy{



    public SinglePlayerSpawningStrategy(){
        super();
        setBuildingSpawnArea();
    }

    @Override
    protected void setBuildingSpawnArea() {
        this.buildingSpawnAreas = new SpawnAreaList();
        buildingSpawnAreas.add(new SpawnArea(GameConfig.SCREEN_WIDTH, 0, GameConfig.SCREEN_WIDTH, GameConfig.BUILDING_SPAWN_WIDTH));
        buildingSpawnAreas.add(new SpawnArea(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT-GameConfig.BUILDING_HEIGHT- GameConfig.BUILDING_SPAWN_WIDTH, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT-GameConfig.BUILDING_HEIGHT));
    }

    @Override
    protected void setObstacleSpawnArea() {

    }

    @Override
    protected void setRewardSpawnArea() {

    }

    @Override
    protected void setRoadSpawnArea() {

    }

    @Override
    protected void setPlayerSpawnArea() {

    }

}

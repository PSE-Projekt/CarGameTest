package de.cargame.model.handler.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class GameObjectSpawningStrategy {



    protected SpawnAreaList buildingSpawnAreas= new SpawnAreaList();
    protected SpawnAreaList obstacleSpawnAreas= new SpawnAreaList();
    protected SpawnAreaList rewardSpawnAreas= new SpawnAreaList();
    protected SpawnAreaList roadSpawnAreas= new SpawnAreaList();
    protected SpawnAreaList playerSpawnAreas= new SpawnAreaList();


    public GameObjectSpawningStrategy(){
        setBuildingSpawnArea();
        setObstacleSpawnArea();
        setRewardSpawnArea();
        setRoadSpawnArea();
        setPlayerSpawnArea();
    }

    protected abstract void setBuildingSpawnArea();
    protected abstract void setObstacleSpawnArea();
    protected abstract void setRewardSpawnArea();
    protected abstract void setRoadSpawnArea();
    protected abstract void setPlayerSpawnArea();



}

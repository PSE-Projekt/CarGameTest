package de.cargame.model.handler.entity;

import de.cargame.config.GameConfig;

public class MultiplayerSpawningStrategy extends GameObjectSpawningStrategy {


    public MultiplayerSpawningStrategy() {
        super();
    }

    @Override
    protected void setBuildingSpawnArea() {

    }

    @Override
    protected void setObstacleSpawnArea() {

    }

    @Override
    protected void setRewardSpawnArea() {

    }

    @Override
    protected void setRoadMarkSpawnArea() {

    }

    @Override
    protected void setPlayerSpawnArea() {

    }

    @Override
    protected void setAiCarSpawnArea() {

    }


    private SpawnArea generateRoadSpawnArea(int customHeight) {
        return new SpawnArea(GameConfig.SCREEN_WIDTH, GameConfig.BUILDING_HEIGHT + GameConfig.BUILDING_SPAWN_WIDTH, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT - GameConfig.BUILDING_SPAWN_WIDTH - GameConfig.BUILDING_HEIGHT - customHeight);
    }

    private SpawnAreaList generateBuildingSpawnAreas() {
        this.buildingSpawnAreas = new SpawnAreaList();
        buildingSpawnAreas.add(new SpawnArea(GameConfig.SCREEN_WIDTH, 0, GameConfig.SCREEN_WIDTH, GameConfig.BUILDING_SPAWN_WIDTH));
        buildingSpawnAreas.add(new SpawnArea(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT - GameConfig.BUILDING_HEIGHT - GameConfig.BUILDING_SPAWN_WIDTH, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT - GameConfig.BUILDING_HEIGHT));

        return buildingSpawnAreas;
    }
}

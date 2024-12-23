package de.cargame.model.handler.entity;

import de.cargame.config.GameConfig;

public class SinglePlayerSpawningStrategy extends GameObjectSpawningStrategy {

    public SinglePlayerSpawningStrategy() {
        super();
        setBuildingSpawnArea();
    }

    @Override
    protected void setBuildingSpawnArea() {
        SpawnAreaList spawnAreaList = generateBuildingSpawnAreas();
        this.buildingSpawnAreas = spawnAreaList;
    }

    @Override
    protected void setObstacleSpawnArea() {
        this.obstacleSpawnAreas.add(generateRoadSpawnArea(GameConfig.OBSTACLE_HEIGHT));
    }

    @Override
    protected void setRewardSpawnArea() {
        this.rewardSpawnAreas.add(generateRoadSpawnArea(GameConfig.REWARD_HEIGHT));
    }

    @Override
    protected void setRoadSpawnArea() {

    }

    @Override
    protected void setPlayerSpawnArea() {

    }

    @Override
    protected void setAiCarSpawnArea() {
        this.aiCarSpawnAreas.add(generateRoadSpawnArea(GameConfig.AI_CAR_HEIGHT));
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

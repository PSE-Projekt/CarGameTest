package de.cargame.model.handler;


import de.cargame.config.GameConfig;
import de.cargame.model.entity.gameobject.AICarType;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class GameObjectSpawnScheduler {
    private GameObjectHandler gameObjectHandler;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


    public void startSpawning(GameObjectHandler gameObjectHandler) {
        this.gameObjectHandler = gameObjectHandler;
        scheduleAICar();
        scheduleObstacle();
        scheduleReward();
        scheduleBuilding();
        scheduleRoadMark();
    }


    private void scheduleAICar() {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnAICar(), GameConfig.AI_CAR_SPAWN_TIME_MIN, GameConfig.AI_CAR_SPAWN_TIME_MAX);
    }

    private void scheduleObstacle() {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnObstacle(), GameConfig.OBSTACLE_SPAWN_TIME_MIN, GameConfig.OBSTACLE_SPAWN_TIME_MAX);
    }

    private void scheduleReward() {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnReward(), GameConfig.REWARD_SPAWN_TIME_MIN, GameConfig.REWARD_SPAWN_TIME_MAX);
    }

    private void scheduleBuilding() {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnBuilding(), GameConfig.BUILDING_SPAWN_TIME_MIN, GameConfig.BUILDING_SPAWN_TIME_MAX);
    }


    private void scheduleRoadMark() {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnRoadMarks(), GameConfig.ROAD_MARK_SPAWN_TIME_MIN, GameConfig.ROAD_MARK_SPAWN_TIME_MAX);
    }


    public void stopSpawning() {
        scheduler.shutdownNow();
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    private int getRandomDelay(int minDelay, int maxDelay) {
        return ThreadLocalRandom.current().nextInt(minDelay, maxDelay);
    }

    private void scheduleSpawn(Supplier<Runnable> spawnAction, int minDelay, int maxDelay) {
        int initialDelay = getRandomDelay(minDelay, maxDelay);
        scheduler.schedule(() -> {
            spawnAction.get().run();
            scheduleSpawn(spawnAction, minDelay, maxDelay);
        }, initialDelay, TimeUnit.MILLISECONDS);
    }




}

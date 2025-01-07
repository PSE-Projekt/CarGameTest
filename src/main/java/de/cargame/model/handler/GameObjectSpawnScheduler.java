package de.cargame.model.handler;


import de.cargame.config.GameConfig;
import de.cargame.model.service.GameObjectService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class GameObjectSpawnScheduler {
    private final double fastForwardSpeedFactor = (double) GameConfig.GAME_SPEED / GameConfig.GAME_SPEED_FAST_FORWARD;
    private final GameObjectService gameObjectService;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final PlayerHandler playerHandler;

    public GameObjectSpawnScheduler(PlayerHandler playerHandler, GameObjectService gameObjectService) {
        this.playerHandler = playerHandler;
        this.gameObjectService = gameObjectService;
    }


    public void startSpawning() {
        String playerId = this.playerHandler.getPlayer().getId();

        scheduleAICar();
        scheduleObstacle();
        scheduleReward();
        scheduleBuilding();
        scheduleRoadMark();

    }

    private void scheduleAICar() {
        scheduleSpawn(() -> gameObjectService::spawnAICar, GameConfig.AI_CAR_SPAWN_TIME_MIN, GameConfig.AI_CAR_SPAWN_TIME_MAX);
    }

    private void scheduleObstacle() {
        scheduleSpawn(() -> gameObjectService::spawnObstacle, GameConfig.OBSTACLE_SPAWN_TIME_MIN, GameConfig.OBSTACLE_SPAWN_TIME_MAX);
    }

    private void scheduleReward() {
        scheduleSpawn(() -> gameObjectService::spawnReward, GameConfig.REWARD_SPAWN_TIME_MIN, GameConfig.REWARD_SPAWN_TIME_MAX);
    }

    private void scheduleBuilding() {
        scheduleSpawn(() -> gameObjectService::spawnBuilding, GameConfig.BUILDING_SPAWN_TIME_MIN, GameConfig.BUILDING_SPAWN_TIME_MAX);
    }


    private void scheduleRoadMark() {
        scheduleSpawn(() -> gameObjectService::spawnRoadMarks, GameConfig.ROAD_MARK_SPAWN_TIME_MIN, GameConfig.ROAD_MARK_SPAWN_TIME_MAX);
    }


    public void stopSpawning() {
        scheduler.shutdownNow();
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    private int getRandomDelay(int minDelay, int maxDelay, boolean isFastForwarding) {
        if (isFastForwarding) {
            minDelay = (int) Math.max(1, minDelay * fastForwardSpeedFactor);
            maxDelay = (int) Math.max(1, maxDelay * fastForwardSpeedFactor) + 1;
        } else {
            minDelay = Math.max(1, minDelay);
            maxDelay = Math.max(1, maxDelay) + 1;
        }
        return ThreadLocalRandom.current().nextInt(minDelay, maxDelay);
    }

    private void scheduleSpawn(Supplier<Runnable> spawnAction, int minDelay, int maxDelay) {
        boolean isFastForwarding = playerHandler.isFastForwarding();
        int initialDelay = getRandomDelay(minDelay, maxDelay, isFastForwarding);
        scheduler.schedule(() -> {
            spawnAction.get().run();
            scheduleSpawn(spawnAction, minDelay, maxDelay);
        }, initialDelay, TimeUnit.MILLISECONDS);
    }


}

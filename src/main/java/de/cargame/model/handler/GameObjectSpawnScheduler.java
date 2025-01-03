package de.cargame.model.handler;


import de.cargame.config.GameConfig;
import de.cargame.model.entity.player.Player;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class GameObjectSpawnScheduler {
    private GameObjectHandler gameObjectHandler;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private PlayerHandler playerHandler;

    private final double fastForwardSpeedFactor = (double) GameConfig.GAME_SPEED / GameConfig.GAME_SPEED_FAST_FORWARD;

    public GameObjectSpawnScheduler(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }


    public void startSpawning(GameObjectHandler gameObjectHandler) {
        this.gameObjectHandler = gameObjectHandler;
        List<Player> players = playerHandler.getActiveAndAlivePlayers();

        for (Player player : players) {
            String playerId = player.getId();
            scheduleAICar(playerId);
            scheduleObstacle(playerId);
            scheduleReward(playerId);
            scheduleBuilding(playerId);
            scheduleRoadMark(playerId);
        }
    }

    private void scheduleAICar(String playerId) {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnAICar(playerId), GameConfig.AI_CAR_SPAWN_TIME_MIN, GameConfig.AI_CAR_SPAWN_TIME_MAX, playerId);
    }

    private void scheduleObstacle(String playerId) {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnObstacle(playerId), GameConfig.OBSTACLE_SPAWN_TIME_MIN, GameConfig.OBSTACLE_SPAWN_TIME_MAX, playerId);
    }

    private void scheduleReward(String playerId) {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnReward(playerId), GameConfig.REWARD_SPAWN_TIME_MIN, GameConfig.REWARD_SPAWN_TIME_MAX, playerId);
    }

    private void scheduleBuilding(String playerId) {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnBuilding(playerId), GameConfig.BUILDING_SPAWN_TIME_MIN, GameConfig.BUILDING_SPAWN_TIME_MAX, playerId);
    }


    private void scheduleRoadMark(String playerId) {
        System.out.println("-------------ROAD MARK SPAWN--------------");
        scheduleSpawn(() -> () -> gameObjectHandler.spawnRoadMarks(playerId), GameConfig.ROAD_MARK_SPAWN_TIME_MIN, GameConfig.ROAD_MARK_SPAWN_TIME_MAX, playerId);
    }


    public void stopSpawning() {
        scheduler.shutdownNow();
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    private int getRandomDelay(int minDelay, int maxDelay, boolean isFastForwarding) {
        if(isFastForwarding){
            minDelay = (int) Math.max(1, minDelay * fastForwardSpeedFactor);
            maxDelay = (int) Math.max(1, maxDelay * fastForwardSpeedFactor)+1;
        }else {
            minDelay = Math.max(1, minDelay);
            maxDelay = Math.max(1, maxDelay)+1;
        }
        return ThreadLocalRandom.current().nextInt(minDelay, maxDelay);
    }

    private void scheduleSpawn(Supplier<Runnable> spawnAction, int minDelay, int maxDelay, String playerId) {
        boolean isFastForwarding = playerHandler.isFastForwarding(playerId);
        int initialDelay = getRandomDelay(minDelay, maxDelay, isFastForwarding);
        scheduler.schedule(() -> {
            spawnAction.get().run();
            scheduleSpawn(spawnAction, minDelay, maxDelay, playerId);
        }, initialDelay, TimeUnit.MILLISECONDS);
    }



}

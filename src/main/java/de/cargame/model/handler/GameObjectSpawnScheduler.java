package de.cargame.model.handler;


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
        scheduleSpawn(() -> () -> gameObjectHandler.spawnAICar(), 400, 1500);
    }

    private void scheduleObstacle() {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnObstacle(), 700, 1400);
    }

    private void scheduleReward() {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnReward(), 20000, 50000);
    }

    private void scheduleBuilding() {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnBuilding(), 500, 1300);
    }


    private void scheduleRoadMark() {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnRoadMarks(), 1000, 1001);
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

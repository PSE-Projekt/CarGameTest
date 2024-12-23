package de.cargame.model.handler;


import de.cargame.model.entity.gameobject.AICarType;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class GameObjectSpawnScheduler {


    private GameObjectHandler gameObjectHandler;
    private Timer timer;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


    public void startSpawning(GameObjectHandler gameObjectHandler) {
        this.gameObjectHandler = gameObjectHandler;

        timer = new Timer();
        scheduleAICar();
        scheduleObstacle();
        scheduleReward();
        scheduleBuilding();
    }

    private void scheduleAICar() {
        //todo implement random event for straight / cross moving
        scheduleSpawn(() -> () -> gameObjectHandler.spawnAICar(AICarType.STRAIGHT_MOVING), 1200, 3000);
    }

    private void scheduleObstacle() {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnObstacle(), 1500, 1900);
    }

    private void scheduleReward() {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnReward(), 20000, 50000);
    }

    private void scheduleBuilding() {
        scheduleSpawn(() -> () -> gameObjectHandler.spawnBuilding(), 500, 1300);
    }

    public void stopSpawning() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private int getRandomDelay(int minDelay, int maxDelay) {
        return ThreadLocalRandom.current().nextInt(minDelay, maxDelay);
    }

    private void scheduleSpawn(Supplier<Runnable> spawnAction, int minDelay, int maxDelay) {
        int initialDelay = ThreadLocalRandom.current().nextInt(minDelay, maxDelay);

        scheduler.schedule(() -> {
            spawnAction.get().run();
            scheduleSpawn(spawnAction, minDelay, maxDelay);
        }, initialDelay, TimeUnit.MILLISECONDS);
    }


}

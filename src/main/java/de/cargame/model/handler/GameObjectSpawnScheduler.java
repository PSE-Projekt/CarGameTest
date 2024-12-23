package de.cargame.model.handler;


import de.cargame.model.entity.gameobject.AICarType;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class GameObjectSpawnScheduler {


    private GameObjectHandler gameObjectHandler;
    private Timer timer;
    private Random random = new Random();


    public void startSpawning(GameObjectHandler gameObjectHandler) {
        this.gameObjectHandler = gameObjectHandler;

        timer = new Timer();
        scheduleAICar();
        scheduleObstacle();
        scheduleReward();
        scheduleBuilding();
    }

    private void scheduleAICar() {
        int delay = ThreadLocalRandom.current().nextInt(2500, 3700);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameObjectHandler.spawnAICar(AICarType.STRAIGHT_MOVING);
                scheduleAICar();

            }
        }, delay);
    }

    private void scheduleObstacle() {
        int delay = ThreadLocalRandom.current().nextInt(5000, 6300);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                scheduleObstacle();
                gameObjectHandler.spawnObstacle();
            }
        }, delay);
    }

    private void scheduleReward() {
        int delay = ThreadLocalRandom.current().nextInt(20000, 50000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                scheduleReward();
                gameObjectHandler.spawnReward();
            }
        }, delay);
    }

    private void scheduleBuilding() {
        int delay = ThreadLocalRandom.current().nextInt(1200, 2600);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                scheduleBuilding();
                gameObjectHandler.spawnBuilding();
            }
        }, delay);
    }


    public void stopSpawning() {
        if (timer != null) {
            timer.cancel();
        }
    }


}

package de.cargame.model.handler;


import de.cargame.model.entity.gameobject.AICarType;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class GameObjectSpawnScheduler {


    private GameObjectHandler gameObjectHandler;
    private Timer timer;

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
        int delay = ThreadLocalRandom.current().nextInt(3000, 4000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                gameObjectHandler.spawnObstacle();
                scheduleObstacle();
            }
        }, delay);
    }

    private void scheduleReward() {
        int delay = ThreadLocalRandom.current().nextInt(20000, 50000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameObjectHandler.spawnReward();
                scheduleReward();
            }
        }, delay);
    }

    private void scheduleBuilding() {
        int delay = ThreadLocalRandom.current().nextInt(1300, 2600);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameObjectHandler.spawnBuilding();
                scheduleBuilding();
            }
        }, delay);
    }

    public void stopSpawning() {
        if (timer != null) {
            timer.cancel();
        }
    }


}

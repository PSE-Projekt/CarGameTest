package de.cargame.model.handler;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.gameobject.AICarType;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
        int delay = random.nextInt(2000) + 1300;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Coordinate coordinate = new Coordinate(GameConfig.SCREEN_WIDTH, random.nextInt(GameConfig.SCREEN_HEIGHT - 100));
                gameObjectHandler.spawnAICar(coordinate, AICarType.STRAIGHT_MOVING);
                scheduleAICar();

            }
        }, delay);
    }

    private void scheduleObstacle() {
        int delay = random.nextInt(5000) + 1300;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Coordinate coordinate = new Coordinate(GameConfig.SCREEN_WIDTH, random.nextInt(GameConfig.SCREEN_HEIGHT - 100));
                gameObjectHandler.spawnObstacle(coordinate);
                scheduleObstacle();
            }
        }, delay);
    }

    private void scheduleReward() {
        int delay = random.nextInt(20000) + 30000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Coordinate coordinate = new Coordinate(GameConfig.SCREEN_WIDTH, random.nextInt(GameConfig.SCREEN_HEIGHT - 100));
                gameObjectHandler.spawnReward(coordinate);
                scheduleReward();
            }
        }, delay);
    }

    private void scheduleBuilding() {
        int delay = random.nextInt(1100) + 1100;
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

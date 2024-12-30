package de.cargame.model.entity.gameobject.car;

import de.cargame.config.GameConfig;
import de.cargame.model.entity.Coordinate;

import java.util.Random;

public class CrossMovementStrategy extends MovementStrategy {

    public CrossMovementStrategy(Coordinate gameObjectSpawnCoordinate) {
        super(gameObjectSpawnCoordinate);
    }

    @Override
    public Coordinate calcTargetPos() {

        Random random = new Random();
        double randomX = -500;

        double randomY = random.nextInt(GameConfig.SCREEN_HEIGHT);
        this.targetPos = new Coordinate(randomX, randomY);
        return targetPos;
    }

}

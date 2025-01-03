package de.cargame.model.entity.player;


import de.cargame.config.GameConfig;
import de.cargame.controller.input.UserInputType;
import de.cargame.controller.input.UserInputBundle;
import de.cargame.model.entity.gameobject.UserInputObserver;
import de.cargame.model.entity.gameobject.CarType;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Player implements UserInputObserver {

    private final String id;
    private UserInputBundle userInputBundle;
    private Score score;
    private PlayerCar playerCar;
    private int lives;
    private boolean isPlaying;
    private CarType carSelection;

    public Player() {
        this.id = UUID.randomUUID().toString();
        this.userInputBundle = new UserInputBundle();
        setDefaultValues();
        this.isPlaying = false;
    }

    @Override
    public void update(UserInputBundle userInputBundle) {
        this.userInputBundle = userInputBundle;
    }

    public double increaseScore(double value) {
        score.increaseScore(value);
        return score.getValue();
    }

    public void setDefaultValues() {
        this.userInputBundle.reset();
        this.score = new Score();
        this.lives = GameConfig.MAX_LIVES;
    }

    public void resetScore() {
        score.reset();
    }

    public int increaseLife() {
        return ++lives;
    }

    public int decreaseLife() {
        return --lives;
    }

    public boolean isAlive() {
        return lives > 0 && isPlaying;
    }


    public boolean isFastForwarding() {
        return this.userInputBundle.isFastForward();
    }

    public UserInputType getUserInput() {
        return this.userInputBundle.getLatestInput();
    }

}

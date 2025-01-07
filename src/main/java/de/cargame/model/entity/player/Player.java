package de.cargame.model.entity.player;


import de.cargame.config.GameConfig;
import de.cargame.controller.input.UserInput;
import de.cargame.controller.input.UserInputBundle;
import de.cargame.model.entity.gameobject.car.player.CarType;
import de.cargame.model.entity.gameobject.car.player.PlayerCar;
import de.cargame.model.entity.gameobject.interfaces.UserInputObserver;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
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

    public void increaseScore(double value) {
        score.increaseScore(value);
    }

    public void setDefaultValues() {
        this.userInputBundle.reset();
        this.score = new Score();
        this.lives = GameConfig.MAX_LIVES;
        this.isPlaying = false;
    }

    public void resetScore() {
        score.reset();
    }

    public void increaseLife() {
        lives++;
    }

    public void decreaseLife() {
        lives--;
    }

    public boolean isAlive() {
        return lives > 0 && isPlaying;
    }


    public boolean isFastForwarding() {
        return this.userInputBundle.isFastForward();
    }

    public Optional<UserInput> getUserInput() {
        return this.userInputBundle.getLatestInput();
    }

}

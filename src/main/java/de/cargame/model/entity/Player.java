package de.cargame.model.entity;


import de.cargame.config.GameConfig;
import de.cargame.controller.input.UserInput;
import de.cargame.model.entity.gameobject.CarType;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Player implements UserInputObserver {

    private final String id;
    private String name;
    private UserInput currentUserInput;
    private Score score;
    private PlayerCar playerCar;
    private int lives;
    private boolean isPlaying;
    private CarType carSelection;


    public Player() {
        this.id = UUID.randomUUID().toString();
        this.name = "Player_" + id;
        setDefaultValues();
        this.isPlaying = false;
    }

    @Override
    public void update(UserInput userInput) {
        currentUserInput = userInput;
    }

    public int increaseScore(int value) {
        score.increaseScore(value);
        return score.getValue();
    }

    public void setDefaultValues() {
        this.currentUserInput = UserInput.NONE;
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

}

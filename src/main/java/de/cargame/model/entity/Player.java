package de.cargame.model.entity;


import de.cargame.config.GameConfig;
import de.cargame.config.UserInput;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Player implements UserInputObserver {


    @Getter
    private final String id;
    private String name;
    @Getter
    private UserInput currentUserInput;
    private Score score;

    @Setter
    @Getter
    private PlayerCar playerCar;
    private int lives;


    public Player() {
        this.id = UUID.randomUUID().toString();
        this.name = "Player_" + id;
        this.currentUserInput = UserInput.NONE;
        this.score = new Score();
        this.lives = GameConfig.MAX_LIVES;
    }

    @Override
    public void update(UserInput userInput) {
        currentUserInput = userInput;
    }

    public int increaseScore(int value) {
        score.increaseScore(value);
        return score.getValue();
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

}

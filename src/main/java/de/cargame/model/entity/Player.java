package de.cargame.model.entity;


import de.cargame.config.UserInput;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import de.cargame.model.handler.PlayerHandler;

import java.util.UUID;

public class Player implements UserInputObserver {


    private final String id;
    private String name;
    private UserInput currentUserInput;
    private Score score;
    private PlayerCar playerCar;

    private int lives;


    public Player() {
        this.id = UUID.randomUUID().toString();
        this.name = "Player_" + id;
        this.currentUserInput = UserInput.NONE;
        this.score = new Score();
        this.lives=3;
    }

    @Override
    public void update(UserInput userInput) {

    }

    public int increaseScore(int value) {
        score.increaseScore(value);
        return score.getValue();
    }

    public void resetScore() {
        score.reset();
    }

    public int increaseLife(){
        return ++lives;
    }

    public int decreaseLife(){
        return --lives;
    }


    public void setPlayerCar(PlayerCar playerCar) {
        this.playerCar = playerCar;
    }

    public PlayerCar getPlayerCar() {
        return playerCar;
    }

    public UserInput getCurrentUserInput() {
        return currentUserInput;
    }

    public void setCurrentUserInput(UserInput userInput) {
        this.currentUserInput = userInput;
    }

}

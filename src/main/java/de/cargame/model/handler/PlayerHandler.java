package de.cargame.model.handler;

import de.cargame.controller.input.UserInput;
import de.cargame.exception.PlayerNotFoundException;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.Score;
import de.cargame.model.entity.gameobject.CarType;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class PlayerHandler {

    private Player playerKeyboard;
    private Player playerGamepad;

    public PlayerHandler() {
        playerKeyboard = new Player();
        playerGamepad = new Player();
    }


    public void increaseScore(String playerId, int value) {
        getPlayer(playerId).increaseScore(value);
    }

    public void resetScore(String playerId) {
        getPlayer(playerId).resetScore();
    }

    public UserInput getCurrentUserInput(String playerId) {
        return getPlayer(playerId).getCurrentUserInput();
    }

    public void setCarSelection(String playerId, CarType carSelection) {
        getPlayer(playerId).setCarSelection(carSelection);
    }

    public PlayerCar getCarSelection(String playerId) {
        return getPlayer(playerId).getPlayerCar();
    }

    public int increaseLife(String playerId) {
        return getPlayer(playerId).increaseLife();
    }


    public int decreaseLife(String playerId) {
        return getPlayer(playerId).decreaseLife();
    }

    public int getLifeCount(String playerId) {
        return getPlayer(playerId).getLives();
    }

    public void setPlayerKeyboard(Player playerKeyboard) {
        this.playerKeyboard = playerKeyboard;
    }

    public void setPlayerGamepad(Player playerGamepad) {
        this.playerGamepad = playerGamepad;
    }

    public void setPlayerCar(String playerId, PlayerCar playerCar) {
        getPlayer(playerId).setPlayerCar(playerCar);
    }

    public int getScore(String playerId){
        return getPlayer(playerId).getScore().getValue();
    }


    public String getKeyboardPlayerId() {
        return playerKeyboard.getId();
    }

    public String getGamepadPlayerId() {
        return playerGamepad.getId();
    }

    public boolean atLeastOneActivePlayerAlive() {
        return playerKeyboard.isAlive() || playerGamepad.isAlive();
    }

    public List<Player> getActiveAndAlivePlayers() {
        List<Player> players = new ArrayList<>();
        if (playerKeyboard.isAlive()) players.add(playerKeyboard);
        if (playerGamepad.isAlive()) players.add(playerGamepad);
        return Collections.unmodifiableList(players);
    }

    private Player getPlayer(String id) {
        if (playerGamepad.getId().equals(id)) {
            return playerGamepad;
        } else if (playerKeyboard.getId().equals(id)) {
            return playerKeyboard;
        } else {
            log.error("Player not found");
            throw new PlayerNotFoundException("Player not found");
        }
    }
}

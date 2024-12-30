package de.cargame.model.handler;

import de.cargame.controller.input.UserInput;
import de.cargame.exception.PlayerNotFoundException;
import de.cargame.model.entity.player.Player;
import de.cargame.model.entity.player.PlayerObserver;
import de.cargame.model.entity.player.PlayerUpdate;
import de.cargame.model.entity.gameobject.CarType;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import de.cargame.model.service.PlayerUpdateNotifyService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Slf4j
public class PlayerHandler {

    private final PlayerUpdateNotifyService playerUpdateNotifyService;

    private Player playerKeyboard;
    private Player playerGamepad;

    public PlayerHandler() {
        this.playerUpdateNotifyService = new PlayerUpdateNotifyService();

        playerKeyboard = new Player();
        playerGamepad = new Player();
    }


    public void increaseScore(String playerId, double value) {
        getPlayer(playerId).increaseScore(value);
        PlayerUpdate playerUpdate = generatePlayerUpdate(playerId);
        this.playerUpdateNotifyService.notifyObservers(playerUpdate);
    }

    public void resetScore(String playerId) {
        getPlayer(playerId).resetScore();
        PlayerUpdate playerUpdate = generatePlayerUpdate(playerId);
        this.playerUpdateNotifyService.notifyObservers(playerUpdate);
    }

    public void resetScores() {
        resetScore(playerKeyboard.getId());
        resetScore(playerGamepad.getId());
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


    public void registerPlayerObserver(PlayerObserver playerObserver){
        playerUpdateNotifyService.addObserver(playerObserver);
    }
    public int increaseLife(String playerId) {
        getPlayer(playerId).increaseLife();
        PlayerUpdate playerUpdate = generatePlayerUpdate(playerId);
        this.playerUpdateNotifyService.notifyObservers(playerUpdate);
        return getLifeCount(playerId);
    }


    public int decreaseLife(String playerId) {
        getPlayer(playerId).decreaseLife();
        PlayerUpdate playerUpdate = generatePlayerUpdate(playerId);
        this.playerUpdateNotifyService.notifyObservers(playerUpdate);
        return getLifeCount(playerId);
    }

    public int getLifeCount(String playerId) {
        return getPlayer(playerId).getLives();
    }

    public void setPlayerCar(String playerId, PlayerCar playerCar) {
        getPlayer(playerId).setPlayerCar(playerCar);
    }

    public double getScore(String playerId) {
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

    private PlayerUpdate generatePlayerUpdate(String playerId) {
        return new PlayerUpdate(playerId, (int) getScore(playerId), getLifeCount(playerId));
    }
}

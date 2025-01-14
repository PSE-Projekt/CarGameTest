package de.cargame.controller;

import de.cargame.controller.api.PlayerAPI;
import de.cargame.model.entity.gameobject.car.player.CarType;
import de.cargame.model.entity.player.Player;
import de.cargame.model.service.PlayerService;

import java.util.Optional;

/**
 * The PlayerController class provides implementation for the PlayerAPI interface.
 * It acts as a bridge between higher-level application logic and the underlying PlayerService,
 * which manages the creation, data retrieval, and state configuration of players.
 * Players can be controlled using either a keyboard or a gamepad, and the class
 * provides methods to manage both types.
 */
public class PlayerController implements PlayerAPI {

    private final PlayerService playerService = new PlayerService();

    @Override
    public void createPlayerKeyboard() {
        playerService.createPlayerKeyboard();
    }

    @Override
    public void createPlayerGamepad() {
        playerService.createPlayerGamepad();
    }

    @Override
    public String getKeyboardPlayerId() {
        String id = getKeyboardPlayer().getId();
        return id;
    }

    @Override
    public String getGamepadPlayerId() {
        String id = getGamepadPlayer().getId();
        return id;
    }

    @Override
    public Player getKeyboardPlayer() {
        return playerService.getKeyboardPlayer();
    }

    @Override
    public Player getGamepadPlayer() {
        return playerService.getGamepadPlayer();
    }

    @Override
    public void setCarSelection(String playerId, CarType carType) {
        playerService.setCarSelection(playerId, carType);
    }

    @Override
    public void setPlaying(String playerId, boolean playing) {
        playerService.setPlaying(playerId, playing);
    }

    @Override
    public boolean isPlaying(String playerId) {
        return playerService.isPlaying(playerId);
    }
}

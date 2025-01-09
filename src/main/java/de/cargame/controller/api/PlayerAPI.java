package de.cargame.controller.api;

import de.cargame.model.entity.gameobject.car.player.CarType;
import de.cargame.model.entity.player.Player;

import java.util.Optional;

public interface PlayerAPI {

    void createPlayerKeyboard();

    void createPlayerGamepad();

    Optional<String> getKeyboardPlayerId();

    Optional<String> getGamepadPlayerId();

    Player getKeyboardPlayer();

    Player getGamepadPlayer();

    void setCarSelection(String playerId, CarType carType);

    void setPlaying(String playerId, boolean playing);

    boolean isPlaying(String playerId);

}

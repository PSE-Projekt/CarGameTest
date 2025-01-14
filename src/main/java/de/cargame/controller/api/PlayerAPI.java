package de.cargame.controller.api;

import de.cargame.model.entity.gameobject.car.player.CarType;
import de.cargame.model.entity.player.Player;

import java.util.Optional;


/**
 * The PlayerAPI interface provides methods to manage player creation, retrieve player information,
 * and configure player state in the game. It supports operations for both keyboard and gamepad-controlled players.
 */
public interface PlayerAPI {


    void createPlayerKeyboard();

    void createPlayerGamepad();

    String getKeyboardPlayerId();

    String getGamepadPlayerId();

    Player getKeyboardPlayer();

    Player getGamepadPlayer();

    void setCarSelection(String playerId, CarType carType);

    void setPlaying(String playerId, boolean playing);

    boolean isPlaying(String playerId);

}

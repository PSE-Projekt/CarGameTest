package de.cargame.controller.api;

import de.cargame.controller.GameApplicationManager;
import de.cargame.controller.entity.GameModelData;
import de.cargame.model.GameInstance;
import de.cargame.model.entity.player.Player;

import java.util.List;

public interface GameInstanceAPI {

    void startGamePlayerKeyboard();
    void startGamePlayerGamePad();

    List<GameModelData> getModel();
}

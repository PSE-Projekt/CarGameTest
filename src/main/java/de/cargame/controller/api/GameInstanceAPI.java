package de.cargame.controller.api;

import de.cargame.controller.entity.GameModelData;

import java.util.List;

public interface GameInstanceAPI {

    void startGamePlayerKeyboard();

    void startGamePlayerGamePad();

    void resetGameInstances();

    List<GameModelData> getModel();
}

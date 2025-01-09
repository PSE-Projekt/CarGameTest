package de.cargame.controller.api;

import de.cargame.controller.entity.GameModelData;

import java.util.List;


/**
 * The GameInstanceAPI interface provides methods to manage and control
 * game instances in the application. It allows initiating games for
 * players using different input devices, resetting game instances,
 * and retrieving game model data.
 */
public interface GameInstanceAPI {

    void startGamePlayerKeyboard();

    void startGamePlayerGamePad();

    void resetGameInstances();

    List<GameModelData> getModel();
}

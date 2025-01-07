package de.cargame.controller.api;

import de.cargame.controller.entity.GameMode;
import de.cargame.controller.entity.GameState;

public interface GameStateAPI {

    GameMode getGameMode();

    void setGameMode(GameMode gameMode);

    GameState getGameState();

    void setGameState(GameState gameState);

}

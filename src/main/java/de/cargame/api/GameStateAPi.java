package de.cargame.api;

import de.cargame.controller.entity.GameMode;
import de.cargame.controller.entity.GameState;

public interface GameStateAPi {

    GameMode getGameMode();

    void setGameMode(GameMode gameMode);

    GameState getGameState();

    void setGameState(GameState gameState);
}

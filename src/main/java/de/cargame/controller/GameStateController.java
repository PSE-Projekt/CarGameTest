package de.cargame.controller;

import de.cargame.api.GameStateAPi;
import de.cargame.controller.entity.GameMode;
import de.cargame.controller.entity.GameState;
import de.cargame.model.handler.GameStateHandler;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStateController implements GameStateAPi {

    private final GameStateHandler gameStateHandler;

    public GameStateController() {
        this.gameStateHandler = new GameStateHandler();
    }


    @Override
    public GameMode getGameMode() {
        return gameStateHandler.getGameMode();
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        gameStateHandler.setGameMode(gameMode);
    }

    @Override
    public GameState getGameState() {
        return gameStateHandler.getGameState();
    }

    @Override
    public void setGameState(GameState gameState) {
        setGameState( gameState);
    }
}

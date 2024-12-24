package de.cargame.controller;

import de.cargame.api.PlayerApi;
import de.cargame.model.handler.PlayerHandler;

public class PlayerController implements PlayerApi {

    private final PlayerHandler playerHandler;

    public PlayerController(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    @Override
    public int getLives(String playerId) {
        return playerHandler.getLifeCount(playerId);
    }

    @Override
    public int getScore(String id) {
        return 0;
    }
}

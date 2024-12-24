package de.cargame.api;

import de.cargame.controller.entity.GameMode;
import de.cargame.controller.entity.ViewState;

public interface GameStateAPi {

    GameMode getGameMode();

    void setGameMode(GameMode gameMode);

    ViewState getViewState();

    void setViewState(ViewState viewState);
}

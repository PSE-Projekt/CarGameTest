package de.cargame.controller;

import de.cargame.controller.entity.GameMode;
import de.cargame.controller.entity.ViewState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStateController {


    private GameMode gameMode;

    private ViewState viewState;


    public GameStateController() {
        gameMode = GameMode.NOT_SET;
        viewState = ViewState.MAIN_MENU;
    }
}

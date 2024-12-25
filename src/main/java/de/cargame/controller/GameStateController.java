package de.cargame.controller;

import de.cargame.controller.entity.GameMode;
import de.cargame.controller.entity.GameState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStateController {


    private GameMode gameMode;

    private GameState gameState;


    public GameStateController() {
        gameMode = GameMode.NOT_SET;
        gameState = GameState.MAIN_MENU;
    }
}

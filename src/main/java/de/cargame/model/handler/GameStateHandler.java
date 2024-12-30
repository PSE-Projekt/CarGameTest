package de.cargame.model.handler;

import de.cargame.controller.entity.GameMode;
import de.cargame.controller.entity.GameState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStateHandler {

    private GameMode gameMode;

    private GameState gameState;

}

package de.cargame.model.handler.entity;

import de.cargame.controller.entity.GameMode;
import de.cargame.model.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GameStartParameter {

    private GameMode gameMode;
    private Player playerOne;
    private Player playerTwo;
}

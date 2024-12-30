package de.cargame.model.entity.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerUpdate {

    private final String playerId;
    private final int scoreValue;
    private final int lives;
}

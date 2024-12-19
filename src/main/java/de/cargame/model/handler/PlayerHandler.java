package de.cargame.model.handler;

import de.cargame.config.UserInput;
import de.cargame.exception.PlayerNotFoundException;
import de.cargame.model.entity.CarType;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class PlayerHandler {

    Map<String, Player> players;

    public PlayerHandler() {
        this.players = new HashMap<>();
    }

    public void increaseScore(String id, int value) {
        getPlayer(id).increaseScore(value);
    }

    public void resetScore(String id) {
        getPlayer(id).resetScore();
    }

    public UserInput getCurrentUserInput(String id) {
        return getPlayer(id).getCurrentUserInput();
    }

    public void setCarSelection(String playerId, CarType carSelection) {


    }

    public PlayerCar getCarSelection(String playerId) {
        return getPlayer(playerId).getPlayerCar();
    }

    public int increaseLife(PlayerCar playerCar) {
        return getPlayer(playerCar).increaseLife();
    }


    public int decreaseLife(PlayerCar playerCar) {
        return getPlayer(playerCar).decreaseLife();
    }


    private Player getPlayer(String id) {
        return players.get(id);
    }

    private Player getPlayer(PlayerCar playerCar) {
        Optional<Player> playerOptional = players.values()
                .stream()
                .filter(p -> p.getPlayerCar() == playerCar)
                .findFirst();

        if(playerOptional.isPresent()) return playerOptional.get();
        log.error("Player not found");
        throw new PlayerNotFoundException("Player not found");
    }
}

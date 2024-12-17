package de.cargame.model.handler;

import de.cargame.config.UserInput;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.car.PlayerCar;

import java.util.HashMap;
import java.util.Map;

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

    public void setCarSelection(String id, PlayerCar playerCar) {
        getPlayer(id).setPlayerCar(playerCar);
    }

    public PlayerCar getCarSelection(String id) {
        return getPlayer(id).getPlayerCar();
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
        return players.values()
                .stream()
                .filter(p -> p.getPlayerCar() == playerCar)
                .toList()
                .getFirst();
    }
}

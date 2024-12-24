package de.cargame.model.handler;

import de.cargame.controller.input.UserInput;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.CarType;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class PlayerHandler {

    private Map<String, Player> players;

    public PlayerHandler() {
        this.players = new HashMap<>();
    }


    public void addPlayer(Player player) {
        if (!players.containsKey(player.getId())) {
            players.put(player.getId(), player);
            return;
        }
        log.warn("The player {} is already present in the playerhandler", player);
    }

    public void removePlayer(Player player) {
        if (players.containsKey(player.getId())) {
            players.remove(player.getId());
            return;
        }
        log.warn("The player {} has already been removed from playerhandler", player);
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

    public int increaseLife(String playerId) {
        return getPlayer(playerId).increaseLife();
    }


    public int decreaseLife(String playerId) {
        return getPlayer(playerId).decreaseLife();
    }

    public int getLifeCount(String playerId) {
        return getPlayer(playerId).getLives();
    }


    private Player getPlayer(String id) {
        return players.get(id);
    }

//    private Player getPlayer(PlayerCar playerCar) {
//        Optional<Player> playerOptional = players.values()
//                .stream()
//                .filter(p -> p.getPlayerCar() == playerCar)
//                .findFirst();

//        if (playerOptional.isPresent()) return playerOptional.get();
//        log.error("Player not found");
//        throw new PlayerNotFoundException("Player not found");
}

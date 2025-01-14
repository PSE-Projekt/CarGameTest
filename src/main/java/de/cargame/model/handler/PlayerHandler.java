package de.cargame.model.handler;

import de.cargame.controller.input.UserInput;
import de.cargame.model.entity.gameobject.car.player.CarType;
import de.cargame.model.entity.gameobject.car.player.PlayerCar;
import de.cargame.model.entity.player.Player;
import de.cargame.model.entity.player.PlayerObserver;
import de.cargame.model.entity.player.PlayerUpdate;
import de.cargame.model.service.PlayerUpdateNotifyService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


@Setter
@Slf4j
public class PlayerHandler {

    private final PlayerUpdateNotifyService playerUpdateNotifyService;

    @Getter
    private Player player;

    public PlayerHandler(Player player) {
        this.player = player;
        this.playerUpdateNotifyService = new PlayerUpdateNotifyService();
    }


    public void increaseScore(double value) {
        player.increaseScore(value);
        PlayerUpdate playerUpdate = generatePlayerUpdate();
        this.playerUpdateNotifyService.notifyObservers(playerUpdate);
    }

    public void resetScore() {
        player.resetScore();
        PlayerUpdate playerUpdate = generatePlayerUpdate();
        this.playerUpdateNotifyService.notifyObservers(playerUpdate);
        System.out.println("reset score");
    }

    public Optional<UserInput> getCurrentUserInput() {
        return player.getUserInput();
    }

    public boolean isFastForwarding() {
        return player.isFastForwarding();
    }


    public void registerPlayerObserver(PlayerObserver playerObserver) {
        playerUpdateNotifyService.addObserver(playerObserver);
    }

    public void increaseLife() {
        player.increaseLife();
        PlayerUpdate playerUpdate = generatePlayerUpdate();
        this.playerUpdateNotifyService.notifyObservers(playerUpdate);
    }


    public void decreaseLife() {
        player.decreaseLife();
        PlayerUpdate playerUpdate = generatePlayerUpdate();
        this.playerUpdateNotifyService.notifyObservers(playerUpdate);
    }

    public int getLifeCount() {
        return player.getLives();
    }

    public void setPlayerCar(PlayerCar playerCar) {
        player.setPlayerCar(playerCar);
    }

    public double getScore() {
        return player.getScore().getValue();
    }


    public boolean isPlayerAlive() {
        return player.isAlive();
    }

    private PlayerUpdate generatePlayerUpdate() {
        return new PlayerUpdate(player.getId(), (int) getScore(), getLifeCount());
    }
}

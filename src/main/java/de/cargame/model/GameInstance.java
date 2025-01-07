package de.cargame.model;

import de.cargame.api.GameStateAPI;
import de.cargame.config.GameConfig;
import de.cargame.controller.ApplicationController;
import de.cargame.controller.GameObjectController;
import de.cargame.controller.entity.GameModelData;
import de.cargame.controller.entity.GameState;
import de.cargame.model.entity.player.Player;
import de.cargame.model.entity.player.PlayerObserver;
import de.cargame.model.handler.PlayerHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameInstance implements Runnable {

    private final GameStateAPI gameStateController;
    private final PlayerHandler playerHandler = new PlayerHandler();
    private final ApplicationController applicationController;
    private final GameObjectController gameObjectController;

@Getter
private boolean isFinished = false;


    public GameInstance(GameStateAPI gameStateController, ApplicationController applicationController, Player player) {
        this.gameStateController = gameStateController;
        this.applicationController = applicationController;
        this.gameObjectController = new GameObjectController(gameStateController, playerHandler);
        this.playerHandler.setPlayer(player);
    }

    @Override
    public void run() {
        gameObjectController.startGame();
        long lastTime = System.nanoTime();
        while (gameStateController.getGameState().equals(GameState.IN_GAME)) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            lastTime = currentTime;

            gameObjectController.update(deltaTime);
            applicationController.renderGameInstance(this);
            try {
                Thread.sleep(GameConfig.FPS);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
        isFinished = true;
        System.out.println("leave");
    }

    public int getScore() {
        return (int) playerHandler.getScore();
    }

    public GameModelData getGameModelData() {
        return new GameModelData(getPlayingPlayerId(), gameObjectController.getAllGameObjects());
    }

    public String getPlayingPlayerId() {
        return playerHandler.getPlayer().getId();
    }

    public void registerUI(PlayerObserver playerObserver) {
        playerHandler.registerPlayerObserver(playerObserver);
    }
}

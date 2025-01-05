package de.cargame.model;

import de.cargame.config.GameConfig;
import de.cargame.controller.ApplicationController;
import de.cargame.controller.GameObjectController;
import de.cargame.controller.GameStateController;
import de.cargame.controller.entity.GameState;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.player.Player;
import de.cargame.model.handler.PlayerHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GameInstance implements Runnable {

    private GameStateController gameStateController;
    private PlayerHandler playerHandler = new PlayerHandler();
    private ApplicationController applicationController;
    private GameObjectController gameObjectController;


    public GameInstance(GameStateController gameStateController, ApplicationController applicationController, Player player) {
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
        System.out.println("leave");
    }

    private void stopGame() {
        gameObjectController.stopGame();
    }

    public List<GameObject> getAllGameObjects() {
        return gameObjectController.getAllGameObjects();
    }

    public double getScore() {
        return playerHandler.getScore();
    }

    public String getPlayingPlayerId() {
        return playerHandler.getPlayer().getId();
    }
}

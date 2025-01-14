package de.cargame.model;

import de.cargame.config.GameConfig;
import de.cargame.controller.GameApplicationManager;
import de.cargame.controller.api.GameStateAPI;
import de.cargame.controller.entity.GameModelData;
import de.cargame.controller.entity.GameState;
import de.cargame.model.entity.player.Player;
import de.cargame.model.entity.player.PlayerObserver;
import de.cargame.model.handler.PlayerHandler;
import de.cargame.model.service.GameObjectService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameInstance implements Runnable {

    private final GameStateAPI gameStateController;
    private final PlayerHandler playerHandler = new PlayerHandler();
    private final GameApplicationManager gameApplicationManager;
    private final GameObjectService gameObjectService;

    @Getter
    private boolean isFinished = false;


    public GameInstance(GameStateAPI gameStateController, GameApplicationManager gameApplicationManager, Player player) {
        this.gameStateController = gameStateController;
        this.gameApplicationManager = gameApplicationManager;
        this.gameObjectService = new GameObjectService(gameStateController, playerHandler);
        this.playerHandler.setPlayer(player);
    }

    /**
     * Executes the core game loop for this game instance. This method manages the
     * game's lifecycle and logic while the game state is set to IN_GAME.
     *
     * The loop performs the following tasks:
     * 1. Starts the game by initializing necessary services and game objects.
     * 2. Maintains a consistent time step (deltaTime) based on the system's nanoTime.
     * 3. Updates the game state, including moving and spawning objects, and handling collisions.
     * 4. Renders the current game state visually via the associated game application manager.
     * 5. Controls the game's frame rate using a sleep interval defined in `GameConfig.FPS`.
     *
     * The loop continues indefinitely as long as the game state remains IN_GAME.
     * If interrupted, the error is logged, and the loop exits, marking the game
     * instance as finished.
     */
    @Override
    public void run() {
        gameObjectService.startGame();
        long lastTime = System.nanoTime();
        while (gameStateController.getGameState().equals(GameState.IN_GAME)) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            lastTime = currentTime;

            gameObjectService.update(deltaTime);
            gameApplicationManager.renderGameInstance(this);
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

    /**
     * Retrieves the current game model data for this game instance.
     * The game model data includes the ID of the currently playing player
     * and a list of all game objects present in the game.
     *
     * @return a GameModelData object containing the player ID and the list of game objects
     */
    public GameModelData getGameModelData() {
        return new GameModelData(getPlayingPlayerId(), gameObjectService.getAllGameObjects());
    }

    public String getPlayingPlayerId() {
        return playerHandler.getPlayer().getId();
    }

    public void registerUI(PlayerObserver playerObserver) {
        playerHandler.registerPlayerObserver(playerObserver);
    }
}

package de.cargame.controller;

import de.cargame.config.GameConfig;
import de.cargame.controller.entity.GameMode;
import de.cargame.controller.entity.GameModelData;
import de.cargame.controller.entity.GameState;
import de.cargame.controller.input.GamePad;
import de.cargame.controller.input.Keyboard;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.CarType;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.handler.PlayerHandler;
import de.cargame.view.TestView;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GameController {


    private final GameStateController gameStateController = new GameStateController();
    private final PlayerHandler playerHandler = new PlayerHandler();
    private final PlayerController playerController = new PlayerController(playerHandler);
    private final GameObjectController gameObjectController = new GameObjectController(gameStateController, playerHandler);


    private TestView testView;

    public GameController() {
        run();
    }

    private void run() {
        //createUI();
        testView = new TestView(this);
        initializePlayerKeyboard();
        dummyStartGame();
    }

    public void startGame() {
        gameObjectController.startGame();
        long lastTime = System.nanoTime();
        while (gameStateController.getGameState().equals(GameState.IN_GAME)) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            lastTime = currentTime;

            gameObjectController.update(deltaTime);
            testView.render();

            try {
                Thread.sleep(GameConfig.FPS);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
        System.out.println("leave");
    }

    public GameModelData getModel() {
        List<GameObject> allGameObjects = gameObjectController.getAllGameObjects();
        return new GameModelData(allGameObjects);
    }

    private String initializePlayerKeyboard() {
        Player player = new Player();
        Keyboard keyboard = new Keyboard(player.getId());
        keyboard.registerObserver(player);
        playerController.setPlayerKeyboard(player);

        player.setPlaying(true);

        return player.getId();
    }

    private String initializePlayerGamePad() {
        Player player = new Player();
        GamePad gamepad = new GamePad(player.getId());
        gamepad.registerObserver(player);
        playerHandler.setPlayerKeyboard(player);

        //TODO REMOVE
        player.setPlaying(true);
        //TODO REMOVE

        return player.getId();
    }


    private void dummyStartGame() {
        //TODO REMOVE--------
        String playerId = playerHandler.getKeyboardPlayerId();
        playerHandler.setCarSelection(playerId, CarType.AGILE_CAR);

        gameStateController.setGameMode(GameMode.SINGLEPLAYER);
        startGame();
        //TODO REMOVE--------

        startGame();
    }
}

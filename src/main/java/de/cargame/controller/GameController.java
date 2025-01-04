package de.cargame.controller;

import de.cargame.config.GameConfig;
import de.cargame.controller.entity.GameMode;
import de.cargame.controller.entity.GameModelData;
import de.cargame.controller.entity.GameState;
import de.cargame.controller.input.GamePad;
import de.cargame.controller.input.Keyboard;
import de.cargame.model.entity.player.Player;
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
        initializePlayerGamePad();
        dummyStartGameMultiplayer();
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
        Player keyboardPlayer = playerHandler.getKeyboardPlayer();
        String keyboardPlayerId = keyboardPlayer.getId();
        Keyboard keyboard = new Keyboard(keyboardPlayerId);
        keyboard.registerObserver(keyboardPlayer);
        playerController.setPlayerKeyboard(keyboardPlayer);

        keyboardPlayer.setPlaying(true);

        return keyboardPlayerId;
    }

    private String initializePlayerGamePad() {
        Player gamepadPlayer = playerHandler.getGamepadPlayer();
        String gamepadPlayerId = gamepadPlayer.getId();
        GamePad gamePad = new GamePad(gamepadPlayerId);
        gamePad.registerObserver(gamepadPlayer);
        playerController.setPlayerGamepad(gamepadPlayer);

        gamepadPlayer.setPlaying(true);

        return gamepadPlayerId;
    }


    private void dummyStartGame() {
        //TODO REMOVE--------
        String playerId = playerHandler.getKeyboardPlayerId();
        playerHandler.setCarSelection(playerId, CarType.AGILE_CAR);
        playerHandler.registerPlayerObserver(testView.getjPanel());

        gameStateController.setGameMode(GameMode.SINGLEPLAYER);
        startGame();
        //TODO REMOVE--------
    }

    private void dummyStartGameMultiplayer() {
        //TODO REMOVE--------
        String playerIdKeyboard = playerHandler.getKeyboardPlayerId();
        String playerIdGamepad = playerHandler.getGamepadPlayerId();

        playerHandler.setCarSelection(playerIdKeyboard, CarType.AGILE_CAR);
        playerHandler.setCarSelection(playerIdGamepad, CarType.FAST_CAR);

        playerHandler.registerPlayerObserver(testView.getjPanel());

        gameStateController.setGameMode(GameMode.MULTIPLAYER);
        startGame();
        //TODO REMOVE--------
    }
}

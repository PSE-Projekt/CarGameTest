package de.cargame.controller;

import de.cargame.controller.entity.GameMode;
import de.cargame.controller.entity.GameModelData;
import de.cargame.controller.input.Keyboard;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.handler.PlayerHandler;
import de.cargame.view.TestView;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Setter(AccessLevel.PRIVATE)
@Slf4j
public class GameController {


    private final GameStateController gameStateController = new GameStateController();
    private final InputController inputController = new InputController();
    private final PlayerHandler playerHandler = new PlayerHandler();
    private final GameObjectController gameObjectController = new GameObjectController(gameStateController, playerHandler);
    private final PlayerController playerController = new PlayerController(playerHandler);

    public GameController() {
        run();
    }

    private void run() {
        //createUI();

        initializePlayerKeyboard();
        gameStateController.setGameMode(GameMode.SINGLEPLAYER);
        TestView testView = new TestView(this, gameObjectController);
        startGame();

        long lastTime = System.nanoTime();
        while (true) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            lastTime = currentTime;

            gameObjectController.update(deltaTime);
            testView.render();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    public void startGame() {
        gameObjectController.startGame();
    }

    public GameModelData getModel() {
        List<GameObject> allGameObjects = gameObjectController.getAllGameObjects();
        return new GameModelData(allGameObjects);
    }

    private String initializePlayerKeyboard() {
        Player player = new Player();
        Keyboard keyboardPlayer = new Keyboard(player.getId());
        keyboardPlayer.registerObserver(player);
        playerHandler.setPlayerKeyboard(player);

        //TODO REMOVE
        player.setPlaying(true);
        //TODO REMOVE

        return player.getId();
    }

    private String initializePlayerGamePad() {
        Player player = new Player();
        Keyboard keyboardPlayer = new Keyboard(player.getId());
        keyboardPlayer.registerObserver(player);
        playerHandler.setPlayerKeyboard(player);

        //TODO REMOVE
        player.setPlaying(true);
        //TODO REMOVE

        return player.getId();
    }
}

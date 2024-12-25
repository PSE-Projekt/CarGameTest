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
        GameModelData gameModelData = new GameModelData(allGameObjects);
        return gameModelData;
    }

    private String initializePlayerKeyboard() {
        Player player1 = new Player();
        Keyboard keyboardPlayer1 = new Keyboard(player1.getId());
        keyboardPlayer1.registerObserver(player1);
        playerHandler.setPlayerKeyboard(player1);


        //TODO REMOVE
        player1.setPlaying(true);
        //TODO REMOVE


        return player1.getId();
    }

}

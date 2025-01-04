package de.cargame.controller;

import de.cargame.controller.entity.GameModelData;
import de.cargame.controller.input.GamePad;
import de.cargame.controller.input.Keyboard;
import de.cargame.model.GameInstance;
import de.cargame.model.entity.gameobject.CarType;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.player.Player;
import de.cargame.model.handler.PlayerHandler;
import de.cargame.view.TestView;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class ApplicationController {


    private final GameStateController gameStateController = new GameStateController();
    private List<GameInstance> gameInstances = new CopyOnWriteArrayList<>();

    private Player playerKeyboard = initializePlayerKeyboard();
    private Player playerGamePad = initializePlayerGamePad();

    private TestView testView;

    public ApplicationController() {
        run();
    }

    private void run() {
        //createUI();
        testView = new TestView(this);
        startGame();
    }

    public void startGame() {
        GameInstance gameInstance = new GameInstance(gameStateController, this, playerKeyboard);
        gameInstances.add(gameInstance);
        gameInstance.run();
    }


    public void renderGameInstance(GameInstance gameInstance) {
        testView.render(gameInstance);
    }

    private Player initializePlayerKeyboard() {
        Player player = new Player();
        Keyboard keyboard = new Keyboard(player.getId());
        keyboard.registerObserver(player);
        player.setPlaying(true);
        player.setCarSelection(CarType.AGILE_CAR);

        return player;
    }

    private Player initializePlayerGamePad() {
        Player player = new Player();
        GamePad gamePad = new GamePad(player.getId());
        gamePad.registerObserver(player);
        player.setPlaying(true);

        return player;
    }

    public List<GameModelData> getModel() {

        List<GameModelData> gameModels = new ArrayList<>();
        for (GameInstance gameInstance : gameInstances) {
            String playerId = gameInstance.getPlayerId();
            List<GameObject> gameObjects = gameInstance.getAllGameObjects();
            gameModels.add(new GameModelData(playerId, gameObjects));
        }
        return gameModels;
    }
}

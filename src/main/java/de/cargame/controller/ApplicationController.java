package de.cargame.controller;

import de.cargame.controller.entity.GameModelData;
import de.cargame.controller.input.GamePad;
import de.cargame.controller.input.Keyboard;
import de.cargame.model.GameInstance;
import de.cargame.model.entity.gameobject.car.player.CarType;
import de.cargame.model.entity.player.Player;
import de.cargame.view.TestView;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class ApplicationController {


    private final GameStateController gameStateController = new GameStateController();
    private List<GameInstance> gameInstances = new CopyOnWriteArrayList<>();

    private InputController inputController = new InputController();

    private Player playerKeyboard = initializePlayerKeyboard();
    //private Player playerGamePad = initializePlayerGamePad();

    private TestView testView;

    public ApplicationController() {
        run();
    }

    private void run() {
        initUI();

        //TODO REMOVE
        dummyChangesToMakeThisShitWork();
        //TODO REMOVE

        startGame();
    }



    public void startGame() {
        GameInstance gameInstance = new GameInstance(gameStateController, this, playerKeyboard);
        gameInstances.add(gameInstance);
        gameInstance.registerUI(testView.getjPanel());
        gameInstance.run();
    }

    public void initUI() {
        testView = new TestView();
    }


    public void renderGameInstance(GameInstance gameInstance) {
        testView.render(gameInstance);
    }

    private Player initializePlayerKeyboard() {
        Player player = new Player();
        inputController.initKeyboard(player.getId());
        inputController.registerKeyboardObserver(player);

        return player;
    }

    private Player initializePlayerGamePad() {
        Player player = new Player();
        inputController.initGamepad(player.getId());
        inputController.registerGamePadObserver(player);

        return player;
    }

    public List<GameModelData> getModel() {

        List<GameModelData> gameModels = new ArrayList<>();
        for (GameInstance gameInstance : gameInstances) {
            GameModelData gameModelData = gameInstance.getGameModelData();
            gameModels.add(gameModelData);
        }
        return gameModels;
    }


    //TODO REMOVE
    private void dummyChangesToMakeThisShitWork() {
        playerKeyboard.setPlaying(true);
        playerKeyboard.setCarSelection(CarType.FAST_CAR);
    }
}

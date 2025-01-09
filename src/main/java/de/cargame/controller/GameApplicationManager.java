package de.cargame.controller;

import de.cargame.controller.api.GameInstanceAPI;
import de.cargame.controller.api.GameStateAPI;
import de.cargame.controller.api.PlayerAPI;
import de.cargame.controller.entity.GameMode;
import de.cargame.model.GameInstance;
import de.cargame.model.entity.gameobject.car.player.CarType;
import de.cargame.model.entity.player.PlayerObserver;
import de.cargame.view.TestView;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class GameApplicationManager {


    private final GameStateAPI gameStateAPI = new GameStateController();
    private final PlayerAPI playerAPI = new PlayerController();
    private final GameInstanceAPI gameInstanceAPI = new GameInstanceController(this, gameStateAPI, playerAPI);

    private TestView testView;

    public GameApplicationManager() {
        run();
    }

    private void run() {
        initUI();

        //TODO REMOVE
        dummyChangesToMakeThisShitWork();
        //TODO REMOVE

        startGame();
    }


    public PlayerObserver getPlayerObserver() {
        return testView.getJPanel();
    }


    public void startGame() {
        GameMode gameMode = gameStateAPI.getGameMode();
        if (gameMode == GameMode.SINGLEPLAYER) {
            startGameSingleplayerMode();
        } else if (gameMode == GameMode.MULTIPLAYER) {
            startGameMultiplayerMode();
        }
    }

    public void initUI() {
        testView = new TestView(gameStateAPI);
    }


    public void renderGameInstance(GameInstance gameInstance) {
        testView.render(gameInstance);
    }


    private void startGameSingleplayerMode() {
        Optional<String> keyboardPlayerIdOptional = playerAPI.getKeyboardPlayerId();
        Optional<String> gamepadPlayerIdOptional = playerAPI.getGamepadPlayerId();
        if (keyboardPlayerIdOptional.isPresent() && playerAPI.isPlaying(keyboardPlayerIdOptional.get())) {
            gameInstanceAPI.startGamePlayerKeyboard();
        } else if (gamepadPlayerIdOptional.isPresent() && playerAPI.isPlaying(gamepadPlayerIdOptional.get())) {
            gameInstanceAPI.startGamePlayerGamePad();
        }
    }

    private void startGameMultiplayerMode() {
        Optional<String> keyboardPlayerIdOptional = playerAPI.getKeyboardPlayerId();
        Optional<String> gamepadPlayerIdOptional = playerAPI.getGamepadPlayerId();
        if (keyboardPlayerIdOptional.isPresent() && playerAPI.isPlaying(keyboardPlayerIdOptional.get())) {
            gameInstanceAPI.startGamePlayerKeyboard();
        }
        if (gamepadPlayerIdOptional.isPresent() && playerAPI.isPlaying(gamepadPlayerIdOptional.get())) {
            gameInstanceAPI.startGamePlayerGamePad();
        }
    }


    //TODO REMOVE
    private void dummyChangesToMakeThisShitWork() {
        gameStateAPI.setGameMode(GameMode.SINGLEPLAYER);
        playerAPI.createPlayerKeyboard();
        Optional<String> playerIdOptional = playerAPI.getKeyboardPlayerId();
        if (playerIdOptional.isPresent()) {
            String playerId = playerIdOptional.get();
            playerAPI.setPlaying(playerId, true);
            playerAPI.setCarSelection(playerId, CarType.FAST_CAR);
        }
    }
}

package de.cargame.controller;

import de.cargame.controller.entity.GameModelData;
import de.cargame.controller.input.Keyboard;
import de.cargame.model.entity.Coordinate;
import de.cargame.model.entity.Player;
import de.cargame.model.entity.gameobject.GameObject;
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
    private final PlayerController playerController = new PlayerController();
    private final GameObjectController gameObjectController = new GameObjectController();

    public GameController() {
        run();
    }

    private void run() {
        //createUI();

        Player playerKeyboard = new Player();
        Keyboard keyboard = new Keyboard();
        keyboard.registerObserver(playerKeyboard);

        gameObjectController.startGame();


        TestView testView = new TestView(this, gameObjectController);

        long lastTime = System.nanoTime();
        while (true) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            lastTime = currentTime;


            gameObjectController.update(deltaTime);
            testView.render();

            try {
                Thread.sleep(16); // ~60 FPS 16
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    public GameModelData getModel(){
        List<GameObject> allGameObjects = gameObjectController.getAllGameObjects();
        GameModelData gameModelData = new GameModelData(allGameObjects);
        return gameModelData;
    }
}

package de.cargame.controller;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter(AccessLevel.PRIVATE)
@Slf4j
public class GameController {


    private GameStateController gameStateController;
    private InputController inputController;

    private PlayerController playerController;

    private GameObjectController gameObjectController;

    public GameController() {
        setGameStateController(new GameStateController());
        setInputController(new InputController());
        setPlayerController(new PlayerController());
        setGameObjectController(new GameObjectController());

        run();
    }


    private void run() {
        //createUI();
        long lastTime = System.nanoTime();

        while (true) {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            lastTime = currentTime;

            // Autos aktualisieren

            gameObjectController.update(deltaTime);

            // Rendering und andere Updates

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    private void tick() {

    }
}

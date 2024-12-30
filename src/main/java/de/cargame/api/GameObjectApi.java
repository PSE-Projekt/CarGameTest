package de.cargame.api;

import de.cargame.controller.GameStateController;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.handler.GameObjectHandler;
import de.cargame.model.handler.PlayerHandler;

import java.util.List;

public interface GameObjectApi {



    List<GameObject> getAllGameObjects();

    void startGame();

    void stopGame();

    public void update(double deltaTime);
}

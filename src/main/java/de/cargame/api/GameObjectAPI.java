package de.cargame.api;

import de.cargame.model.entity.gameobject.GameObject;

import java.util.List;

public interface GameObjectAPI {


    List<GameObject> getAllGameObjects();

    void startGame();

    void stopGame();

    void update(double deltaTime);
}

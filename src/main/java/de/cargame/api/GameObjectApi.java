package de.cargame.api;

import de.cargame.model.entity.gameobject.GameObject;

import java.util.List;

public interface GameObjectApi {


    List<GameObject> getAllGameObjects();

    void startGame();

    void stopGame();

    public void update(double deltaTime);
}

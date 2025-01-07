package de.cargame.model.entity.gameobject.interfaces;


import de.cargame.controller.input.UserInputBundle;

public interface UserInputObserver {

    void update(UserInputBundle userInputBundle);
}

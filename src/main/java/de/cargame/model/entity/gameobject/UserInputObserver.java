package de.cargame.model.entity.gameobject;


import de.cargame.controller.input.UserInputBundle;

public interface UserInputObserver {

    void update(UserInputBundle userInputBundle);
}

package de.cargame.model.entity.gameobject;


import de.cargame.controller.input.UserInput;

public interface UserInputObserver {

    void update(UserInput userInput);
}

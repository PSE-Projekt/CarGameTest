package de.cargame.controller.input;


import de.cargame.model.entity.gameobject.UserInputObserver;

public interface UserInputObservable {

    void registerObserver(UserInputObserver o);

    void removeObserver(UserInputObserver o);

    void notifyObservers(UserInput userInput);

}

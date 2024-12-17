package de.cargame.old.observer;

public interface UserInputObservable {
    void registerObserver(UserInputObserver o);
    void removeObserver(UserInputObserver o);
    void notifyObservers(UserInput userInput);
}
package de.cargame.controller.input;

import de.cargame.model.entity.UserInputObserver;

public class GamePad extends InputDevice {
    private boolean deviceConnected = false;

    public GamePad(String id) {
        super(id);
        initController();
    }

    private void initController() {
    }

    public void inputTriggerUp() {
        //todo
    }

    public void inputTriggerDown() {
        //todo
    }

    public void inputA() {
        //todo
    }

    @Override
    public void registerObserver(UserInputObserver o) {

    }

    @Override
    public void removeObserver(UserInputObserver o) {

    }

    @Override
    public void notifyObservers(UserInput userInput) {

    }
}

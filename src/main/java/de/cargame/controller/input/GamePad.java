package de.cargame.controller.input;

import de.cargame.model.entity.UserInputObserver;
import net.java.games.input.Controller;

public class GamePad extends InputDevice {

    private Controller controller;
    private boolean deviceConnected = false;

    public GamePad(Controller controller) {

        if (controller != null) {
            this.controller = controller;
            deviceConnected = true;
        }
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

package de.cargame.controller.input;

import de.cargame.model.entity.UserInputObserver;
import net.java.games.input.Controller;

public class GamePad extends InputDevice {
    private boolean deviceConnected = false;

    public GamePad(String id, Controller controller) {
        super(id);
        if(controller != null){
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

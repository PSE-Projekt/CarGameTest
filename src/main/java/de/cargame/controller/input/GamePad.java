package de.cargame.controller.input;

import de.cargame.model.entity.gameobject.interfaces.UserInputObserver;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GamePad extends InputDevice {

    private List<UserInputObserver> userInputObserverList = new ArrayList<>();
    private boolean deviceConnected = false;
    private Controller controller;

    public GamePad(String id) {
        super(id);
        initController();
    }

    public boolean initController() {

        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for (Controller controller : controllers) {
            if (controller.getType() == Controller.Type.GAMEPAD) {
                this.controller = controller;
                deviceConnected = true;
                break;
            }
        }
        if (!deviceConnected) {
            System.out.println("No Gamepad found");
            return false;
        }
        return true;
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
        userInputObserverList.add(o);
    }

    @Override
    public void removeObserver(UserInputObserver o) {
        userInputObserverList.remove(o);
    }

    @Override
    public void notifyObservers(UserInputBundle userInputBundle) {
        userInputObserverList.forEach(o -> o.update(userInputBundle));
    }
}

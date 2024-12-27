package de.cargame.controller;

import de.cargame.controller.input.GamePad;
import de.cargame.controller.input.Keyboard;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;

import java.util.Optional;

public class InputController {

    private Keyboard keyboard;
    private GamePad gamePad;

    public InputController() {
    }

    public Optional<Controller> pollGamePad() {
        Event event = new Event();

        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        if (controllers.length == 0) {
            System.out.println("No Controller found");
            return Optional.empty();
        }
        return Optional.of(controllers[0]);
    }
}

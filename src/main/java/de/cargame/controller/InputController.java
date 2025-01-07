package de.cargame.controller;

import de.cargame.controller.input.GamePad;
import de.cargame.controller.input.Keyboard;
import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.gameobject.interfaces.UserInputObserver;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;

import java.security.Key;
import java.util.Optional;

public class InputController {

    private Keyboard keyboard;
    private GamePad gamePad;

    public InputController(){

    }

    public void initKeyboard(String playerId){
        this.keyboard = new Keyboard(playerId);
    }

    public void initGamepad(String playerId){
        this.gamePad = new GamePad(playerId);
    }


    public void registerKeyboardObserver(UserInputObserver inputObserver) {
        this.keyboard.registerObserver(inputObserver);
    }

    public void registerGamePadObserver(UserInputObserver inputObserver) {
        this.gamePad.registerObserver(inputObserver);
    }

}

package de.cargame.controller;

import de.cargame.controller.input.GamePad;
import de.cargame.controller.input.Keyboard;
import de.cargame.model.entity.gameobject.interfaces.UserInputObserver;

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

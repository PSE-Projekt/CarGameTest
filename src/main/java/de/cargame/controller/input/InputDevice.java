package de.cargame.controller.input;

public abstract class InputDevice implements UserInputObservable {
    private String playerid;


    public InputDevice(String playerid) {
        this.playerid = playerid;
    }
}

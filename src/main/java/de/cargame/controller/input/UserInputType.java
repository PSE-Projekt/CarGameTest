package de.cargame.controller.input;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum UserInputType {
    UP(17),
    LEFT(30),
    DOWN(31),
    RIGHT(32),
    SELECT(13),
    FAST_FORWARD(57),
    NONE(-1);


    private int keyCode;


    private UserInputType(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public static Optional<UserInputType> getUserInputForKeyCode(int keyCode) {
        UserInputType[] values = values();
        for (UserInputType value : values) {
            if (value.getKeyCode() == keyCode) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}

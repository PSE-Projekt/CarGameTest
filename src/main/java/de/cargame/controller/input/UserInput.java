package de.cargame.controller.input;

import lombok.Getter;

import java.util.Objects;

@Getter
public class UserInput {

    private final UserInputType userInputType;
    private long time;

    public UserInput(final UserInputType userInputType) {
        setTime();
        this.userInputType = userInputType;
    }


    public boolean timeElapsed(double timeMillis) {
        return System.currentTimeMillis() - time >= timeMillis;
    }


    private void setTime() {
        this.time = System.currentTimeMillis();
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserInput userInput)) return false;
        return userInputType == userInput.userInputType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userInputType, time);
    }
}

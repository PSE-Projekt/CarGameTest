package de.cargame.old.observer;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.util.ArrayList;
import java.util.List;

public class Keyboard extends InputDevice {
    private List<UserInputObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(UserInputObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(UserInputObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(UserInput userInput) {
        for (UserInputObserver observer : observers) {
            observer.update(userInput);
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {

    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        int inputKey = e.getKeyCode();
        switch (inputKey) {
            case 17:
                notifyObservers(UserInput.UP);
                return;
            case 31:
                notifyObservers(UserInput.DOWN);
                return;
            case 57:
                notifyObservers(UserInput.FAST_FORWARD);
                return;
            default:
                return;
        }
    }
    public void nativeKeyReleased(NativeKeyEvent e) {

    }
}

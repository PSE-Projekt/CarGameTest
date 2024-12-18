package de.cargame.controller.input;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import de.cargame.model.entity.UserInputObserver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Keyboard extends InputDevice implements NativeKeyListener {

    private int newestInput;
    private Set<Integer> pressedKeys;
    private List<UserInputObserver> userInputObservers;

    public Keyboard() {
        pressedKeys = new HashSet<>();
        userInputObservers = new ArrayList<>();
    }

    public void inputW() {
        //todo
    }

    public void inputS() {
        //todo
    }

    public void inputSpace() {
        //todo
    }

    @Override
    public void registerObserver(UserInputObserver o) {
        userInputObservers.add(o);
    }

    @Override
    public void removeObserver(UserInputObserver o) {
        userInputObservers.remove(o);
    }


    @Override
    public void notifyObservers(UserInput userInput) {
    }

    public void nativeKeyTyped(NativeKeyEvent e) {


    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        int keyCode = e.getKeyCode();

        if (!pressedKeys.contains(keyCode)) {
            pressedKeys.add(keyCode);

        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {

    }
}

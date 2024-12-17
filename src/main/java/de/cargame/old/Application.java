package de.cargame.old;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import de.cargame.old.gui.SimpleGUI;
import de.cargame.old.observer.InputDevice;
import de.cargame.old.observer.Keyboard;
import de.cargame.old.observer.Player;

public class Application {

    public static void main(String[] args){


        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

           System.exit(1);
        }



        InputDevice keyboard = new Keyboard();
        GlobalScreen.addNativeKeyListener(keyboard);


        Player player1 = new Player();
        keyboard.registerObserver(player1);

        new SimpleGUI();

    }
}

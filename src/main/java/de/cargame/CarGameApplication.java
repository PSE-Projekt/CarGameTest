package de.cargame;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import de.cargame.controller.GameController;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Slf4j
public class CarGameApplication {

    private static GameController gameController;

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        gameController = new GameController();
    }

}

package de.cargame;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import de.cargame.controller.ApplicationController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarGameApplication {

    private static ApplicationController applicationController;

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        System.setProperty("sun.java2d.opengl", "true");
        applicationController = new ApplicationController();
    }

}

package de.cargame;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import de.cargame.controller.ApplicationController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarGameApplication {

    private static ApplicationController applicationController;

    public static void main(String[] args) {
        // Set java.library.path to include the extracted native libraries
        System.setProperty("java.library.path", "target/natives");

        // Force JVM to reload the library path
        try {
            System.setProperty("jna.library.path", "target/natives");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        System.setProperty("sun.java2d.opengl", "true");
        applicationController = new ApplicationController();
    }

}

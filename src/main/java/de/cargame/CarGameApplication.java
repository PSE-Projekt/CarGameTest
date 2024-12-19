package de.cargame;

import de.cargame.controller.GameController;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Slf4j
public class CarGameApplication {

    private static GameController gameController;

    public static void main(String[] args) {
        gameController = new GameController();
    }

}

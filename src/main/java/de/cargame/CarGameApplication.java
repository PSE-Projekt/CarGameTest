package de.cargame;

import de.cargame.controller.GameController;

public class CarGameApplication {

    private static GameController gameController;
    public static void main(String[] args){
        gameController = new GameController();
    }

}

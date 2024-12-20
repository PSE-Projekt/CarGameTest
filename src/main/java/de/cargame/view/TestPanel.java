package de.cargame.view;

import de.cargame.controller.GameController;
import de.cargame.controller.entity.GameModelData;
import de.cargame.model.entity.gameobject.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestPanel extends JPanel {

    private GameController gameController;
    public TestPanel(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GameModelData model = gameController.getModel();
        List<GameObject> gameObjects = model.getGameObjects();
        for (GameObject gameObject : gameObjects) {
            Shape bounds = gameObject.getBound();
            g2d.fill(bounds);
        }
    }
}

package de.cargame.view;

import de.cargame.controller.GameController;
import de.cargame.controller.entity.GameModelData;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.AICar;
import de.cargame.model.entity.gameobject.car.PlayerCar;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestPanel extends JPanel {

    private GameController gameController;

    public TestPanel(GameController gameController) {
        this.gameController = gameController;
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GameModelData model = gameController.getModel();
        List<GameObject> gameObjects = model.getGameObjects();
        for (GameObject gameObject : gameObjects) {
            Shape bounds = gameObject.getBound();
            if (gameObject instanceof AICar) {
                g2d.setColor(Color.RED);
            } else if (gameObject instanceof Obstacle) {
                g2d.setColor(Color.BLUE);
            } else if (gameObject instanceof Reward) {
                g2d.setColor(Color.YELLOW);
            } else if (gameObject instanceof Building) {
                g2d.setColor(Color.BLACK);
            } else if (gameObject instanceof PlayerCar) {
                g2d.setColor(Color.GREEN);
            } else if (gameObject instanceof RoadMark) {
                g2d.setColor(Color.GRAY);
            }
            g2d.fill(bounds);
        }
    }

    private void paintComponents(List<GameObject> gameObjects) {

    }
}

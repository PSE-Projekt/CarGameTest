package de.cargame.view;

import de.cargame.config.GameConfig;
import de.cargame.controller.GameController;
import de.cargame.controller.GameObjectController;

import javax.swing.*;
import java.awt.*;

public class TestView extends JFrame {

    private GameController gameController;
    private GameObjectController gameObjectController;

    private JPanel jPanel;
    private Graphics2D g2d;

    public TestView(GameController gameController, GameObjectController gameObjectController) {
        this.gameController = gameController;
        this.gameObjectController = gameObjectController;


        setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        jPanel = new TestPanel(gameController);
        add(jPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void render() {
        jPanel.repaint();
    }

}

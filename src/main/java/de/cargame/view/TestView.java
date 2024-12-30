package de.cargame.view;

import de.cargame.config.GameConfig;
import de.cargame.controller.GameController;
import de.cargame.model.entity.player.PlayerObserver;

import javax.swing.*;
import java.awt.*;

public class TestView extends JFrame {


    private TestPanel jPanel;

    public TestView(GameController gameController) {


        setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        jPanel = new TestPanel(gameController);
        jPanel.setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        getContentPane().add(jPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void render() {
        jPanel.repaint();
    }

    public PlayerObserver getjPanel() {
        return jPanel;
    }
}

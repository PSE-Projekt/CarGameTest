package de.cargame.view;

import de.cargame.config.GameConfig;
import de.cargame.controller.ApplicationController;
import de.cargame.model.GameInstance;
import de.cargame.model.entity.player.PlayerObserver;

import javax.swing.*;
import java.awt.*;

public class TestView extends JFrame {


    private TestPanel jPanel;

    public TestView(ApplicationController applicationController) {


        setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        jPanel = new TestPanel(applicationController);
        jPanel.setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        getContentPane().add(jPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void render(GameInstance gameInstance) {
        jPanel.repaint();
    }

    public PlayerObserver getjPanel() {
        return jPanel;
    }
}

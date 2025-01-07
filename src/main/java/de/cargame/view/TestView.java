package de.cargame.view;

import de.cargame.config.GameConfig;
import de.cargame.controller.GameStateController;
import de.cargame.controller.api.GameStateAPI;
import de.cargame.controller.entity.GameModelData;
import de.cargame.controller.entity.GameState;
import de.cargame.model.GameInstance;
import de.cargame.model.entity.player.PlayerObserver;

import javax.swing.*;
import java.awt.*;

public class TestView extends JFrame {


    private final GamePanel foregroundPanel = new ForegroundPanel();
    private final GamePanel backgroundPanel = new BackgroundPanel();

    private final JLayeredPane layeredPane = new JLayeredPane();

    GameStateController gameStateController;

    public TestView(GameStateAPI gameStateController) {
        // JFrame-Größe festlegen


        this.gameStateController = (GameStateController) gameStateController;
        setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        setMinimumSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));

        // LayeredPane konfigurieren
        layeredPane.setLayout(null);

        // Panels konfigurieren
        backgroundPanel.setBounds(0, 0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        foregroundPanel.setBounds(0, 0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        backgroundPanel.setVisible(true);
        foregroundPanel.setVisible(true);

        // Panels in LayeredPane einfügen
        layeredPane.add(backgroundPanel, 0); // Hintergrundebene
        layeredPane.add(foregroundPanel, 1); // Vordergrundebene

        // LayeredPane hinzufügen
        add(layeredPane, BorderLayout.CENTER);

        // JFrame konfigurieren
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public PlayerObserver getJPanel() {
        return (PlayerObserver) foregroundPanel;
    }

    public void render(GameInstance gameInstance) {
        GameModelData gameModelData = gameInstance.getGameModelData();
        backgroundPanel.render(gameModelData);
        foregroundPanel.render(gameModelData);
    }

}

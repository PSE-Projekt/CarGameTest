package de.cargame.view;

import de.cargame.model.entity.gameobject.GameObject;
import de.cargame.model.entity.player.PlayerObserver;
import de.cargame.model.entity.player.PlayerUpdate;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Predicate;

public class ForegroundPanel extends GamePanel implements PlayerObserver {

    private JLabel liveLabel;
    private JLabel scoreLabel;


    public ForegroundPanel() {
        liveLabel = new JLabel("", SwingConstants.LEFT);
        scoreLabel = new JLabel("", SwingConstants.RIGHT);
        setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(AlphaComposite.Src);
        g2d.setColor(new Color(255, 255, 255, 255)); // Vollständig transparent
        g2d.setComposite(AlphaComposite.SrcOver); // Zurück zu normalem Modus
        g2d.fillRect(0, 0, getWidth(), getHeight());
        if (gameModelData == null) return;
        List<GameObject> gameObjects = gameModelData.getGameObjects();

        List<GameObject> foregroundElements = gameObjects
                .stream()
                .filter(Predicate.not(GameObject::isCollidable))
                .toList();
        paintGameObjects(foregroundElements, g2d);
    }


    @Override
    public void update(PlayerUpdate playerUpdate) {
        repaint();
        liveLabel.setText("Live : " + playerUpdate.getLives());
        scoreLabel.setText("Score: " + playerUpdate.getScoreValue());
    }

}

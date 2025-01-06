package de.cargame.view;

import de.cargame.model.entity.gameobject.GameObject;

import java.awt.*;
import java.util.List;

public class BackgroundPanel extends GamePanel {


    public BackgroundPanel() {
        setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(AlphaComposite.Src);
        g2d.setColor(new Color(255, 255, 255, 255)); // Vollständig transparent

        g2d.setComposite(AlphaComposite.SrcOver); // Zurück zu normalem Modus

        if (gameModelData == null) return;
        List<GameObject> gameObjects = gameModelData.getGameObjects();
        List<GameObject> backgroundElements = gameObjects
                .stream()
                .filter(GameObject::isCollidable)
                .toList();
        paintGameObjects(backgroundElements, g2d);

    }

}

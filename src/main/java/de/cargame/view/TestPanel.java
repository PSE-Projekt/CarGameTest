package de.cargame.view;

import de.cargame.controller.ApplicationController;
import de.cargame.controller.entity.GameModelData;
import de.cargame.model.entity.gameobject.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Predicate;

public class TestPanel extends JPanel {

    private ApplicationController applicationController;


    private JPanel backgroundPanel = new BackgroundPanel();
    private JPanel foregroundPanel = new ForegroundPanel();

    private JLayeredPane layeredPane = new JLayeredPane();


    public TestPanel(ApplicationController applicationController) {


        layeredPane.add(backgroundPanel, Integer.valueOf(0)); // Hintergrund
        layeredPane.add(foregroundPanel, Integer.valueOf(1)); // Vordergrund

        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);


        setDoubleBuffered(true);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;


        g2d.setComposite(AlphaComposite.Src);
        g2d.setColor(new Color(255, 255, 255, 255)); // Vollständig transparent
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setComposite(AlphaComposite.SrcOver); // Zurück zu normalem Modus


        List<GameModelData> model = applicationController.getModel();


        for (GameModelData gameModelData : model) {
            List<GameObject> gameObjects = gameModelData.getGameObjects();

            List<GameObject> backgroundElements = gameObjects.stream().filter(GameObject::isCollidable).toList();
            List<GameObject> foregroundElements = gameObjects.stream().filter(Predicate.not(GameObject::isCollidable)).toList();

        }
    }


}

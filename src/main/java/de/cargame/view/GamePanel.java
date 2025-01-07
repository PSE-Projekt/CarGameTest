package de.cargame.view;

import de.cargame.controller.entity.GameModelData;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.ai.AICar;
import de.cargame.model.entity.gameobject.car.player.AgileCar;
import de.cargame.model.entity.gameobject.car.player.PlayerCar;
import de.cargame.view.image.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class GamePanel extends JPanel {


    protected GameModelData gameModelData;


    GameImages agileCarImages = new AgileCarImages();
    GameImages fastCarImages = new FastCarImages();
    GameImages buildingImage = new BuildingImages();
    GameImages kamikazeCarImage = new KamikazeCarImages();
    GameImages liveImage = new LifeImages();
    GameImages obstacleImage = new ObstacleImages();


    public GamePanel() {
        setDoubleBuffered(true);
        setOpaque(false);
        setVisible(true);
    }


    public void render(GameModelData gameModelData) {
        this.gameModelData = gameModelData;
        repaint();
    }

    protected void paintGameObjects(List<GameObject> gameObjects, Graphics2D g2d) {

        for (GameObject gameObject : gameObjects) {

            int x = (int) gameObject.getX();
            int y = (int) gameObject.getY();
            int width = gameObject.getWidth();
            int height = gameObject.getHeight();

            Shape bounds = gameObject.getBound();
            String gameObjectId = gameObject.getId();

            if (gameObject instanceof AICar) {
                drawImage(g2d, kamikazeCarImage.getRandomImage(gameObjectId), x, y, width, height);
            } else if (gameObject instanceof Obstacle) {
                drawImage(g2d, obstacleImage.getRandomImage(gameObjectId), x, y, width, height);
            } else if (gameObject instanceof Reward reward) {
                if (!reward.isCollected()) {
                    drawImage(g2d, liveImage.getRandomImage(gameObjectId), x, y, width, height);
                }
            } else if (gameObject instanceof Building) {
                drawImage(g2d, buildingImage.getRandomImage(gameObjectId), x, y, width, height);

            } else if (gameObject instanceof RoadMark) {
                g2d.setColor(Color.GRAY);
            }
            g2d.fill(bounds);
            g2d.setColor(new Color(0f, 0f, 0f, 0f));
            if (gameObject instanceof PlayerCar playerCar) {
                if (playerCar instanceof AgileCar) {
                    drawImage(g2d, agileCarImages.getRandomImage(gameObjectId), x, y, width, height);
                } else {
                    drawImage(g2d, fastCarImages.getRandomImage(gameObjectId), x, y, width, height);
                }
            }

        }
    }

    private void drawImage(Graphics2D graphics2D, BufferedImage image, int x, int y, int width, int height) {
        graphics2D.drawImage(image, x, y, width, height, null);
    }
}

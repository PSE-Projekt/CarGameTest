package de.cargame.view;

import de.cargame.controller.ApplicationController;
import de.cargame.controller.entity.GameModelData;
import de.cargame.model.entity.gameobject.*;
import de.cargame.model.entity.gameobject.car.AICar;
import de.cargame.model.entity.gameobject.car.AgileCar;
import de.cargame.model.entity.gameobject.car.PlayerCar;
import de.cargame.model.entity.player.PlayerObserver;
import de.cargame.model.entity.player.PlayerUpdate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.List;

public class TestPanel extends JPanel implements PlayerObserver {

    private ApplicationController applicationController;
    private JLabel liveLabel;
    private JLabel scoreLabel;


    BufferedImage agileCarImage = preloadImage("src/main/resources/sprites/agile_car.png");
    BufferedImage fastCarImage = preloadImage("src/main/resources/sprites/fast_car.png");
    BufferedImage kamikazeCarImage = preloadImage("src/main/resources/sprites/kamikaze_car.png");
    BufferedImage buildingImage = preloadImage("src/main/resources/sprites/building.png");
    BufferedImage obstacleImage = preloadImage("src/main/resources/sprites/obstacle.png");
    BufferedImage liveImage = preloadImage("src/main/resources/sprites/life.png");

    public TestPanel(ApplicationController applicationController) {
        this.applicationController = applicationController;
        liveLabel = new JLabel("", SwingConstants.LEFT);
        scoreLabel = new JLabel("", SwingConstants.RIGHT);

        add(liveLabel);
        add(scoreLabel);

        setDoubleBuffered(true);
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

            for (GameObject gameObject : gameObjects) {

                int x = (int) gameObject.getX();
                int y = (int) gameObject.getY();
                int width = gameObject.getWidth();
                int height = gameObject.getHeight();

                Shape bounds = gameObject.getBound();
                if (gameObject instanceof AICar) {
                    drawImage(g2d, kamikazeCarImage, x, y, width, height);
                } else if (gameObject instanceof Obstacle) {
                    drawImage(g2d, obstacleImage, x, y, width, height);
                } else if (gameObject instanceof Reward reward) {
                    if(!reward.isCollected()){
                        drawImage(g2d, liveImage, x, y, width, height);
                    }
                } else if (gameObject instanceof Building) {
                    drawImage(g2d, buildingImage, x, y, width, height);

                } else if (gameObject instanceof RoadMark) {
                    g2d.setColor(Color.GRAY);
                }
                g2d.fill(bounds);
                g2d.setColor(new Color(0f,0f,0f,0f ));
                if (gameObject instanceof PlayerCar playerCar) {
                    if (playerCar instanceof AgileCar) {
                        drawImage(g2d, agileCarImage, x, y, width, height);
                    } else {
                        drawImage(g2d, fastCarImage, x, y, width, height);
                    }
                }

            }
        }
    }

    private void drawImage(Graphics2D graphics2D, BufferedImage image, int x, int y, int width, int height) {
        graphics2D.drawImage(image, x, y, width, height, null);
    }

    private BufferedImage preloadImage(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(PlayerUpdate playerUpdate) {
        repaint();
        liveLabel.setText("Live : " + playerUpdate.getLives());
        scoreLabel.setText("Score: " + playerUpdate.getScoreValue());
    }
}

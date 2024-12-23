package de.cargame.config;

public abstract class GameConfig {

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;

    public static final int MAX_LIVES = 3;

    public static final int GAME_SPEED = 100;


    public static final int OBSTACLE_SPAWN_RATE = 5;
    public static final int KAMIKAZE_CAR_SPAWN_RATE = 2;
    public static final int GAME_SPEED_FAST_FORWARD = 3;

    public static final int FAST_CAR_WIDTH = 150;
    public static final int FAST_CAR_HEIGHT = 75;
    public static final int FAST_CAR_SPEED = 300;
    public static final double FAST_CAR_INERTIA = 0.5;
    public static final int AGILE_CAR_WIDTH = 200;
    public static final int AGILE_CAR_HEIGHT = 100;
    public static final int AGILE_CAR_SPEED = 150;
    public static final double AGILE_CAR_INERTIA = 0.1;


    public static final int AI_CAR_WIDTH = 150;
    public static final int AI_CAR_HEIGHT = 70;

    public static final int AI_CAR_SPEED = 2;


    public static final int BUILDING_WIDTH = 50;
    public static final int BUILDING_HEIGHT = 50;

    /**
     * Pixelcount how wide the spawn area for buildings is
     */
    public static final int BUILDING_SPAWN_WIDTH = 10;

    public static final int ROAD_WIDTH = SCREEN_WIDTH;
    public static final int ROAD_HEIGHT = (int) (SCREEN_HEIGHT / 2.5);

    public static final int OBSTACLE_WIDTH = 30;
    public static final int OBSTACLE_HEIGHT = 30;

    public static final int REWARD_WIDTH = 20;
    public static final int REWARD_HEIGHT = 20;

}
package de.cargame.model.handler.entity;

import de.cargame.model.entity.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnAreaList {
    private List<SpawnArea> spawnAreas;

    public SpawnAreaList() {
        this.spawnAreas = new ArrayList<>();
    }

    public void add(SpawnArea spawnArea) {
        spawnAreas.add(spawnArea);
    }


    /**
     * Generates one random coordinate for each SpawnArea present.
     *
     * @return List of random coordinates from each SpawnArea present.
     */
    public List<Coordinate> getRandomCoordinateOfEach() {
        List<Coordinate> coordinates = new ArrayList<>();
        for (SpawnArea spawnArea : spawnAreas) {
            coordinates.add(spawnArea.getRandomCoordinateInArea());
        }
        return coordinates;
    }


    /**
     * Generates one random coordinate from one random SpawnArea present.
     *
     * @return One random coordinate from one random SpawnArea which is present.
     */
    public Coordinate getRandomCoordinate() {
        Random r = new Random();
        SpawnArea spawnArea = spawnAreas.get(new Random().nextInt(spawnAreas.size()));
        return spawnArea.getRandomCoordinateInArea();
    }


}

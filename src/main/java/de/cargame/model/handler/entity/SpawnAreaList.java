package de.cargame.model.handler.entity;

import de.cargame.model.entity.gameobject.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnAreaList {
    private final List<SpawnArea> spawnAreas;

    public SpawnAreaList() {
        this.spawnAreas = new ArrayList<>();
    }

    public void add(SpawnArea spawnArea) {
        spawnAreas.add(spawnArea);
    }

    public void addAll(List<SpawnArea> spawnAreas) {
        this.spawnAreas.addAll(spawnAreas);
    }

    public void remove(SpawnArea spawnArea) {
        spawnAreas.remove(spawnArea);
    }

    public SpawnArea getRandomSpawnArea() {
        return spawnAreas.get(new Random().nextInt(spawnAreas.size()));
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
        SpawnArea spawnArea = spawnAreas.get(new Random().nextInt(spawnAreas.size()));
        return getRandomCoordinate(spawnArea);
    }

    public Coordinate getRandomCoordinate(SpawnArea spawnArea) {
        return spawnArea.getRandomCoordinateInArea();
    }


}

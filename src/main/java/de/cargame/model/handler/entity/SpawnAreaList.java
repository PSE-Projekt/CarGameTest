package de.cargame.model.handler.entity;

import de.cargame.model.entity.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class SpawnAreaList {
    private List<SpawnArea> spawnAreas;

    public SpawnAreaList() {
        this.spawnAreas = new ArrayList<>();
    }

    public void add(SpawnArea spawnArea){
        spawnAreas.add(spawnArea);
    }


    /**
     * Generates one random coordinate for each SpawnArea present.
     * @return List of coordinates
     */
    public List<Coordinate> getRandomCoordinateOfEach(){
        List<Coordinate> coordinates = new ArrayList<>();
        for (SpawnArea spawnArea : spawnAreas) {
            coordinates.add(spawnArea.getRandomCoordinateInArea());
        }
        return coordinates;
    }
}

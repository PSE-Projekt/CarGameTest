package de.cargame.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coordinate {
    private int x;
    private int y;


    public void addX(int xAmount) {
        x += xAmount;
    }
    public void addY(int yAmount) {
        y += yAmount;
    }

}

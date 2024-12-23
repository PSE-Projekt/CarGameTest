package de.cargame.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coordinate {
    private double x;
    private double y;


    public void addX(double xAmount) {
        x += xAmount;
    }

    public void addY(double yAmount) {
        y += yAmount;
    }

    @Override
    public String toString(){
        return "("+getX()+","+getY()+")";
    }

}

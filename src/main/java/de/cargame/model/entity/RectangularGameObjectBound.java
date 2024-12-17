package de.cargame.model.entity;

import lombok.AllArgsConstructor;

import java.awt.*;

public class RectangularGameObjectBound extends GameObjectBound {
    private int height;
    private int width;


    public RectangularGameObjectBound(int x, int y, int width, int height) {
        super(x, y);
        this.width=width;
        this.height = height;
    }


    @Override
    public Shape getBound() {
        int x = coordinate.getX();
        int y = coordinate.getY();

        return new Rectangle(x, y, width, height);
    }
}

package de.cargame.model.entity;

import java.awt.*;

public class RectangularGameObjectBound extends GameObjectBound {
private Dimension dimension;


    public RectangularGameObjectBound(int x, int y, int width, int height) {
        super(x, y);
        dimension = new Dimension(width, height);
        coordinate = new Coordinate(x, y);
    }


    @Override
    public void moveBy(int xAmount, int yAmount) {
        super.moveBy(xAmount, yAmount);
    }

    @Override
    public Shape getBound() {
        int x = coordinate.getX();
        int y = coordinate.getY();
        int height = dimension.getHeight();
        int width = dimension.getWidth();

        return new Rectangle(x, y, width, height);
    }
}

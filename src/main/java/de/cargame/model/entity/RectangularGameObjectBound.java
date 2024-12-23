package de.cargame.model.entity;

import java.awt.*;

public class RectangularGameObjectBound extends GameObjectBound {
    private Dimension dimension;


    public RectangularGameObjectBound(double x, double y, int width, int height) {
        super(x, y);
        dimension = new Dimension(width, height);
        coordinate = new Coordinate(x, y);
    }


    @Override
    public void moveBy(double xAmount, double yAmount) {
        super.moveBy(xAmount, yAmount);
    }

    @Override
    public Shape getBound() {
        double x = coordinate.getX();
        double y = coordinate.getY();
        int height = dimension.getHeight();
        int width = dimension.getWidth();

        return new Rectangle((int) x, (int) y, width, height);
    }
}

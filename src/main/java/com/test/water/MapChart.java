package com.test.water;

import com.test.water.landscape.FloodRow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Set;

public class MapChart {
    private final int rectangleSize;

    public MapChart(int rectangleSize) {
        this.rectangleSize = rectangleSize;
    }

    public BufferedImage processData(final int[] a, final Set<FloodRow> floodRowSet, final int max) {

        BufferedImage bufferedImage = new BufferedImage(a.length * rectangleSize + 1, max * rectangleSize + 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();

        g.setColor(Color.BLACK);
        g.drawLine(0, max * rectangleSize, a.length * rectangleSize, max * rectangleSize);
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j <= a[i]; j++) {
                g.setColor(Color.BLACK);
                g.drawRect(i * rectangleSize, (max - j) * rectangleSize, rectangleSize, rectangleSize);
                g.setColor(Color.YELLOW);
                g.fillRect(i * rectangleSize + 1, (max - j) * rectangleSize + 1, rectangleSize - 1, rectangleSize - 1);
            }
        }

        floodRowSet.forEach(item ->
                drawWater(item.getX(), item.getY(), item.getSize(), max, g)
        );

        g.dispose();
        return bufferedImage;
    }

    public void drawWater(int x, int y, int size, int max, Graphics2D g) {
        for (int i = x; i > x - size; i--) {
            g.setColor(Color.BLACK);
            g.drawRect(i * rectangleSize, (max - y) * rectangleSize, rectangleSize, rectangleSize);
            g.setColor(Color.BLUE);
            g.fillRect(i * rectangleSize + 1, (max - y) * rectangleSize + 1, rectangleSize - 1, rectangleSize - 1);
        }
    }

}

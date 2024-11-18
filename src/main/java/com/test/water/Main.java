package com.test.water;

import com.test.water.landscape.Landscape;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;

public class Main {
    public final static int DISABLE_PICTURE_SIZE = 500;
    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        int[] data = {5, 2, 3, 4, 5, 4, 0, 3, 1};

        try {
            Landscape land = new Landscape();
            long sum = land.calcAmount(data);

            log.info("Water count is equal: " + sum);

            if (land.getMaxHeight() <= DISABLE_PICTURE_SIZE) {
                MapChart chart = new MapChart(12);
                BufferedImage image = chart.processData(data, land.getFloodRows(), land.getMaxHeight());
                Utils.showSavedImage(image);
            }
        } catch (Exception e) {
            log.error("Can not calculate water: " + e.getMessage(), e);
        }
    }
}

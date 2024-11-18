package com.test.water;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class Utils {
    private static Logger log = LoggerFactory.getLogger(Utils.class);

    public static void showSavedImage(BufferedImage image) throws IOException {
        File file = File.createTempFile("WaterCounter_", ".png");
        file.deleteOnExit();
        try (OutputStream os = Files.newOutputStream(file.toPath())) {
            ImageIO.write(image, "png", os);
            Desktop.getDesktop().open(file);
        } catch (Exception e) {
            log.error("Can not create image file: " + e.getMessage(), e);
        }
    }

}

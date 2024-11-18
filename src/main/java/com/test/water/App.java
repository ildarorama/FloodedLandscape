package com.test.water;

import com.test.water.landscape.Landscape;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.util.Random;

public class App extends Application {
    public final static int RECTANGLE_SIZE = 12;
    public final static int POSITION_SIZE = 80;
    public final static int HEIGHT_SIZE = 40;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Random rnd = new Random(System.currentTimeMillis());
        final Landscape landscape = new Landscape();
        final MapChart chart = new MapChart(RECTANGLE_SIZE);

        Stage stage = new Stage();
        BorderPane bp = new BorderPane();
        ImageView view = new ImageView();
        bp.setCenter(new ScrollPane(view));

        Label countLabel = new Label();
        countLabel.setText("Size: 0");
        bp.setTop(new VBox(countLabel));

        Button refreshButton = new Button("Click to refresh");
        refreshButton.setOnAction(e -> {
            int[] data = rnd.ints(POSITION_SIZE, 0, HEIGHT_SIZE).toArray();
            long count = landscape.calcAmount(data);
            BufferedImage image = chart.processData(data, landscape.getFloodRows(), landscape.getMaxHeight());
            Platform.runLater(() -> {
                view.setImage(SwingFXUtils.toFXImage(image, null));
                countLabel.setText("Size: " + count);
            });
        });

        bp.setPadding(new Insets(10, 10, 10, 10));
        HBox box = new HBox(refreshButton);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10, 10, 10, 10));
        bp.setBottom(box);

        Scene scene = new Scene(bp, 1024, 768);
        stage.setScene(scene);
        refreshButton.fire();
        stage.showAndWait();
    }
}

package com.silverhetch.vesta.jfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;

public class VestaApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        StackPane root = new StackPane();
        root.setOnDragOver(dragEvent -> {
            if (dragEvent.getDragboard().hasString()) {
                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            dragEvent.consume();
        });
        root.setOnDragDropped(dragEvent -> {
            new Thread(() -> {
                try {
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            dragEvent.setDropCompleted(true);
            dragEvent.consume();
        });
        root.setBackground(new Background(new BackgroundFill(Paint.valueOf("#000"), null, null)));

        Scene scene = new Scene(root, 640, 480);

        stage.setScene(scene);
        stage.show();
    }
}

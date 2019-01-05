package com.silverhetch.vesta.jfx;

import com.silverhetch.clotho.log.BeautyLog;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;

public class VestaApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(rootView(), 640, 480);
        handlingClipBoard(scene);
        stage.setScene(scene);
        stage.show();
    }

    private Pane rootView() {
        StackPane root = new StackPane();
        root.setOnDragOver(dragEvent -> {
            if (dragEvent.getDragboard().hasString()
                    || dragEvent.getDragboard().hasFiles()
                    || dragEvent.getDragboard().hasUrl()) {
                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            dragEvent.consume();
        });
        root.setOnDragDropped(dragEvent -> {
            inputContent(dragEvent.getDragboard());
            dragEvent.setDropCompleted(true);
            dragEvent.consume();
        });
        root.setBackground(new Background(new BackgroundFill(Paint.valueOf("#000"), null, null)));
        return root;
    }

    private void handlingClipBoard(final Scene scene) {
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN),
                () -> inputContent(Clipboard.getSystemClipboard())
        );
    }

    private void inputContent(Clipboard clipboard) {
        if (clipboard.hasFiles()) {
            inputContent(clipboard.getFiles().get(0).toURI().toASCIIString());
            return;
        }

        if (clipboard.hasUrl()) {
            inputContent(clipboard.getUrl());
            return;
        }

        if (clipboard.hasString()) {
            inputContent(clipboard.getString());
        }
    }

    private void inputContent(String content) {
        new BeautyLog().fetch().info("Content received: " + content);
    }
}

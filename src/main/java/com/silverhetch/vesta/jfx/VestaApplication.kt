package com.silverhetch.vesta.jfx

import com.silverhetch.clotho.log.BeautyLog
import com.silverhetch.vesta.database.H2DB
import com.silverhetch.vesta.jfx.util.ExceptionDialog
import com.silverhetch.vesta.target.DBTargets
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.input.*
import javafx.scene.layout.*
import javafx.scene.paint.Paint
import javafx.stage.Stage

import java.io.File.separator
import java.net.URI

class VestaApplication : Application() {
    @Throws(Exception::class)
    override fun start(stage: Stage) {
        val scene = Scene(rootView(), 640.0, 480.0)
        handlingClipBoard(scene)
        stage.scene = scene
        stage.show()
    }

    private fun rootView(): Pane {
        val root = StackPane()
        root.setOnDragOver { dragEvent ->
            if (dragEvent.dragboard.hasString()
                || dragEvent.dragboard.hasFiles()
                || dragEvent.dragboard.hasUrl()) {
                dragEvent.acceptTransferModes(*TransferMode.COPY_OR_MOVE)
            }
            dragEvent.consume()
        }
        root.setOnDragDropped { dragEvent ->
            inputContent(dragEvent.dragboard)
            dragEvent.isDropCompleted = true
            dragEvent.consume()
        }
        root.background = Background(BackgroundFill(Paint.valueOf("#000"), null, null))
        return root
    }

    private fun handlingClipBoard(scene: Scene) {
        scene.accelerators[KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN)] =Runnable{
            inputContent(Clipboard.getSystemClipboard())
        }
    }

    private fun inputContent(clipboard: Clipboard) {
        if (clipboard.hasFiles()) {
            inputContent(clipboard.files[0].toURI().toASCIIString())
            return
        }

        if (clipboard.hasUrl()) {
            inputContent(clipboard.url)
            return
        }

        if (clipboard.hasString()) {
            inputContent(clipboard.string)
        }
    }

    private fun inputContent(content: String) {
        BeautyLog().fetch().info("Content received: $content")

        try {
            DBTargets(
                H2DB(System.getProperty("user.dir")).connection()
            ).apply {
                add(URI(content) )
            }
        } catch (e: Exception) {
            ExceptionDialog(e).fetch()
        }

    }
}

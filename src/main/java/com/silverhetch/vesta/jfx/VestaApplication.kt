package com.silverhetch.vesta.jfx

import com.silverhetch.clotho.log.BeautyLog
import com.silverhetch.vesta.Vesta
import com.silverhetch.vesta.VestaImpl
import com.silverhetch.vesta.downloads.DBDownloads
import com.silverhetch.vesta.downloads.DownloadServiceImpl
import com.silverhetch.vesta.downloads.Downloads
import com.silverhetch.vesta.jfx.util.ExceptionDialog
import com.silverhetch.vesta.target.DBTargets
import com.silverhetch.vesta.target.Targets
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.input.*
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Paint
import javafx.stage.Stage
import java.io.File
import java.net.URI
import java.nio.file.Files

/**
 * Entry point of Vesta.
 */
class VestaApplication : Application() {
    private val vesta: Vesta = VestaImpl(
        File(
            System.getProperty("user.home") + File.separator + "abc"
        ).apply { mkdirs() }
    )

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
        scene.accelerators[KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN)] = Runnable {
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
        try {
            BeautyLog().fetch().info("Content received: $content")
            val targets: Targets = DBTargets(vesta.dbConnection()).apply { init() }
            val downloads: Downloads = DBDownloads(vesta.dbConnection()).apply { init() }
            if (content.startsWith("http")) {
                downloads.new(URI(content))
                DownloadServiceImpl(downloads, vesta.root()).start {
                    // @todo #8 Handles uri duplicate
                    targets.add(URI(content))
                }
            } else {
                targets.add(URI(content))
            }
        } catch (e: Exception) {
            ExceptionDialog(e).fetch()
        }
    }

    override fun stop() {
        super.stop()
        vesta.shutdown()
    }
}

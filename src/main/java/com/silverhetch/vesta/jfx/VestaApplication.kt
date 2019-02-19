package com.silverhetch.vesta.jfx

import com.silverhetch.clotho.log.BeautyLog
import com.silverhetch.vesta.Vesta
import com.silverhetch.vesta.VestaImpl
import com.silverhetch.vesta.downloads.DBDownloads
import com.silverhetch.vesta.downloads.DownloadServiceImpl
import com.silverhetch.vesta.downloads.Downloads
import com.silverhetch.vesta.jfx.tag.TagManagementView
import com.silverhetch.vesta.jfx.target.TargetManagementView
import com.silverhetch.vesta.jfx.util.ExceptionDialog
import com.silverhetch.vesta.tag.DBTags
import com.silverhetch.vesta.target.Targets
import com.silverhetch.vesta.target.VestaTargets
import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.input.Clipboard
import javafx.scene.input.KeyCode.V
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination.CONTROL_DOWN
import javafx.scene.input.TransferMode
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Paint
import javafx.stage.Stage
import java.io.File
import java.net.URI

/**
 * Entry point of Vesta.
 */
class VestaApplication : Application() {
    private lateinit var tagController: TagManagementView
    private lateinit var targetController: TargetManagementView

    private val vesta: Vesta = VestaImpl(
        File(
            System.getProperty("user.home") + File.separator + "abc"
        ).apply { mkdirs() }
    )

    override fun start(stage: Stage) {
        val scene = Scene(rootView(), 640.0, 480.0)
        scene.accelerators[KeyCodeCombination(V, CONTROL_DOWN)] = Runnable {
            inputContent(Clipboard.getSystemClipboard())
        }
        stage.scene = scene
        stage.title = "Vesta (main)"
        stage.show()

        val tagManagementStage = Stage()
        val loader = FXMLLoader(javaClass.getResource("/TagManagement.fxml"))
        val tagParent = loader.load<Any>() as Parent
        tagController = loader.getController<TagManagementView>()
        tagController.loadData(DBTags(vesta.dbConnection()).apply {
            init()
        })
        tagManagementStage.title = "Vesta (Tag management)"
        tagManagementStage.scene = Scene(tagParent)
        tagManagementStage.show()

        val targetManagementStage = Stage()
        val targetLoader = FXMLLoader(javaClass.getResource("/TargetManagement.fxml"))
        val targetParent = targetLoader.load<Any>() as Parent
        targetController = targetLoader.getController<TargetManagementView>()
        targetController.loadTargets(vesta, VestaTargets(vesta).apply { init() })
        targetManagementStage.title = "Vesta (Target management)"
        targetManagementStage.scene = Scene(targetParent)
        targetManagementStage.show()
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
            val targets: Targets = VestaTargets(vesta).apply { init() }
            val downloads: Downloads = DBDownloads(vesta.dbConnection()).apply { init() }
            downloads.new(URI(content))
            DownloadServiceImpl(
                downloads,
                vesta.downloadRoot()
            ).start { downloaded: File ->
                targets.add(downloaded)
                Platform.runLater {
                    targetController.loadTargets(vesta, targets)
                }
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

package com.silverhetch.vesta.jfx.target

import com.silverhetch.clotho.desktop.event.draging.ListDragging
import com.silverhetch.vesta.Vesta
import com.silverhetch.vesta.attached.tags.DBAttachedTags
import com.silverhetch.vesta.jfx.target.info.TargetInfoView
import com.silverhetch.vesta.tag.DBTags
import com.silverhetch.vesta.tag.uri.TagUriImpl
import com.silverhetch.vesta.target.Target
import com.silverhetch.vesta.target.Targets
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.input.DragEvent
import javafx.scene.input.TransferMode.*
import java.net.URI
import java.net.URL
import java.util.*

/**
 * Target management
 */
class TargetManagementView : Initializable {
    @FXML private lateinit var searchField: TextField
    @FXML private lateinit var listView: ListView<Target>
    @FXML private lateinit var targetInfoController: TargetInfoView
    private lateinit var vesta: Vesta
    private lateinit var targets: Targets
    override fun initialize(url: URL?, bundle: ResourceBundle?) {
        listView.setCellFactory {
            val cell = TargetListCell()
            cell.onDragEntered = ListDragging(cell, listView)
            cell.setOnDragOver { onTagDraggedOver(it) }
            cell.setOnDragDropped { onTagDropped(it, cell.item) }
            cell
        }
        listView.onMouseClicked = TargetListMouseEvent(listView)
        listView.selectionModel.selectedItemProperty().addListener { _, _, target: Target? ->
            target?.let { updateTargetInfo(target) }
        }

        searchField.textProperty().addListener { _, _, newValue ->
            loadTargets(targets.byKeyword(newValue).values)
        }
    }

    private fun onTagDraggedOver(it: DragEvent) {
        if (listView.selectionModel.selectedItems.size == 1
            && TagUriImpl(URI(it.dragboard.url)).valid()
        ) {
            it.acceptTransferModes(LINK)
            it.consume()
        }
    }

    private fun onTagDropped(it: DragEvent, target: Target) {
        if (it.dragboard.url == null) {
            return
        }
        val tagUri = TagUriImpl(URI(it.dragboard.url))
        if (tagUri.valid()) {
            DBAttachedTags(
                vesta.dbConnection(),
                target.id()
            ).add(DBTags(vesta.dbConnection()).byUri(tagUri))
            updateTargetInfo()
            it.isDropCompleted = true
            it.consume()
        }
    }

    private fun updateTargetInfo() {
        updateTargetInfo(listView.selectionModel.selectedItem)
    }

    private fun updateTargetInfo(target: Target) {
        targetInfoController.loadTarget(
            target,
            DBAttachedTags(
                vesta.dbConnection(),
                target.id()
            ).apply { init() }
        )
    }

    fun loadTargets(vesta: Vesta, targets: Targets) {
        this.vesta = vesta
        this.targets = targets
        loadTargets(targets.all().values)
    }

    private fun loadTargets(values: Collection<Target>) {
        listView.items.clear()
        listView.items.addAll(values)
    }
}
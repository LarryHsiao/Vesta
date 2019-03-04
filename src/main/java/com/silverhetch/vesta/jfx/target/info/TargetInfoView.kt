package com.silverhetch.vesta.jfx.target.info

import com.silverhetch.vesta.attached.tags.AttachedTag
import com.silverhetch.vesta.attached.tags.AttachedTags
import com.silverhetch.vesta.tag.uri.TagUriImpl
import com.silverhetch.vesta.target.Target
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.input.DragEvent
import javafx.scene.input.TransferMode.LINK
import java.net.URI
import java.net.URL
import java.util.*

/**
 * View that shows target.
 */
class TargetInfoView : Initializable {
    @FXML private lateinit var label: Label
    @FXML private lateinit var tagList: ListView<AttachedTag>
    private lateinit var target: Target
    private lateinit var attachedTags: AttachedTags

    override fun initialize(url: URL?, bundle: ResourceBundle?) {
        tagList.setCellFactory { AttachedTagCell() }
        tagList.setOnDragOver { onDraggedOver(it) }
        tagList.setOnDragDropped { onDraggedDropped(it) }
    }

    private fun onDraggedDropped(it: DragEvent) {
        if (it.dragboard.url == null) {
            return
        }
        val tagUri = TagUriImpl(URI(it.dragboard.url))
        if (tagUri.valid()) {
            attachedTags.add(tagUri)
            it.isDropCompleted = true
            it.consume()
            loadTarget()
        }
    }

    private fun onDraggedOver(it: DragEvent) {
        if (TagUriImpl(URI(it.dragboard.url)).valid()) {
            it.run {
                acceptTransferModes(LINK)
                consume()
            }
        }
    }

    /**
     * Load the info view by [Target] and [AttachedTags].
     */
    fun loadTarget(target: Target, attachedTags: AttachedTags) {
        this.target = target
        this.attachedTags = attachedTags
        loadTarget()
    }

    private fun loadTarget() {
        label.text = target.name() + " "
        tagList.items.clear()
        tagList.items.addAll(attachedTags.all().values)
    }
}
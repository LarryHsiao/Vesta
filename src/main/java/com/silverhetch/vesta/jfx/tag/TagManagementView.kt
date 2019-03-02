package com.silverhetch.vesta.jfx.tag

import com.silverhetch.vesta.tag.Tag
import com.silverhetch.vesta.tag.Tags
import com.silverhetch.vesta.tag.uri.TagUriImpl
import com.sun.javafx.collections.ObservableListWrapper
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.input.*
import javafx.scene.input.TransferMode.*
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


/**
 * Tag management view. Provides CRUD operation.
 */
class TagManagementView : Initializable {
    @FXML private lateinit var listView: ListView<Tag>
    @FXML private lateinit var newTagField: TextField
    @FXML private lateinit var newTagFieldButton: Button
    private lateinit var tags: Tags

    override fun initialize(p0: URL?, bundle: ResourceBundle?) {
        listView.items = ObservableListWrapper<Tag>(ArrayList<Tag>())
        listView.onMouseClicked = TagListMouseEvent(listView)
        listView.setCellFactory {
            TagListCell().apply {
                setOnDragDetected {
                    val db = startDragAndDrop(LINK)
                    val clipboard = ClipboardContent()
                    clipboard.putUrl(TagUriImpl(item).value().toString())
                    db.setContent(clipboard)
                }
            }
        }
    }

    fun loadData(tags: Tags) {
        this.tags = tags
        loadData()
    }

    private fun loadData() {
        listView.items.clear()
        listView.items.addAll(tags.all().values)
    }

    fun onNewtagClicked() {
        if (newTagField.text.isEmpty()) {
            return
        }
        tags.add(newTagField.text)
        newTagField.text = ""
        loadData()
    }
}
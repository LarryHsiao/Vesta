package com.silverhetch.vesta.jfx

import com.jfoenix.controls.JFXListView
import com.jfoenix.controls.JFXPopup
import com.jfoenix.controls.JFXPopup.PopupHPosition.LEFT
import com.jfoenix.controls.JFXPopup.PopupVPosition.TOP
import com.silverhetch.vesta.tag.Tag
import com.silverhetch.vesta.tag.Tags
import com.sun.javafx.collections.ObservableListWrapper
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.input.MouseButton.SECONDARY
import javafx.scene.input.MouseEvent
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
        listView.setCellFactory {
            object : ListCell<Tag>() {
                override fun updateItem(item: Tag?, empty: Boolean) {
                    super.updateItem(item, empty)
                    text = if (empty) {
                        ""
                    } else {
                        item?.name() ?: ""
                    }
                    graphic = null
                }
            }
        }
        listView.setOnMouseClicked { event ->
            if (event.button == SECONDARY) {
                listView.selectionModel.selectedItem?.also { selected ->
                    JFXPopup().apply {
                        popupContent = JFXListView<String>().let { popupListView ->
                            popupListView.id = "popup"
                            popupListView.items.addAll(
                                "Remove"
                            )
                            popupListView.selectionModel.selectedIndexProperty().addListener { _, _, index ->
                                when (index) {
                                    0 -> {
                                        selected.delete()
                                        listView.items.remove(selected)
                                    }
                                }
                                hide()
                            }
                            popupListView
                        }
                        show(listView, TOP, LEFT, event.x, event.y)
                    }
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

    fun onNewtagClicked(mouseEvent: MouseEvent) {
        if (newTagField.text.isEmpty()) {
            return
        }
        tags.add(newTagField.text)
        newTagField.text = ""
        loadData()
    }
}
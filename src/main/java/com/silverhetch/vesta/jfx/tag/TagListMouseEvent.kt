package com.silverhetch.vesta.jfx.tag

import com.jfoenix.controls.JFXListView
import com.jfoenix.controls.JFXPopup
import com.silverhetch.vesta.tag.Tag
import javafx.event.EventHandler
import javafx.scene.control.ListView
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent

/**
 * Handling mouse event on tag list.
 */
class TagListMouseEvent(private val listView: ListView<Tag>) : EventHandler<MouseEvent> {
    override fun handle(event: MouseEvent?) {
        if (event?.button == MouseButton.SECONDARY) {
            listView.selectionModel.selectedItem?.also { selected ->
                val popup = JFXPopup()
                popup.popupContent = JFXListView<String>().let { popupListView ->
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
                        popup.hide()
                    }
                    popupListView
                }
                popup.show(listView, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.x, event.y)
            }
        }
    }
}
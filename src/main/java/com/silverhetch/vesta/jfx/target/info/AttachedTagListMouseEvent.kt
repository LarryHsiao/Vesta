package com.silverhetch.vesta.jfx.target.info

import com.jfoenix.controls.JFXListView
import com.jfoenix.controls.JFXPopup
import com.jfoenix.controls.JFXPopup.PopupHPosition.LEFT
import com.jfoenix.controls.JFXPopup.PopupVPosition.TOP
import com.silverhetch.vesta.attached.tags.AttachedTag
import javafx.event.EventHandler
import javafx.scene.control.ListView
import javafx.scene.input.MouseButton.SECONDARY
import javafx.scene.input.MouseEvent

/**
 * Handling mouse event on tag list.
 */
class AttachedTagListMouseEvent(
    private val listView: ListView<AttachedTag>
) : EventHandler<MouseEvent> {
    override fun handle(event: MouseEvent?) {
        if (event?.button == SECONDARY) {
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
                                selected.remove()
                                listView.items.remove(selected)
                            }
                        }
                        popup.hide()
                    }
                    popupListView
                }
                popup.show(listView, TOP, LEFT, event.x, event.y)
            }
        }
    }
}
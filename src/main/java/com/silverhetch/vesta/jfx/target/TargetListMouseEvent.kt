package com.silverhetch.vesta.jfx.target

import com.jfoenix.controls.JFXListView
import com.jfoenix.controls.JFXPopup
import com.jfoenix.controls.JFXPopup.PopupHPosition.*
import com.jfoenix.controls.JFXPopup.PopupVPosition.*
import com.silverhetch.vesta.target.Target
import javafx.event.EventHandler
import javafx.scene.control.ListView
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseButton.*
import javafx.scene.input.MouseEvent

/**
 * Handling mouse event on tag list.
 */
class TargetListMouseEvent(
    private val listView: ListView<Target>
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
                                selected.delete()
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
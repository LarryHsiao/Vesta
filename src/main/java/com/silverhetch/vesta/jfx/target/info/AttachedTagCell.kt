package com.silverhetch.vesta.jfx.target.info

import com.silverhetch.vesta.attached.tags.AttachedTag
import javafx.scene.control.ListCell

/**
 * ListView cell for [AttachedTag].
 */
class AttachedTagCell : ListCell<AttachedTag>() {
    override fun updateItem(item: AttachedTag?, empty: Boolean) {
        super.updateItem(item, empty)
        text = if (empty) {
            ""
        } else {
            item?.tag()?.name() ?: ""
        }
        graphic = null
    }
}
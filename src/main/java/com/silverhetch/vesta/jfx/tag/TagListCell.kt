package com.silverhetch.vesta.jfx.tag

import com.silverhetch.vesta.tag.Tag
import javafx.scene.control.ListCell

/**
 * Tag list cell
 */
class TagListCell : ListCell<Tag>() {
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
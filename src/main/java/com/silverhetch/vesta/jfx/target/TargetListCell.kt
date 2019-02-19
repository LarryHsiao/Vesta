package com.silverhetch.vesta.jfx.target

import com.silverhetch.vesta.target.Target
import javafx.scene.control.ListCell

/**
 * Target list cell
 */
class TargetListCell : ListCell<Target>() {
    override fun updateItem(item: Target?, empty: Boolean) {
        super.updateItem(item, empty)
        text = if (empty) {
            ""
        } else {
            item?.name() ?: ""
        }
        graphic = null
    }
}
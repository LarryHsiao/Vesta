package com.silverhetch.vesta.jfx.target.info

import com.silverhetch.vesta.attached.tags.AttachedTag
import com.silverhetch.vesta.attached.tags.AttachedTags
import com.silverhetch.vesta.target.Target
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import javafx.scene.control.ListView
import java.net.URL
import java.util.*

/**
 * View that shows target.
 */
class TargetInfoView : Initializable {
    @FXML private lateinit var label: Label
    @FXML private lateinit var tagList: ListView<AttachedTag>
    private lateinit var target: Target

    override fun initialize(url: URL?, bundle: ResourceBundle?) {
        tagList.setCellFactory { AttachedTagCell() }
    }

    /**
     * Load the info view by [Target] and [AttachedTags].
     */
    fun loadTarget(target: Target, attachedTags: AttachedTags) {
        this.target = target
        label.text = target.name() + " "
        tagList.items.clear()
        tagList.items.addAll(attachedTags.all().values)
    }
}
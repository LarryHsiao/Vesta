package com.silverhetch.vesta.jfx.target.info

import com.silverhetch.vesta.attachement.Attachments
import com.silverhetch.vesta.target.Target
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import java.net.URL
import java.util.*

/**
 * View that shows target.
 */
class TargetInfoView : Initializable {
    @FXML private lateinit var label: Label
    private lateinit var target: Target

    override fun initialize(url: URL?, bundle: ResourceBundle?) {
    }

    fun loadTarget(target: Target, attachments: Attachments) {
        this.target = target
        label.text = target.name() + " "
        attachments.all().forEach {
            label.text += it.value.tag().name() + " "
        }
    }
}
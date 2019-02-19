package com.silverhetch.vesta.jfx.target

import com.silverhetch.vesta.target.Target
import com.silverhetch.vesta.target.Targets
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ListView
import java.net.URL
import java.util.*

/**
 * Target management
 */
class TargetManagementView : Initializable {
    @FXML private lateinit var listView: ListView<Target>
    private lateinit var targets: Targets
    override fun initialize(url: URL?, bundle: ResourceBundle?) {
        listView.setCellFactory { TargetListCell() }
        listView.onMouseClicked = TargetListMouseEvent(listView)

        /**
         * @todo #9 Handles event to attach tag
         */
    }

    fun loadTargets(targets: Targets) {
        this.targets = targets
        loadTargets()
    }

    private fun loadTargets() {
        listView.items.clear()
        listView.items.addAll(targets.all().values)
    }
}
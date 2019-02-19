package com.silverhetch.vesta.jfx.target

import com.silverhetch.vesta.Vesta
import com.silverhetch.vesta.attachement.DBAttachedTags
import com.silverhetch.vesta.jfx.target.info.TargetInfoView
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
    @FXML private lateinit var targetInfoController: TargetInfoView
    private lateinit var vesta: Vesta
    private lateinit var targets: Targets
    override fun initialize(url: URL?, bundle: ResourceBundle?) {
        listView.setCellFactory { TargetListCell() }
        listView.onMouseClicked = TargetListMouseEvent(listView)
        listView.selectionModel.selectedItemProperty().addListener { _, _, target: Target ->
            targetInfoController.loadTarget(
                target,
                DBAttachedTags(
                    vesta.dbConnection(),
                    target.id()
                ).apply { init() }
            )
        }

        /**
         * @todo #9 Handles event to attach tag
         */
    }

    fun loadTargets(vesta: Vesta, targets: Targets) {
        this.vesta = vesta
        this.targets = targets
        loadTargets()
    }

    private fun loadTargets() {
        listView.items.clear()
        listView.items.addAll(targets.all().values)
    }
}
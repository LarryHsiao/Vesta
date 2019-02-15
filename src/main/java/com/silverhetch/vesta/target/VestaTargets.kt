package com.silverhetch.vesta.target

import com.silverhetch.vesta.Vesta
import com.silverhetch.vesta.target.db.DBTargets
import com.silverhetch.vesta.target.file.FileTargets
import java.io.File

class VestaTargets(private val vesta: Vesta) : Targets {
    private val db = DBTargets(vesta.dbConnection())
    private val fileSystem = FileTargets(vesta.targetRoot())
    override fun init() {
        db.init()
        fileSystem.init()
    }

    override fun add(newFile: File) {
        fileSystem.add(newFile)
        db.add(newFile)
    }

    override fun all(): Map<String, Target> {
        /**
         * @todo #file-1 handles file system changed
         */
        return db.all()
    }
}
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

    override fun byName(name: String): Target {
        return VestaTarget(
            fileSystem.byName(name),
            db.byName(name)
        )
    }

    override fun byKeyword(keyword: String): Map<String, Target> {
        return outputMap(db.byKeyword(keyword))
    }

    override fun all(): Map<String, Target> {
        /**
         * @todo #file-1 handles file system changed
         */
        return outputMap(db.all())
    }

    private fun outputMap(dbTargets: Map<String, Target>): Map<String, Target> {
        val result = LinkedHashMap<String, Target>()
        dbTargets.forEach { dbEntry ->
            result[dbEntry.key] = VestaTarget(fileSystem.byName(dbEntry.key), dbEntry.value)
        }
        return result
    }
}
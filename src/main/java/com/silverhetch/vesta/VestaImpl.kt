package com.silverhetch.vesta

import com.silverhetch.vesta.database.H2DB
import com.silverhetch.vesta.target.Targets
import java.io.File
import java.sql.Connection

/**
 * Implementation of [Vesta].
 */
class VestaImpl(private val root: File) : Vesta {
    private val dbConnection: Connection by lazy {
        H2DB(root.absolutePath).connection()
    }

    override fun root(): File {
        return root
    }

    override fun downloadRoot(): File {
        return File(root, "download").apply { mkdirs() }
    }

    override fun targetRoot(): File {
        return File(root, "targets").apply { mkdirs() }
    }

    override fun dbConnection(): Connection {
        return dbConnection
    }

    override fun shutdown() {
        dbConnection.close()
    }
}
package com.silverhetch.vesta

import com.silverhetch.vesta.database.H2DB
import java.io.File
import java.sql.Connection

/**
 * Implementation of [Vesta].
 */
class VestaImpl(private val root: File) : Vesta {
    private val dbConnection: Connection by lazy {
        H2DB(System.getProperty("user.home")).connection()
    }

    override fun root(): File {
        return root
    }

    override fun dbConnection(): Connection {
        return dbConnection
    }

    override fun shutdown() {
        dbConnection.close()
    }
}
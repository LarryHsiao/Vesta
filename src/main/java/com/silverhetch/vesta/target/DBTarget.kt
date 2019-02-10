package com.silverhetch.vesta.target

import java.net.URI
import java.sql.Connection

class DBTarget(private val connection: Connection, private val uri: String) : Target {
    override fun delete() {
        connection.prepareStatement("""delete from target where uri=?;""").use {
            it.setString(1, "uri")
        }
    }

    override fun uri(): URI {
        return URI(uri)
    }
}
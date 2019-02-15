package com.silverhetch.vesta.target.db

import com.silverhetch.vesta.target.Target
import java.net.URI
import java.sql.Connection

/**
 * DB implementation of [Target]
 */
class DBTarget(private val connection: Connection,
               private val id : Long,
               private val uri: String) : Target {
    override fun id(): Long {
        return id
    }

    override fun delete() {
        connection.prepareStatement("""delete from targets where id=?;""").use {
            it.setLong(1, id)
        }
    }

    override fun uri(): URI {
        return URI(uri)
    }
}
package com.silverhetch.vesta.downloads

import java.net.URI
import java.sql.Connection

/**
 * Implementation of [Download]
 */
class DBDownload(
    private val connection: Connection,
    private val id: Long,
    private val uri: URI
) : Download {
    override fun id(): Long {
        return id
    }

    override fun uri(): URI {
        return uri
    }

    override fun delete() {
        connection.prepareStatement(
            """delete from downloads where id=?"""
        ).use {
            it.setLong(1, id)
            it.execute()
        }
    }
}
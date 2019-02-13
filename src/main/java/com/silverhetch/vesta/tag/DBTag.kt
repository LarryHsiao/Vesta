package com.silverhetch.vesta.tag

import java.sql.Connection

/**
 * A tag which stored in database.
 */
class DBTag(
    private val connection: Connection,
    private val id: Long,
    private val name: String) : Tag {

    override fun name(): String {
        return name
    }

    override fun id(): Long {
        return id
    }
}
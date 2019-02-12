package com.silverhetch.vesta.tag

import java.sql.Connection

/**
 * A tag which stored in database.
 */
class DBTag(
    private val connection: Connection,
    private val name: String) : Tag {

    override fun name(): String {
        return name
    }
}
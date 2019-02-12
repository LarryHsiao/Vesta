package com.silverhetch.vesta.tag

import java.sql.Connection

/**
 * Tags that stored in database.
 */
class DBTags(private val connection: Connection) : Tags {
    override fun init() {
        connection.createStatement().execute("""
            CREATE TABLE IF NOT EXISTS tags
            (
              name CHAR unique
            );""")
    }

    override fun add(name: String) {
        connection.prepareStatement("""insert into tags values ( ? );""").use {
            it.setString(1, name)
            it.execute()
        }
    }

    override fun all(): Array<Tag> {
        connection.createStatement().use { statement ->
            val result = ArrayList<Tag>()
            statement.executeQuery("""select * from tags""").use { resultSet ->
                while (resultSet.next()) {
                    result.add(DBTag(
                        connection,
                        resultSet.getString("name")
                    ))
                }
            }
            return result.toTypedArray()
        }
    }
}

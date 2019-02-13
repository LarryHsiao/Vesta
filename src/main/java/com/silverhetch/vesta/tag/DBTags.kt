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
              id integer primary key auto_increment,
              name CHAR unique
            );""")
    }

    override fun add(name: String) {
        connection.prepareStatement("""insert into tags(name) values ( ? );""").use {
            it.setString(1, name)
            it.execute()
        }
    }

    override fun all(): Map<String, Tag> {
        connection.createStatement().use { statement ->
            val result = HashMap<String, Tag>()
            statement.executeQuery("""select * from tags""").use { resultSet ->
                while (resultSet.next()) {
                    val name = resultSet.getString("name")
                    result[name] = DBTag(
                        connection,
                        resultSet.getLong("id"),
                        name
                    )
                }
            }
            return result
        }
    }
}

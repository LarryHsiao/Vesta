package com.silverhetch.vesta.tag

import com.silverhetch.vesta.tag.uri.TagUri
import java.lang.IllegalArgumentException
import java.net.URI
import java.sql.Connection
import java.sql.ResultSet

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

    override fun byUri(uri: TagUri): Tag {
        if (!uri.valid()) {
            throw IllegalArgumentException("Invalid TagUri")
        }
        connection.prepareStatement("""select * from tags where id=? limit 1""").use { statement ->
            statement.setLong(1, uri.id())
            statement.executeQuery().use { result ->
                result.next()
                return DBTag(
                    connection,
                    result.getLong("id"),
                    result.getString("name")
                )
            }
        }
    }

    override fun all(): Map<String, Tag> {
        connection.createStatement().use { statement ->
            val result = LinkedHashMap<String, Tag>()
            statement.executeQuery("""select * from tags order by name asc""").use { resultSet ->
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

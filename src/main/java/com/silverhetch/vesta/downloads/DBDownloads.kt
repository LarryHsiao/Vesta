package com.silverhetch.vesta.downloads

import java.net.URI
import java.sql.Connection

/**
 * Implementation of [Downloads].
 */
class DBDownloads(private val connection: Connection) : Downloads {
    override fun init() {
        connection.createStatement().execute("""
            create table if not exists downloads(
              id integer primary key auto_increment,
              uri char unique
            );""")
    }

    override fun new(uri: URI) {
        connection.prepareStatement("""insert into downloads(uri) values ( ? );""").use {
            it.setString(1, uri.toString())
            it.execute()
        }
    }

    override fun all(): Map<String, URI> {
        connection.prepareStatement("""
           select * from downloads;
        """).use { statement ->
            statement.executeQuery().use {
                val result = HashMap<String, URI>()
                while (it.next()) {
                    val uri = it.getString("uri")
                    result[uri] = URI(uri)
                }
                return result
            }
        }
    }
}
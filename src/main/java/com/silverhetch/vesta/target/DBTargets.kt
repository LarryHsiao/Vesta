package com.silverhetch.vesta.target

import java.net.URI
import java.sql.Connection

/**
 * Database implementation of Targets.
 */
class DBTargets(private val connection: Connection) : Targets {
    override fun init() {
        connection.createStatement().execute("""
            create table if not exists targets
            (
              id integer primary key auto_increment,
              uri char unique
            );"""
        )
    }

    override fun add(uri: URI) {
        connection.prepareStatement("""insert into targets(uri) values (?);""").use { statement ->
            statement.setString(1, uri.toString())
            statement.execute()
        }
    }

    override fun all(): Array<Target> {
        connection.createStatement().use { statement ->
            val targets = ArrayList<Target>()
            statement.executeQuery("""select * from targets;""").use { resultSet ->
                while (resultSet.next()) {
                    targets.add(DBTarget(
                        connection,
                        resultSet.getLong("id"),
                        resultSet.getString("uri")
                    ))
                }
            }
            return targets.toTypedArray()
        }
    }
}
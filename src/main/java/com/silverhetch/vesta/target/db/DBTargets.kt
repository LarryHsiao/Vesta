package com.silverhetch.vesta.target.db

import com.silverhetch.vesta.target.Target
import com.silverhetch.vesta.target.Targets
import java.io.File
import java.sql.Connection

/**
 * Database implementation of Targets.
 */
class DBTargets(
    private val connection: Connection
) : Targets {
    override fun init() {
        connection.createStatement().execute("""
            create table if not exists targets
            (
              id integer primary key auto_increment,
              name char unique
            );"""
        )
    }

    override fun add(newFile: File) {
        connection.prepareStatement("""insert into targets(name) values (?);""").use { statement ->
            statement.setString(1, newFile.name)
            statement.execute()
        }
    }

    override fun all(): Map<String, Target> {
        connection.createStatement().use { statement ->
            val targets = LinkedHashMap<String, Target>()
            statement.executeQuery("""select * from targets order by name asc;""").use { resultSet ->
                while (resultSet.next()) {
                    val name = resultSet.getString("name")
                    targets[name] = DBTarget(
                        connection,
                        resultSet.getLong("id"),
                        name
                    )
                }
            }
            return targets
        }
    }
}
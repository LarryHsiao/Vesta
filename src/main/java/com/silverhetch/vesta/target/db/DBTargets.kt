package com.silverhetch.vesta.target.db

import com.silverhetch.vesta.target.Target
import com.silverhetch.vesta.target.Targets
import java.io.File
import java.sql.Connection
import java.sql.ResultSet

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

    override fun byName(name: String): Target {
        connection.prepareStatement("""select * from targets where NAME like ?;""").use { statement ->
            statement.setString(1, name)
            statement.executeQuery().use { resultSet ->
                resultSet.next()
                return toTarget(resultSet)
            }
        }
    }

    override fun all(): Map<String, Target> {
        connection.createStatement().use { statement ->
            val targets = LinkedHashMap<String, Target>()
            statement.executeQuery("""select * from targets order by name asc;""").use { resultSet ->
                while (resultSet.next()) {
                    toResult(targets, resultSet)
                }
            }
            return targets
        }
    }

    private fun toResult(map: LinkedHashMap<String, Target>, resultSet: ResultSet) {
        val name = resultSet.getString("name")
        map[name] = toTarget(resultSet)
    }

    private fun toTarget(resultSet: ResultSet): Target {
        return DBTarget(
            connection,
            resultSet.getLong("id"),
            resultSet.getString("name")
        )
    }
}
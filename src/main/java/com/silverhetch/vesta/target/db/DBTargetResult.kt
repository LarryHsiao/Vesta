package com.silverhetch.vesta.target.db

import com.silverhetch.vesta.target.Target
import java.sql.Connection
import java.sql.ResultSet

class DBTargetResult(
    private val conn: Connection,
    private val resultSet: ResultSet
) {
    fun target(): Target {
        return DBTarget(
            conn,
            resultSet.getLong("id"),
            resultSet.getString("name")
        )
    }
}
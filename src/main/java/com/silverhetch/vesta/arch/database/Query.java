package com.silverhetch.vesta.arch.database;

import com.silverhetch.vesta.arch.database.sql.Sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * General Purpose query execution.
 */
public class Query implements SqlExecution<ResultSet> {
    private final Connection dbConn;
    private final Sql sql;

    public Query(Connection dbConn, Sql sql) {
        this.dbConn = dbConn;
        this.sql = sql;
    }

    public ResultSet execute() throws SQLException {
        return dbConn.createStatement().executeQuery(
                sql.generate()
        );
    }
}

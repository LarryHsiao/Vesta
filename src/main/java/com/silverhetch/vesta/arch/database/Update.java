package com.silverhetch.vesta.arch.database;

import com.silverhetch.clotho.Source;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * General sql execution.
 */
public class Update implements SqlExecution<Boolean> {
    private final Source<Connection> connSource;
    private final Sql sql;

    public Update(Source<Connection> connSource, Sql sql) {
        this.connSource = connSource;
        this.sql = sql;
    }

    @Override
    public Boolean execute() throws SQLException {
        return connSource.fetch().createStatement().execute(
                sql.generate()
        );
    }
}
